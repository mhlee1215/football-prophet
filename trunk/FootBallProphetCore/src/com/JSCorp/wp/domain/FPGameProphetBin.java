package com.JSCorp.wp.domain;

import java.util.List;

public class FPGameProphetBin {
	List<FPGameProphet> GameProphets;

	public List<FPGameProphet> getGameProphets() {
		return GameProphets;
	}

	public void setGameProphets(List<FPGameProphet> gameProphets) {
		GameProphets = gameProphets;
	}

	@Override
	public String toString() {
		return "{\"GameProphets\":\"" + GameProphets + "\"}";
	}
	
	
}
