package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JSCorp.wp.domain.FPGameProphet;
import com.respace.dao.FPGameProphetDaoImpl;




@Service
public class FPGameProphetServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPGameProphetDaoImpl		gameProphetDao;
	
	boolean isEncrypt = true;

	public List<FPGameProphet> readGameProphetList(FPGameProphet gameProphet) {
		return gameProphetDao.readGameProphetList(gameProphet);
	}

	public int createGameProphet(FPGameProphet gameProphet) throws Exception {
		return gameProphetDao.createGameProphet(gameProphet);
		
	}

	public FPGameProphet readGameProphet(FPGameProphet gameProphet) throws Exception {
		return gameProphetDao.readGameProphet(gameProphet);
	}
	
	public int updateGameProphet(FPGameProphet gameProphet) {
		return gameProphetDao.updateGameProphet(gameProphet);
	}
	
	public int updateGameProphetResult(FPGameProphet gameProphet) {
		return gameProphetDao.updateGameProphetResult(gameProphet);
	}

	public int deleteGameProphet(FPGameProphet gameProphet) {
		return gameProphetDao.deleteGameProphet(gameProphet);
	}
	
	public int countGameProphet(FPGameProphet gameProphet){
		return gameProphetDao.countGameProphet(gameProphet);
	}
}
