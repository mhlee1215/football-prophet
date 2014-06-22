package com.JSCorp.wp.domain;

import java.util.List;

public class FPUserBin {
	List<FPUser> Users;

	public List<FPUser> getUsers() {
		return Users;
	}

	public void setUsers(List<FPUser> users) {
		Users = users;
	}

	@Override
	public String toString() {
		return "{\"Users\":\"" + Users + "\"}";
	}
}
