package com.service;

import java.util.List;
import java.util.Map;

public interface BaseService1<T> {

	Long queryNumberByMap(Map<String, Object> map);

	List<T> queryRowsByMap(Map<String, Object> map);

	List<T> getByMap(Map<String, Object> map);

	T findById(Long id);

	T findByMap(Map<String, Object> map);
	
	void save(T t);
	
	void del(Map<String, String[]> map);
	
	void update(T t);
}
