package com.JSCorp.wp.domain;

import java.util.List;

public class FPGameResultBin {
	List<FPGameResult> GameResults;

	public List<FPGameResult> getGameResults() {
		return GameResults;
	}

	public void setGameResults(List<FPGameResult> gameResults) {
		GameResults = gameResults;
	}

	@Override
	public String toString() {
		return "{\"GameResults\":\"" + GameResults + "\"}";
	}
}
