package com.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.validator.AddGroup;
import com.validator.UpdGroup;

public class Category {
	private Long cid;
	@NotBlank(groups={AddGroup.class,UpdGroup.class},message="分类名称不能为空")
	private String cname;
	private String ctime;
	private Integer cdelflag;
	private String caddname;
	
	public String getCaddname() {
		return caddname;
	}
	public void setCaddname(String caddname) {
		this.caddname = caddname;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCtime() {
		return ctime;
	}
	public void setCtime(String ctime) {
		this.ctime = ctime;
	}
	public Integer getCdelflag() {
		return cdelflag;
	}
	public void setCdelflag(Integer cdelflag) {
		this.cdelflag = cdelflag;
	}
	
}
