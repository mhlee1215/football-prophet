package com.respace.domain;

public class RS_Project {
	private int id = 0;
	private int id_contact = 0;
	private String 	title 	= "";
	private String 	description 	= "";
	private String 	host_name 	= "";
	private String code_category = "";
	private String thumbnail_url;
	
	private int query_start = 0;
	private int query_number = 0;
	
	
	public int getQuery_start() {
		return query_start;
	}
	public void setQuery_start(int query_start) {
		this.query_start = query_start;
	}
	public int getQuery_number() {
		return query_number;
	}
	public void setQuery_number(int query_number) {
		this.query_number = query_number;
	}
	public String getThumbnail_url() {
		return thumbnail_url;
	}
	public void setThumbnail_url(String thumbnail_url) {
		this.thumbnail_url = thumbnail_url;
	}
	public String getCode_category() {
		return code_category;
	}
	public void setCode_category(String code_category) {
		this.code_category = code_category;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getHost_name() {
		return host_name;
	}
	public void setHost_name(String host_name) {
		this.host_name = host_name;
	}
	public int getId_contact() {
		return id_contact;
	}
	public void setId_contact(int id_contact) {
		this.id_contact = id_contact;
	}
	@Override
	public String toString() {
		return "RS_Project [id=" + id + ", id_contact=" + id_contact
				+ ", title=" + title + ", description=" + description
				+ ", host_name=" + host_name + ", code_category="
				+ code_category + ", thumbnail_url=" + thumbnail_url
				+ ", query_start=" + query_start + ", query_number="
				+ query_number + "]";
	}
}
