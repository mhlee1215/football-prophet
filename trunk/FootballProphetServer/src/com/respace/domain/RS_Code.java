package com.respace.domain;

public class RS_Code {
	private int id = 0;
	private String code = "";
	private String name = "";
	private String category = "";
	private int seq_no = 0;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(int seq_no) {
		this.seq_no = seq_no;
	}
	
	@Override
	public String toString() {
		return "RS_Code [id=" + id + ", code=" + code + ", name=" + name
				+ ", category=" + category + ", seq_no=" + seq_no
				+ ", query_start=" + query_start + ", query_number="
				+ query_number + "]";
	}
	
	
	
}
