package com.JSCorp.wp.domain;

public class FPUserGroup {
	int id;
	String name = "";
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"name\":\"" + name
				+ "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number + "\"}";
	}
}
