package com.entity;

import org.hibernate.validator.constraints.NotBlank;

import com.validator.AddGroup;
import com.validator.UpdGroup;

public class Substance {
	private Long sid;
	@NotBlank(groups={AddGroup.class,UpdGroup.class},message="名称不能为空")
	private String sname;
	private String spath;
	private String scontent;
	private String stime;
	private Integer sdelflag;
	private Integer aid;
	private String cids;
	private Integer tid;
	private String saddname;
	
	public String getSaddname() {
		return saddname;
	}
	public void setSaddname(String saddname) {
		this.saddname = saddname;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getCids() {
		return cids;
	}
	public void setCids(String cids) {
		this.cids = cids;
	}
	public Long getSid() {
		return sid;
	}
	public void setSid(Long sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSpath() {
		return spath;
	}
	public void setSpath(String spath) {
		this.spath = spath;
	}
	public String getScontent() {
		return scontent;
	}
	public void setScontent(String scontent) {
		this.scontent = scontent;
	}
	public String getStime() {
		return stime;
	}
	public void setStime(String stime) {
		this.stime = stime;
	}
	public Integer getSdelflag() {
		return sdelflag;
	}
	public void setSdelflag(Integer sdelflag) {
		this.sdelflag = sdelflag;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	
	
}
