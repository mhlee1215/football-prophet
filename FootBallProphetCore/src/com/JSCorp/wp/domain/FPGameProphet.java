package com.JSCorp.wp.domain;

public class FPGameProphet {
	
	public static String FLAG_TRUE = "1";
	public static String FLAG_FALSE = "0";
	
	int id = 0;
	int user_id = 0;
	int match_id = 0;
	String prophet_type = "";
	String home_team_win = "";
	String away_team_win = "";
	String draw = "";
	String prophet_result = "";
	String comment = "";
	
	private int query_start = 0;
	private int query_number = 0;

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
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

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getMatch_id() {
		return match_id;
	}

	public void setMatch_id(int match_id) {
		this.match_id = match_id;
	}

	public String getProphet_type() {
		return prophet_type;
	}

	public void setProphet_type(String prophet_type) {
		this.prophet_type = prophet_type;
	}

	public String getHome_team_win() {
		return home_team_win;
	}

	public void setHome_team_win(String home_team_win) {
		if(FLAG_TRUE.equals(home_team_win)){
			this.home_team_win = FLAG_TRUE;
			this.away_team_win = FLAG_FALSE;
			this.draw = FLAG_FALSE;
		}else{
			this.home_team_win = FLAG_FALSE;
		}
	}
	
	public void setHome_team_win(){
		this.home_team_win = "1";
		this.away_team_win = "0";
		this.draw = "0";
	}
	
	public void setAway_team_win(){
		this.home_team_win = "0";
		this.away_team_win = "1";
		this.draw = "0";
	}
	
	public void setDraw(){
		this.home_team_win = "0";
		this.away_team_win = "0";
		this.draw = "1";
	}

	public String getAway_team_win() {
		return away_team_win;
	}

	public void setAway_team_win(String away_team_win) {
		if(FLAG_TRUE.equals(away_team_win)){
			this.home_team_win = FLAG_FALSE;
			this.away_team_win = FLAG_TRUE;
			this.draw = FLAG_FALSE;
		}else{
			this.away_team_win = FLAG_FALSE;
		}
	}

	public String getDraw() {
		return draw;
	}

	public void setDraw(String draw) {		
		if(FLAG_TRUE.equals(draw)){
			this.home_team_win = FLAG_FALSE;
			this.away_team_win = FLAG_FALSE;
			this.draw = FLAG_TRUE;
		}else{
			this.draw = FLAG_FALSE;
		}
	}

	public String getProphet_result() {
		return prophet_result;
	}

	public void setProphet_result(String prophet_result) {
		this.prophet_result = prophet_result;
	}

	@Override
	//{"${member.name}":"${member.value}","${otherMembers}"}
	public String toString() {
		return "{\"id\":\"" + id + "\",\"user_id\":\"" + user_id
				+ "\",\"match_id\":\"" + match_id + "\",\"prophet_type\":\""
				+ prophet_type + "\",\"home_team_win\":\"" + home_team_win
				+ "\",\"away_team_win\":\"" + away_team_win + "\",\"draw\":\""
				+ draw + "\",\"prophet_result\":\"" + prophet_result
				+ "\",\"comment\":\"" + comment + "\",\"query_start\":\""
				+ query_start + "\",\"query_number\":\"" + query_number + "\"}";
	}

	//?${member.name()}=${member.value}&${otherMembers}
	public String toStringSealize() {
		return "?id=" + id + "&user_id=" + user_id + "&match_id=" + match_id
				+ "&prophet_type=" + prophet_type + "&home_team_win="
				+ home_team_win + "&away_team_win=" + away_team_win + "&draw="
				+ draw + "&prophet_result=" + prophet_result + "&comment="
				+ comment + "&query_start=" + query_start + "&query_number="
				+ query_number;
	}
	
	
}
