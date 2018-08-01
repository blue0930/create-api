package ren.xiangmu.creatorapi.beans;

import java.sql.Timestamp;

import ren.xiangmu.creatorapi.utils.DateUtils;

public class Token {
	
	private String key;
	private Timestamp expiredTime;
	private String type;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public Timestamp getExpiredTime() {
		return expiredTime;
	}
	public void setExpiredTime(Timestamp expiredTime) {
		this.expiredTime = expiredTime;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isValid() {
		Timestamp now = DateUtils.now();
		return now.before(this.expiredTime);
	}
}
