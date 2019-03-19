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
import com.entity.Category;
import com.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController extends BaseController {
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/loadCategory.shtml")
	public Map<String, Object> loadCategory(
			@RequestParam(required = true, defaultValue = "0") Integer pageIndex,
			@RequestParam(required = true, defaultValue = "15") Integer limit) {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", pageIndex * limit);
		map.put("limit", limit);
		try {
			map.put("cdelflag", 0);
			remap.put("rows", categoryService.queryRowsByMap(map));
			remap.put("results", categoryService.queryNumberByMap(map));
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

	@PostMapping("/addCategory.shtml")
	public Map<String, Object> addCategory(HttpSession hs,
			@Validated(value = AddGroup.class) Category category,
			BindingResult bindingResult) {
		Map<String, Object> remap = new HashMap<String, Object>();
		try {
			if (bindingResult.hasErrors()) {
				remap.put("hasError", true);
				remap.put("error", bindingResult.getAllErrors().get(0)
						.getDefaultMessage());
			} else {
				Admin admin =(Admin) hs.getAttribute("adminLogin");
				category.setCaddname(admin.getAname());
				category.setCtime(DateUtil.getCurrentDateStr());
				categoryService.save(category);
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

	@PostMapping("/updCategory.shtml")
	public Map<String, Object> updCategory(HttpSession hs,
			@Validated(value = UpdGroup.class) Category category,
			BindingResult bindingResult) {
		Map<String, Object> remap = new HashMap<String, Object>();
		try {
			if (bindingResult.hasErrors()) {
				remap.put("hasError", true);
				remap.put("error", bindingResult.getAllErrors().get(0)
						.getDefaultMessage());
			} else {
				Admin admin =(Admin) hs.getAttribute("adminLogin");
				category.setCaddname(admin.getAname());
				category.setCtime(DateUtil.getCurrentDateStr());
				categoryService.update(category);
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

	@PostMapping("/delCategory.shtml")
	public Map<String, Object> delCategory(String ids) {
		Map<String, Object> remap = new HashMap<String, Object>();
		Map<String, String[]> map = new HashMap<String, String[]>();
		try {
			String[] i = ids.split(",");
			map.put("ids", i);
			categoryService.del(map);
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
}
