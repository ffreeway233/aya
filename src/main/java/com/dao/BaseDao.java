/**
 * 
 */
package com.dao;

import java.util.List;
import java.util.Map;

/**
 * Title: menghanguoji<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-11-13 上午11:28:51 <br>
 * @author freeway
 */
public interface BaseDao<T> {

	Long queryNumberByMap(Map<String, Object> map);

	List<T> queryRowsByMap(Map<String, Object> map);

	List<T> getByMap(Map<String, Object> map);

	T findById(Long id);

	T findByMap(Map<String, Object> map);
	
	void save(T t);
	
	void del(Map<String, String[]> map);
	
	void update(T t);
}
