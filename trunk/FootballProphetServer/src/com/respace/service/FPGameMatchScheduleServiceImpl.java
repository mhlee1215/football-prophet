package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.FPGameMatchScheduleDaoImpl;
import com.respace.domain.FPGameMatchSchedule;



@Service
public class FPGameMatchScheduleServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPGameMatchScheduleDaoImpl		gameMatchScheduleDao;
	
	boolean isEncrypt = true;

	public List<FPGameMatchSchedule> readGameMatchScheduleList(FPGameMatchSchedule gameMatchSchedule) {
		return gameMatchScheduleDao.readGameMatchScheduleList(gameMatchSchedule);
	}

	public int createGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) throws Exception {
		return gameMatchScheduleDao.createGameMatchSchedule(gameMatchSchedule);
		
	}

	public FPGameMatchSchedule readGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) throws Exception {
		return gameMatchScheduleDao.readGameMatchSchedule(gameMatchSchedule);
	}
	
	public int updateGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		return gameMatchScheduleDao.updateGameMatchSchedule(gameMatchSchedule);
	}

	public int deleteGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		return gameMatchScheduleDao.deleteGameMatchSchedule(gameMatchSchedule);
	}
	
	public int countGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule){
		return gameMatchScheduleDao.countGameMatchSchedule(gameMatchSchedule);
	}
}
