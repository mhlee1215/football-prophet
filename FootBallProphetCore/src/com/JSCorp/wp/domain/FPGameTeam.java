package com.JSCorp.wp.domain;

public class FPGameTeam {
	int id;
	String team_name = "";
	String team_name_kor = "";
	String game_group = "";
	
	int gameScorePoint;
	int goalScorePoint;
	
	
	private int query_start = 0;
	private int query_number = 0;

	
	public int getGameScorePoint() {
		return gameScorePoint;
	}

	public void setGameScorePoint(int gameScorePoint) {
		this.gameScorePoint = gameScorePoint;
	}

	public int getGoalScorePoint() {
		return goalScorePoint;
	}

	public void setGoalScorePoint(int goalScorePoint) {
		this.goalScorePoint = goalScorePoint;
	}

	public String getTeam_name_kor() {
		return team_name_kor;
	}

	public void setTeam_name_kor(String team_name_kor) {
		this.team_name_kor = team_name_kor;
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

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getGame_group() {
		return game_group;
	}

	public void setGame_group(String game_group) {
		this.game_group = game_group;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\", \"team_name\":\"" + team_name
				+ "\", \"team_name_kor\":\"" + team_name_kor
				+ "\", \"game_group\":\"" + game_group
				+ "\", \"gameScorePoint\":\"" + gameScorePoint
				+ "\", \"goalScorePoint\":\"" + goalScorePoint
				+ "\", \"query_start\":\"" + query_start
				+ "\", \"query_number\":\"" + query_number + "\"}";
	}
}
