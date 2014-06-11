package com.JSCorp.wp.domain;

public class FPGameMatchSchedule {
	int id;
	String type;
	int home_team_id;
	int away_team_id;
	String city;
	String time;
	String reference_time;

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHome_team_id() {
		return home_team_id;
	}

	public void setHome_team_id(int home_team_id) {
		this.home_team_id = home_team_id;
	}

	public int getAway_team_id() {
		return away_team_id;
	}

	public void setAway_team_id(int away_team_id) {
		this.away_team_id = away_team_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getReference_time() {
		return reference_time;
	}

	public void setReference_time(String reference_time) {
		this.reference_time = reference_time;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"type\":\"" + type
				+ "\",\"home_team_id\":\"" + home_team_id
				+ "\",\"away_team_id\":\"" + away_team_id + "\",\"city\":\""
				+ city + "\",\"time\":\"" + time + "\",\"reference_time\":\""
				+ reference_time + "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number + "\"}";
	}
}
