package com.respace.domain;

public class FPUser {
	int id;
	String device_id;
	int group_id;
	String nickname;
	String tag;
	int score_dynamic;
	int score_static;
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

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getScore_dynamic() {
		return score_dynamic;
	}

	public void setScore_dynamic(int score_dynamic) {
		this.score_dynamic = score_dynamic;
	}

	public int getScore_static() {
		return score_static;
	}

	public void setScore_static(int score_static) {
		this.score_static = score_static;
	}

	@Override
	public String toString() {
		return "{\"FPUser\":[\"id\":\"" + id + "\", device_id\":\"" + device_id
				+ "\", group_id\":\"" + group_id + "\", nickname\":\""
				+ nickname + "\", tag\":\"" + tag + "\", score_dynamic\":\""
				+ score_dynamic + "\", score_static\":\"" + score_static
				+ "\", query_start\":\"" + query_start
				+ "\", query_number\":\"" + query_number + "]}";
	}
}
