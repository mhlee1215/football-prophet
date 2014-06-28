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
	
	
	String month = "";
	String day = "";
	String time = "";
	String reference_month = "";
	String reference_day = "";
	String reference_time = "";
	
	int prophet_home_win = 0;
	int prophet_away_win = 0;
	int prophet_draw = 0;
	int prophet_finished = 0;
	

	private int query_start = 0;
	private int query_number = 0;

	String match_finished = "";
	
	int home_team_score = 0;
	int away_team_score = 0;
	
	public boolean isHomeTeamWin(){
		if( home_team_score > away_team_score)
			return true;
		else 
			return false;
	}
	
	public boolean isAwayTeamWin(){
		if( home_team_score < away_team_score)
			return true;
		else 
			return false;
	}

	
	public String getReference_month() {
		return reference_month;
	}

	public void setReference_month(String reference_month) {
		this.reference_month = reference_month;
	}

	public String getReference_day() {
		return reference_day;
	}

	public void setReference_day(String reference_day) {
		this.reference_day = reference_day;
	}

	public int getHome_team_score() {
		return home_team_score;
	}

	public void setHome_team_score(int home_team_score) {
		this.home_team_score = home_team_score;
	}

	public int getAway_team_score() {
		return away_team_score;
	}

	public void setAway_team_score(int away_team_score) {
		this.away_team_score = away_team_score;
	}

	public String getMatch_finished() {
		return match_finished;
	}

	public void setMatch_finished(String match_finished) {
		this.match_finished = match_finished;
	}

	public void setProphet_finished(int prophet_finished) {
		this.prophet_finished = prophet_finished;
	}

	public int getProphet_finished() {
		return prophet_finished;
	}

	public int getProphet_home_win() {
		return prophet_home_win;
	}

	public void setProphet_home_win(int prophet_home_win) {
		this.prophet_home_win = prophet_home_win;
		
		if(this.prophet_home_win == 1){
			this.prophet_away_win = 0;
			this.prophet_draw = 0;
		}
		
		prophet_finished = 1;
	}

	public int getProphet_away_win() {
		return prophet_away_win;
	}

	public void setProphet_away_win(int prophet_away_win) {
		this.prophet_away_win = prophet_away_win;
		
		if(this.prophet_away_win == 1){
			this.prophet_home_win = 0;
			this.prophet_draw = 0;
		}
		
		prophet_finished = 1;
	}

	public int getProphet_draw() {
		return prophet_draw;
	}

	public void setProphet_draw(int prophet_draw) {
		this.prophet_draw = prophet_draw;
		
		if(this.prophet_draw == 1){
			this.prophet_home_win = 0;
			this.prophet_away_win = 0;
		}
		
		prophet_finished = 1;
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
				+ "\",\"month\":\"" + month + "\",\"day\":\"" + day
				+ "\",\"time\":\"" + time + "\",\"reference_month\":\""
				+ reference_month + "\",\"reference_day\":\"" + reference_day
				+ "\",\"reference_time\":\"" + reference_time
				+ "\",\"prophet_home_win\":\"" + prophet_home_win
				+ "\",\"prophet_away_win\":\"" + prophet_away_win
				+ "\",\"prophet_draw\":\"" + prophet_draw
				+ "\",\"prophet_finished\":\"" + prophet_finished
				+ "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number
				+ "\",\"match_finished\":\"" + match_finished
				+ "\",\"home_team_score\":\"" + home_team_score
				+ "\",\"away_team_score\":\"" + away_team_score + "\"}";
	}
}
