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

import com.dao.AdminDao;
import com.entity.Admin;
import com.service.AdminService;

/**
 * Title: gsguoshui<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-8-7 下午1:28:30 <br>
 * @author freeway
 */
@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	
	@Override
	public Admin login(Admin admin) {
		return adminDao.login(admin);
	}


	@Override
	public void update(Admin admin2) {
		adminDao.update(admin2);
	}


	@Override
	public Admin findById(int aid) {
		return adminDao.findById(aid);
	}

	@Override
	public List<Admin> queryRowsByMap(Map<String, Object> map) {
		return adminDao.queryRowsByMap(map);
	}


	@Override
	public Long queryNumberByMap(Map<String, Object> map) {
		return adminDao.queryNumberByMap(map);
	}


	@Override
	public int qureyAdminByAname(String aname) {
		return adminDao.qureyAdminByAname(aname);
	}


	@Override
	public void save(Admin admin) {
		adminDao.save(admin);
	}
	
	@Override
	public void del(int aid) {
		adminDao.del(aid);
	}
	



}
