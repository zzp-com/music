package com.music.bean;

import java.io.Serializable;

public class AdminBean implements Serializable {
	private int adminID;
	private String adminName;
	private String adminPwd;
	private String adminType;
	private String lastLongTime;
	public int getAdminID() {
		return adminID;
	}
	public void setAdminID(int adminID) {
		this.adminID = adminID;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getAdminPwd() {
		return adminPwd;
	}
	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}
	public String getAdminType() {
		return adminType;
	}
	public void setAdminType(String adminType) {
		this.adminType = adminType;
	}
	public String getLastLongTime() {
		return lastLongTime;
	}
	public void setLastLongTime(String lastLongTime) {
		this.lastLongTime = lastLongTime;
	}
	
	
}
