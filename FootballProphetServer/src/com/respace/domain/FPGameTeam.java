package com.respace.domain;

public class FPGameTeam {
	int id;
	String team_name;
	String game_group;
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
		return "{\"FPGameTeam\":[\"id\":\"" + id + "\", team_name\":\""
				+ team_name + "\", game_group\":\"" + game_group
				+ "\", query_start\":\"" + query_start
				+ "\", query_number\":\"" + query_number + "]}";
	}

}
