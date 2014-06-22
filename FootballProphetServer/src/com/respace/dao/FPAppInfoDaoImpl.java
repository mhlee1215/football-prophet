package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPAppInfo;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class FPAppInfoDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPAppInfo> readAppInfoList(FPAppInfo appInfo) {	
		List<FPAppInfo> array = getSqlMapClientTemplate().queryForList("AppInfoSql.readAppInfoList", appInfo);
		return array;
	}

	public FPAppInfo readAppInfo(FPAppInfo appInfo) {
		FPAppInfo result = (FPAppInfo)getSqlMapClientTemplate().queryForObject("AppInfoSql.readAppInfo", appInfo);
		return result;
	}


	public int createAppInfo(FPAppInfo appInfo) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("AppInfoSql.createAppInfo", appInfo);
		return rt_id;
	}
	

	public int deleteAppInfo(FPAppInfo appInfo) {
		return getSqlMapClientTemplate().delete("AppInfoSql.deleteAppInfo", appInfo);		
	}

	public int updateAppInfo(FPAppInfo appInfo) {
		return getSqlMapClientTemplate().update("AppInfoSql.updateAppInfo", appInfo);
	}
	
	public int countAppInfo(FPAppInfo appInfo) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("AppInfoSql.countAppInfo", appInfo);
		return count;
	}

}
