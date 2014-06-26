package com.JSCorp.wp.domain;

public class FPTournamentSchedule {
	int fromMatchId;
	int winnerGoesMatchId;
	boolean isWinnerGoesHomePosition;
	int loserGoesMatchId;
	boolean isLoserGoesHomePosition;
	
	public FPTournamentSchedule(int fromMatchId, int winnerGoesMatchId, boolean isWinnerGoesHomePosition){
		this.fromMatchId = fromMatchId;
		this.winnerGoesMatchId = winnerGoesMatchId;
		this.isWinnerGoesHomePosition = isWinnerGoesHomePosition;
		this.loserGoesMatchId = 0;
		this.isLoserGoesHomePosition = false;
	}
	
	public FPTournamentSchedule(int fromMatchId, int winnerGoesMatchId, boolean isWinnerGoesHomePosition, int loserGoesMatchId, boolean isLoserGoesHomePosition){
		this.fromMatchId = fromMatchId;
		this.winnerGoesMatchId = winnerGoesMatchId;
		this.isWinnerGoesHomePosition = isWinnerGoesHomePosition;
		this.loserGoesMatchId = loserGoesMatchId;
		this.isLoserGoesHomePosition = isLoserGoesHomePosition;
	}

	public int getFromMatchId() {
		return fromMatchId;
	}

	public void setFromMatchId(int fromMatchId) {
		this.fromMatchId = fromMatchId;
	}

	public int getWinnerGoesMatchId() {
		return winnerGoesMatchId;
	}

	public void setWinnerGoesMatchId(int winnerGoesMatchId) {
		this.winnerGoesMatchId = winnerGoesMatchId;
	}

	public boolean isWinnerGoesHomePosition() {
		return isWinnerGoesHomePosition;
	}

	public void setWinnerGoesHomePosition(boolean isWinnerGoesHomePosition) {
		this.isWinnerGoesHomePosition = isWinnerGoesHomePosition;
	}

	public int getLoserGoesMatchId() {
		return loserGoesMatchId;
	}

	public void setLoserGoesMatchId(int loserGoesMatchId) {
		this.loserGoesMatchId = loserGoesMatchId;
	}

	public boolean isLoserGoesHomePosition() {
		return isLoserGoesHomePosition;
	}

	public void setLoserGoesHomePosition(boolean isLoserGoesHomePosition) {
		this.isLoserGoesHomePosition = isLoserGoesHomePosition;
	}

	@Override
	public String toString() {
		return "{\"fromMatchId\":\"" + fromMatchId
				+ "\", \"winnerGoesMatchId\":\"" + winnerGoesMatchId
				+ "\", \"isWinnerGoesHomePosition\":\""
				+ isWinnerGoesHomePosition + "\", \"loserGoesMatchId\":\""
				+ loserGoesMatchId + "\", \"isLoserGoesHomePosition\":\""
				+ isLoserGoesHomePosition + "\"}";
	}
}
