package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.FPUserDaoImpl;
import com.respace.domain.FPUser;



@Service
public class FPUserServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPUserDaoImpl		userDao;
	
	boolean isEncrypt = true;

	public List<FPUser> readUserList(FPUser user) {
		return userDao.readUserList(user);
	}

	public int createUser(FPUser user) throws Exception {
		return userDao.createUser(user);
		
	}

	public FPUser readUser(FPUser user) throws Exception {
		return userDao.readUser(user);
	}
	
	public int updateUser(FPUser user) {
		return userDao.updateUser(user);
	}

	public int deleteUser(FPUser user) {
		return userDao.deleteUser(user);
	}
	
	public int countUser(FPUser user){
		return userDao.countUser(user);
	}
}
