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

import com.dao.SubstanceDao;
import com.entity.Substance;
import com.service.SubstanceService;

/**
 * Title: menghanguoji<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-11-13 上午11:03:32 <br>
 * @author freeway
 */
@Service("substanceService")
public class SubstanceServiceImpl implements SubstanceService {
	@Autowired
	private SubstanceDao substanceDao;
	
	@Override
	public Long queryNumberByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return substanceDao.queryNumberByMap(map);
	}

	@Override
	public List<Substance> queryRowsByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return substanceDao.queryRowsByMap(map);
	}

	@Override
	public List<Substance> getByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return substanceDao.getByMap(map);
	}

	@Override
	public Substance findById(Long id) {
		// TODO Auto-generated method stub
		return substanceDao.findById(id);
	}

	@Override
	public Substance findByMap(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return substanceDao.findByMap(map);
	}

	@Override
	public void save(Substance t) {
		// TODO Auto-generated method stub
		substanceDao.save(t);
	}

	@Override
	public void del(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		substanceDao.del(map);
	}

	@Override
	public void update(Substance t) {
		// TODO Auto-generated method stub
		substanceDao.update(t);
	}
}
