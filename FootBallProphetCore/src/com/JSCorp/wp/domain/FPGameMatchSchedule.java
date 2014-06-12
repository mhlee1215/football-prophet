package com.JSCorp.wp.domain;

public class FPGameMatchSchedule {
	int id;
	String type = "";
	int home_team_id;
	String home_team_name = "";
	int away_team_id;
	String away_team_name = "";
	String gameGroup = "";
	String city = "";
	String time = "";
	
	String month = "";
	String day = "";
	String reference_time = "";
	
	String prophet_home_win = "";
	String prophet_away_win = "";
	String prophet_draw = "";
	

	private int query_start = 0;
	private int query_number = 0;

	
	public String getProphet_home_win() {
		return prophet_home_win;
	}

	public void setProphet_home_win(String prophet_home_win) {
		this.prophet_home_win = prophet_home_win;
	}

	public String getProphet_away_win() {
		return prophet_away_win;
	}

	public void setProphet_away_win(String prophet_away_win) {
		this.prophet_away_win = prophet_away_win;
	}

	public String getProphet_draw() {
		return prophet_draw;
	}

	public void setProphet_draw(String prophet_draw) {
		this.prophet_draw = prophet_draw;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getGameGroup() {
		return gameGroup;
	}

	public void setGameGroup(String gameGroup) {
		this.gameGroup = gameGroup;
	}

	public String getHome_team_name() {
		return home_team_name;
	}

	public void setHome_team_name(String home_team_name) {
		this.home_team_name = home_team_name;
	}

	public String getAway_team_name() {
		return away_team_name;
	}

	public void setAway_team_name(String away_team_name) {
		this.away_team_name = away_team_name;
	}

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
				+ "\",\"home_team_name\":\"" + home_team_name
				+ "\",\"away_team_id\":\"" + away_team_id
				+ "\",\"away_team_name\":\"" + away_team_name
				+ "\",\"gameGroup\":\"" + gameGroup + "\",\"city\":\"" + city
				+ "\",\"time\":\"" + time + "\",\"month\":\"" + month
				+ "\",\"day\":\"" + day + "\",\"reference_time\":\""
				+ reference_time + "\",\"prophet_home_win\":\""
				+ prophet_home_win + "\",\"prophet_away_win\":\""
				+ prophet_away_win + "\",\"prophet_draw\":\"" + prophet_draw
				+ "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number + "\"}";
	}
}
