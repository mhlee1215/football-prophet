package com.JSCorp.wp.domain;

public class FPAppInfo {
	int id;
	String app_name;
	String version_name;
	String version_code;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getApp_name() {
		return app_name;
	}
	public void setApp_name(String app_name) {
		this.app_name = app_name;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"app_name\":\"" + app_name
				+ "\", \"version_name\":\"" + version_name
				+ "\", \"version_code\":\"" + version_code + "\"}";
	}
	
}
