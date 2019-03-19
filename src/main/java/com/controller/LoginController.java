/**
 * 
 */
package com.controller;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import util.RSAUtils;
import util.Tool;

import com.entity.Admin;
import com.entity.util.ReturnError;
import com.service.AdminService;
import com.util.DateUtil;
import com.util.IdGenerator;
import com.util.MD5;
import com.util.ResponseUtil;
import com.validator.LoginGroup;

/**
 * Title: gsguoshui<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017 <br>
 * Create DateTime: 2017-8-7 上午11:23:37 <br>
 * 
 * @author freeway
 */
@RestController
@RequestMapping("/login")
public class LoginController {
	private static final Logger log = Logger.getLogger(LoginController.class);// 日志文件

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/creatKey", method = RequestMethod.POST)
	public void creatKey(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		RSAUtils rsa = new RSAUtils();
		// 生成公钥和密钥
		Map<String, Object> keyMap = rsa.createKey();
		RSAPublicKey publicKey = (RSAPublicKey) keyMap.get("publicKey");
		RSAPrivateKey privateKey = (RSAPrivateKey) keyMap.get("privateKey");
		JSONObject jsonObject = null;
		try {
			// js通过模和公钥指数获取公钥对字符串进行加密，注意必须转为16进制
			// 模
			String Modulus = publicKey.getModulus().toString(16);
			// 公钥指数
			String Exponent = publicKey.getPublicExponent().toString(16);
			// 私钥指数
			String private_exponent = privateKey.getPrivateExponent()
					.toString();
			// java中的模和私钥指数不需要转16进制，但是js中的需要转换为16进制
			session.setAttribute("Modulus", publicKey.getModulus().toString());
			session.setAttribute("private_exponent", private_exponent);
			map.put("errType", "true");
			map.put("Exponent", Exponent);
			map.put("Modulus", Modulus);
			jsonObject = JSONObject.fromObject(map);
			ResponseUtil.write(response, jsonObject);
		} catch (Exception e) {
			map.put("errType", "error");
			jsonObject = JSONObject.fromObject(map);
			ResponseUtil.write(response, jsonObject);
		}
	}

	@PostMapping("/adminLogin.shtml")
	public synchronized void login(
			@Validated(value = LoginGroup.class) Admin admin,
			BindingResult bindingResult, HttpSession session,
			HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ReturnError returnError = new ReturnError();
		// 判断参数
		checkGroup(returnError, response, bindingResult);
		MD5 md5 = new MD5();
		try {
			RSAUtils rsa = new RSAUtils();
			String Modulus = (String) session.getAttribute("Modulus");
			String private_exponent = (String) session
					.getAttribute("private_exponent");
			// 根据模和私钥指数获取私钥
			RSAPrivateKey prkey = RSAUtils.getPrivateKey(Modulus,
					private_exponent);
			// session.invalidate();
			// session = request.getSession();
			// rsa解码
			admin.setApassword(md5.getMD5ofStr(rsa.decrypttoStr(prkey,
					admin.getApassword())));
			admin = adminService.login(admin);
			if (admin == null) {
				returnError.setHasError(true);
				returnError.setError("账号或密码错误");
				ResponseUtil.write(response, JSONObject.fromObject(returnError));
				return;
			}
			admin.setIp(Tool.getIp(request));
			admin.setAtoken(IdGenerator.genPrimaryKey().replace("-", ""));
			admin.setAlogintime(DateUtil.getCurrentDateStr());
			adminService.update(admin);
			log.info("管理员登录：" + admin.getAname() + "，" + admin.getIp());
			session.setAttribute("adminLogin", admin);
			returnError.setHasError(false);
			returnError.setErrType("true");
			returnError.setErrInfo("index.html");
			ResponseUtil.write(response, JSONObject.fromObject(returnError));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			returnError.setHasError(true);
			returnError.setError("系统异常");
			ResponseUtil.write(response, JSONObject.fromObject(returnError));
			return;
		}

	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	@RequestMapping("/loginOut.shtml")
	public ModelAndView loginOut(HttpServletRequest request,
			HttpServletResponse response,HttpSession hs) {
		hs.removeAttribute("adminLogin");
		ModelAndView mv = new ModelAndView("redirect:../admin/login.html");
		return mv;
	}

	@PostMapping("/checkLogin.shtml")
	public void checkLogin(HttpServletResponse response,
			HttpServletRequest request, HttpSession hs) throws Exception {
		Admin admin = (Admin) hs.getAttribute("adminLogin");
		ReturnError returnError = new ReturnError();
		if (admin != null) {
			returnError.setErrType("logined");
			returnError.setErrInfo("index.html");

		} else {
			returnError.setErrType("nologin");
		}
		ResponseUtil.write(response, JSONObject.fromObject(returnError));
	}

	@PostMapping("/getLoginInfo.shtml")
	public void getLoginInfo(HttpServletResponse response,
			HttpServletRequest request,HttpSession hs) throws Exception {
		ReturnError returnError = new ReturnError();
		Admin admin = (Admin) hs.getAttribute("adminLogin");
		Admin admin2 = null;
		if(admin==null){
			hs.removeAttribute("adminLogin");
			returnError.setHasError(true);
			returnError.setErrType("outtime");
			returnError.setError("会话过期，请重新登录");
		}else{
			admin2 = adminService.findById(admin.getAid());
			if (!admin2.getIp().equals(admin.getIp()) || !admin2.getAtoken().equals(admin.getAtoken())) {
				hs.removeAttribute("adminLogin");
				returnError.setHasError(true);
				returnError.setErrType("outtime");
				returnError.setError("账号被登出");
			}else{
				returnError.setHasError(false);
				returnError.setObject(admin);
			}
		}
		ResponseUtil.write(response, JSONObject.fromObject(returnError));
	}

	// 判断参数是否错误
	public void checkGroup(ReturnError returnError,
			HttpServletResponse response, BindingResult bindingResult)
			throws Exception {
		if (bindingResult.hasErrors()) {
			returnError.setHasError(true);
			returnError.setError("参数错误");
			ResponseUtil.write(response, JSONObject.fromObject(returnError));
			return;
		}
	}
}
