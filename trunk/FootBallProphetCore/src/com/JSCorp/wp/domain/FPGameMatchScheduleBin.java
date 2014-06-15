package com.JSCorp.wp.domain;

import java.util.List;

public class FPGameMatchScheduleBin {
	List<FPGameMatchSchedule> GameMatchSchedules;

	public List<FPGameMatchSchedule> getGameMatchSchedules() {
		return GameMatchSchedules;
	}

	public void setGameMatchSchedules(List<FPGameMatchSchedule> gameMatchSchedules) {
		GameMatchSchedules = gameMatchSchedules;
	}

	@Override
	public String toString() {
		return "{\"GameMatchSchedules\":\"" + GameMatchSchedules + "\"}";
	}
	
	
}
