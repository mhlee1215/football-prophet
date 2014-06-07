package com.respace.domain;

public class RS_Article {
	private int id = 0;
	private String title = "";
	private String author = "";
	private String email = "";
	private String website = "";
	private String content = "";
	private String category = "";
	private String isvalid = "";
	private String modifieddate = "";
	private String registereddate = "";
	
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
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIsvalid() {
		return isvalid;
	}
	public void setIsvalid(String isvalid) {
		this.isvalid = isvalid;
	}
	public String getModifieddate() {
		return modifieddate;
	}
	public void setModifieddate(String modifieddate) {
		this.modifieddate = modifieddate;
	}
	public String getRegistereddate() {
		return registereddate;
	}
	public void setRegistereddate(String registereddate) {
		this.registereddate = registereddate;
	}
	@Override
	public String toString() {
		return "RS_Article [id=" + id + ", title=" + title + ", author="
				+ author + ", email=" + email + ", website=" + website
				+ ", content=" + content + ", category=" + category
				+ ", isvalid=" + isvalid + ", modifieddate=" + modifieddate
				+ ", registereddate=" + registereddate + ", query_start="
				+ query_start + ", query_number=" + query_number + "]";
	}
}
