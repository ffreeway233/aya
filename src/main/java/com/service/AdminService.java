/**
 * 
 */
package com.service;

import java.util.List;
import java.util.Map;

import com.entity.Admin;

/**
 * Title: gsguoshui<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-8-7 下午1:28:05 <br>
 * @author freeway
 */
public interface AdminService {

	/**
	 * @param admin
	 * @return
	 */
	Admin login(Admin admin);

	/**
	 * @param admin2
	 */
	void update(Admin admin2);

	/**
	 * @param aid
	 * @return
	 */
	Admin findById(int aid);

	List<Admin> queryRowsByMap(Map<String, Object> map);

	/**
	 * @param map
	 * @return
	 */
	Long queryNumberByMap(Map<String, Object> map);

	/**
	 * @param aname
	 * @return
	 */
	int qureyAdminByAname(String aname);

	/**
	 * @param admin
	 */
	void save(Admin admin);

	/**
	 * @param aid
	 */
	void del(int aid);
}
