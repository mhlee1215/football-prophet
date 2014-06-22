package com.respace.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.JSCorp.wp.domain.FPAppInfo;
import com.respace.dao.FPAppInfoDaoImpl;




@Service
public class FPAppInfoServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private FPAppInfoDaoImpl		appInfoDao;
	
	boolean isEncrypt = true;

	public List<FPAppInfo> readAppInfoList(FPAppInfo appInfo) {
		return appInfoDao.readAppInfoList(appInfo);
	}

	public int createAppInfo(FPAppInfo appInfo) throws Exception {
		return appInfoDao.createAppInfo(appInfo);
		
	}

	public FPAppInfo readAppInfo(FPAppInfo appInfo) throws Exception {
		return appInfoDao.readAppInfo(appInfo);
	}
	
	public int updateAppInfo(FPAppInfo appInfo) {
		return appInfoDao.updateAppInfo(appInfo);
	}

	public int deleteAppInfo(FPAppInfo appInfo) {
		return appInfoDao.deleteAppInfo(appInfo);
	}
	
	public int countAppInfo(FPAppInfo appInfo){
		return appInfoDao.countAppInfo(appInfo);
	}
}
