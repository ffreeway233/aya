package com.cn;

@SuppressWarnings("serial")
public class CacheConfModel implements java.io.Serializable {
	/**
	 * 
	 */
	private long beginTime;
	private boolean isForever = false;
	private int durableTime;

	public long getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(long beginTime) {
		this.beginTime = beginTime;
	}

	public boolean isForever() {
		return isForever;
	}

	public void setForever(boolean isForever) {
		this.isForever = isForever;
	}

	public int getDurableTime() {
		return durableTime;
	}

	public void setDurableTime(int durableTime) {
		this.durableTime = durableTime;
	}
}