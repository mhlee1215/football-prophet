package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JSCorp.wp.domain.FPGameResult;
import com.respace.dao.FPGameResultDaoImpl;




@Service
public class FPGameResultServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPGameResultDaoImpl		gameResultDao;
	
	boolean isEncrypt = true; 

	public List<FPGameResult> readGameResultList(FPGameResult gameResult) {
		return gameResultDao.readGameResultList(gameResult);
	}

	public int createGameResult(FPGameResult gameResult) throws Exception {
		return gameResultDao.createGameResult(gameResult);
		
	}

	public FPGameResult readGameResult(FPGameResult gameResult) throws Exception {
		return gameResultDao.readGameResult(gameResult);
	}
	
	public int updateGameResult(FPGameResult gameResult) {
		return gameResultDao.updateGameResult(gameResult);
	}

	public int deleteGameResult(FPGameResult gameResult) {
		return gameResultDao.deleteGameResult(gameResult);
	}
	
	public int countGameResult(FPGameResult gameResult){
		return gameResultDao.countGameResult(gameResult);
	}
}
