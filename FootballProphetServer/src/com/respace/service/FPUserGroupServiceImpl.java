package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JSCorp.wp.domain.FPUserGroup;
import com.respace.dao.FPUserGroupDaoImpl;




@Service
public class FPUserGroupServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPUserGroupDaoImpl		userGroupDao;
	
	boolean isEncrypt = true;

	public List<FPUserGroup> readUserGroupList(FPUserGroup userGroup) {
		return userGroupDao.readUserGroupList(userGroup);
	}

	public int createUserGroup(FPUserGroup userGroup) throws Exception {
		return userGroupDao.createUserGroup(userGroup);
		
	}

	public FPUserGroup readUserGroup(FPUserGroup userGroup) throws Exception {
		return userGroupDao.readUserGroup(userGroup);
	}
	
	public int updateUserGroup(FPUserGroup userGroup) {
		return userGroupDao.updateUserGroup(userGroup);
	}

	public int deleteUserGroup(FPUserGroup userGroup) {
		return userGroupDao.deleteUserGroup(userGroup);
	}
	
	public int countUserGroup(FPUserGroup userGroup){
		return userGroupDao.countUserGroup(userGroup);
	}
}
