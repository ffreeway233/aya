/**
 * 
 */
package com.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Admin;
import com.entity.Substance;
import com.service.SubstanceService;
import com.util.DateUtil;
import com.validator.AddGroup;
import com.validator.UpdGroup;

/**
 * Title: menghanguoji<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017 <br>
 * Create DateTime: 2017-11-13 上午11:01:17 <br>
 * 
 * @author freeway
 */
@RestController
@RequestMapping("/substance")
public class SubstanceController extends BaseController {
	@Autowired
	private SubstanceService substanceService;

	@RequestMapping("/loadSubstance.shtml")
	public Map<String, Object> loadSubstance(
			@RequestParam(required = true, defaultValue = "0") Integer pageIndex,
			@RequestParam(required = true, defaultValue = "15") Integer limit,Long cid,String sname,Integer tid) {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageIndex * limit);
		map.put("limit", limit);
		try {
			map.put("sdelflag", 0);
			
			if(cid != null){
				map.put("cid", cid);
			}
			if(tid != null){
				map.put("tid", tid);
			}
			if(sname != null && !sname.equals("")){
				map.put("sname", sname);
			}
			remap.put("rows", substanceService.queryRowsByMap(map));
			remap.put("results", substanceService.queryNumberByMap(map));
			remap.put("hasError", false);
		} catch (Exception e) {
			e.printStackTrace();
			remap.put("rows", "");
			remap.put("results", 0);
			remap.put("hasError", true);
			remap.put("error", "error");
		}
		return remap;
	}

	@PostMapping("/addSubstance.shtml")
	public Map<String, Object> addSubstance(HttpSession hs,
			@Validated(value = AddGroup.class) Substance substance,
			BindingResult bindingResult) {
		Map<String, Object> remap = new HashMap<String, Object>();
		try {
			if (bindingResult.hasErrors()) {
				remap.put("hasError", true);
				remap.put("error", bindingResult.getAllErrors().get(0)
						.getDefaultMessage());
			} else {
				Admin admin =(Admin) hs.getAttribute("adminLogin");
				substance.setSaddname(admin.getAname());
				substance.setAid(admin.getAid());
				substance.setStime(DateUtil.getCurrentDateStr());
				substanceService.save(substance);
				remap.put("hasError", false);
				remap.put("error", "添加成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			remap.put("hasError", true);
			remap.put("error", "error");
		}
		return remap;
	}

	@PostMapping("/updSubstance.shtml")
	public Map<String, Object> updSubstance(HttpSession hs,
			@Validated(value = UpdGroup.class) Substance substance,
			BindingResult bindingResult) {
		Map<String, Object> remap = new HashMap<String, Object>();
		try {
			if (bindingResult.hasErrors()) {
				remap.put("hasError", true);
				remap.put("error", bindingResult.getAllErrors().get(0)
						.getDefaultMessage());
			} else {
				Admin admin =(Admin) hs.getAttribute("adminLogin");
				substance.setSaddname(admin.getAname());
				substance.setAid(admin.getAid());
				substance.setStime(DateUtil.getCurrentDateStr());
				substanceService.update(substance);
				remap.put("hasError", false);
				remap.put("error", "修改成功");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			remap.put("hasError", true);
			remap.put("error", "error");
		}
		return remap;
	}

	@PostMapping("/delSubstance.shtml")
	public Map<String, Object> delSubstance(String ids) {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, String[]> map = new HashMap<String, String[]>();
		try {
			String[] i = ids.split(",");
			map.put("ids", i);
			substanceService.del(map);
			remap.put("hasError", false);
			remap.put("error", "成功");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			remap.put("hasError", true);
			remap.put("error", "error");
		}
		return remap;
	}
	@PostMapping("/findSubstance.shtml")
	public Map<String, Object> findSubstance(Long sid){
		Map<String, Object> remap = new HashMap<>();
		try {
			remap.put("hasError", false);
			remap.put("object", substanceService.findById(sid));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			remap.put("hasError", true);
			remap.put("error", "error");
		}
		return remap;
	}
}
