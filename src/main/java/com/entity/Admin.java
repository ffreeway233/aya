/**
 * 
 */
package com.entity;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.validator.AddGroup;
import com.validator.LoginGroup;
import com.validator.UpdGroup;

/**
 * Title: zjyidan<br>
 * Description: <br>
 * Copyright: Copyright (c) 2017    <br>
 * Create DateTime: 2017-8-22 下午1:35:30 <br>
 * @author freeway
 */
public class Admin {

	@NotNull(message="id不能为空",groups={UpdGroup.class})
	private Integer aid;
	@NotBlank(groups={UpdGroup.class,AddGroup.class,LoginGroup.class})
	private String aname;
	@NotBlank(groups={UpdGroup.class,AddGroup.class,LoginGroup.class})
	private String apassword;
	private String atoken;
	private String alogintime;
	private String ip;
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	public String getAtoken() {
		return atoken;
	}
	public void setAtoken(String atoken) {
		this.atoken = atoken;
	}
	public String getAlogintime() {
		return alogintime;
	}
	public void setAlogintime(String alogintime) {
		this.alogintime = alogintime;
	}
	
}
