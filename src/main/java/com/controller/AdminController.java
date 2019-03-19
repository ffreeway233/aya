/**
 * 
 */
package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Admin;
import com.entity.util.ReturnError;
import com.service.AdminService;
import com.util.MD5;
import com.util.ResponseUtil;
import com.validator.AddGroup;
import com.validator.UpdGroup;

/**
 * Title: gsguoshui<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017 <br>
 * Create DateTime: 2017-8-9 上午10:42:14 <br>
 * 
 * @author freeway
 */
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

	@Autowired
	private AdminService adminService;
	@RequestMapping("/updPsd.shtml")
	public void updPsd(String newpassword, String oldpassword,
			String repassword, HttpServletResponse response,HttpSession session) throws Exception {
		ReturnError returnError = new ReturnError();
		MD5 md5 = new MD5();
		try {
			Admin admin = (Admin) session.getAttribute("adminLogin");
			if (admin != null) {
				if(!md5.getMD5ofStr(oldpassword).equals(admin.getApassword())){
					returnError.setHasError(true);
					returnError.setError("原密码错误");
				}else{
				admin.setApassword(md5.getMD5ofStr(repassword));
				adminService.update(admin);
				session.removeAttribute("adminLogin");
				session.setAttribute("adminLogin", admin);
				returnError.setHasError(false);
				returnError.setError("更新成功！");
				}
				
			} else {
				returnError.setHasError(true);
				returnError.setErrType("nologin");
				returnError.setError("未登录");
				session.removeAttribute("adminLogin");
			}
		} catch (Exception e) {
			e.printStackTrace();
			returnError.setHasError(true);
			returnError.setErrType("error");
			returnError.setError("操作异常");
		}finally{
			ResponseUtil.write(response, JSONObject.fromObject(returnError));
		}
	}

	@RequestMapping("/loadAdmin.shtml")
	public void loadAdmin(
			@RequestParam(required = true, defaultValue = "0") Integer pageIndex,
			@RequestParam(required = true, defaultValue = "15") Integer limit,
			String key, HttpServletResponse response) throws Exception {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageIndex * limit);
		map.put("limit", limit);
		map.put("key", key);
		try {
			remap.put("rows", adminService.queryRowsByMap(map));
			remap.put("results", adminService.queryNumberByMap(map));
			remap.put("hasError", false);
			ResponseUtil.write(response, JSONObject.fromObject(remap));
		} catch (Exception e) {
			e.printStackTrace();
			remap.put("rows", "");
			remap.put("results", 0);
			remap.put("hasError", true);
			remap.put("error", "error");
			ResponseUtil.write(response, JSONObject.fromObject(remap));
		}
	}

	@PostMapping("/addAdm.shtml")
	public void addAdm(@Validated(value = AddGroup.class) Admin admin,
			BindingResult bindingResult, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		MD5 md5 = new MD5();
		if (bindingResult.hasErrors()) {
			map.put("hasError", true);
			map.put("error", "提交参数错误");
			ResponseUtil.write(response, JSONObject.fromObject(map));
			return;
		}
		int rs = adminService.qureyAdminByAname(admin.getAname());
		if (rs != 0) {
			map.put("hasError", true);
			map.put("error", "已存在该名称账号");
		} else {
			admin.setApassword(md5.getMD5ofStr(admin.getApassword()));
			adminService.save(admin);
			map.put("hasError", false);
			map.put("error", "新增成功！");
		}
		ResponseUtil.write(response, JSONObject.fromObject(map));
	}

	@PostMapping("/updAdm.shtml")
	public void updAdm(@Validated(value = UpdGroup.class) Admin admin,
			BindingResult bindingResult, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		MD5 md5 = new MD5();
		Admin adm = null;
		if (bindingResult.hasErrors()) {
			map.put("hasError", true);
			map.put("error", "提交参数错误");
			ResponseUtil.write(response, JSONObject.fromObject(map));
			return;
		}
		adm = adminService.findById(admin.getAid());
		if (adm == null) {
			map.put("hasError", true);
			map.put("error", "是空号");
		} else {
			admin.setIp(adm.getIp());
			admin.setAlogintime(adm.getAlogintime());
			admin.setAtoken(adm.getAtoken());
			admin.setApassword(md5.getMD5ofStr(admin.getApassword()));
			adminService.update(admin);
			map.put("hasError", false);
			map.put("error", "修改成功！");
		}
		ResponseUtil.write(response, JSONObject.fromObject(map));
	}
	@PostMapping("/delAdm.shtml")
	public void delAdm(String[] ids, HttpServletResponse response)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Admin admin = null;
		try {
			for (String id : ids) {
				admin = adminService.findById(Integer.valueOf(id));
				if (admin != null) {
					adminService.del(admin.getAid());
				}
			}
			map.put("hasError", false);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			map.put("hasError", true);
			map.put("error", "error");
			throw new RuntimeException();
		} finally {
			ResponseUtil.write(response, JSONObject.fromObject(map));
		}
	}
}
