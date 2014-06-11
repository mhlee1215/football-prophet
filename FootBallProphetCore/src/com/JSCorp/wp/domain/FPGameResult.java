package com.JSCorp.wp.domain;

public class FPGameResult {
	int id;
	String match_id;
	String match_type; // '\'1\':group match\n\'2\':Round of 16\n\'3\':Quater
						// final\n\'4\':Semi final\n\'5\':final',
	int home_team_score;
	int away_team_score;
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

	public String getMatch_id() {
		return match_id;
	}

	public void setMatch_id(String match_id) {
		this.match_id = match_id;
	}

	public String getMatch_type() {
		return match_type;
	}

	public void setMatch_type(String match_type) {
		this.match_type = match_type;
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

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"match_id\":\"" + match_id
				+ "\",\"match_type\":\"" + match_type
				+ "\",\"home_team_score\":\"" + home_team_score
				+ "\",\"away_team_score\":\"" + away_team_score
				+ "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number + "\"}";
	}
}
