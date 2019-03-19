/**
 * 
 */
package com.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.entity.Admin;

/**
 * Title: newyangguang<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-3-3 上午11:29:08 <br>
 * @author freeway
 */
public interface AdminDao {

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
	 * @param admnid
	 * @return
	 */
	Admin findById(int admnid);

	/**
	 * @param map
	 * @return
	 */
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
