package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JSCorp.wp.domain.FPGameTeam;
import com.respace.dao.FPGameTeamDaoImpl;




@Service
public class FPGameTeamServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPGameTeamDaoImpl		gameTeamDao;
	
	boolean isEncrypt = true;

	public List<FPGameTeam> readGameTeamList(FPGameTeam gameTeam) {
		return gameTeamDao.readGameTeamList(gameTeam);
	}

	public int createGameTeam(FPGameTeam gameTeam) throws Exception {
		return gameTeamDao.createGameTeam(gameTeam);
		
	}

	public FPGameTeam readGameTeam(FPGameTeam gameTeam) throws Exception {
		return gameTeamDao.readGameTeam(gameTeam);
	}
	
	public int updateGameTeam(FPGameTeam gameTeam) {
		return gameTeamDao.updateGameTeam(gameTeam);
	}

	public int deleteGameTeam(FPGameTeam gameTeam) {
		return gameTeamDao.deleteGameTeam(gameTeam);
	}
	
	public int countGameTeam(FPGameTeam gameTeam){
		return gameTeamDao.countGameTeam(gameTeam);
	}
}
