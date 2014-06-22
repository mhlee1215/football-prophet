package com.JSCorp.wp.domain;

import java.util.List;

public class FPGameTeamBin {
	List<FPGameTeam> GameTeams;

	public List<FPGameTeam> getGameTeams() {
		return GameTeams;
	}

	public void setGameTeams(List<FPGameTeam> gameTeams) {
		GameTeams = gameTeams;
	}

	@Override
	public String toString() {
		return "{\"GameTeams\":\"" + GameTeams + "\"}";
	}
}
