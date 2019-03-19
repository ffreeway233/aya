/**
 * 
 */
package com.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dao.CategoryDao;
import com.entity.Category;
import com.service.CategoryService;

/**
 * Title: menghanguoji<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-11-13 上午11:03:32 <br>
 * @author freeway
 */
@Service("categoryService")
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryDao categoryDao;
	
	@Override
	public Long queryNumberByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return categoryDao.queryNumberByMap(map);
	}

	@Override
	public List<Category> queryRowsByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return categoryDao.queryRowsByMap(map);
	}

	@Override
	public List<Category> getByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return categoryDao.getByMap(map);
	}

	@Override
	public Category findById(Long id) {
		// TODO Auto-generated method stub
		return categoryDao.findById(id);
	}

	@Override
	public Category findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return categoryDao.findByMap(map);
	}

	@Override
	public void save(Category t) {
		// TODO Auto-generated method stub
		categoryDao.save(t);
	}

	@Override
	public void del(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		categoryDao.del(map);
	}

	@Override
	public void update(Category t) {
		// TODO Auto-generated method stub
		categoryDao.update(t);
	}
}
