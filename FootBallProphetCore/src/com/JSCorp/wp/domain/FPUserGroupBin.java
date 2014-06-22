package com.JSCorp.wp.domain;

import java.util.List;

public class FPUserGroupBin {
	List<FPUserGroup> UserGroups;

	public List<FPUserGroup> getUserGroups() {
		return UserGroups;
	}

	public void setUserGroups(List<FPUserGroup> userGroups) {
		UserGroups = userGroups;
	}

	@Override
	public String toString() {
		return "{\"UserGroups\":\"" + UserGroups + "\"}";
	}
}
