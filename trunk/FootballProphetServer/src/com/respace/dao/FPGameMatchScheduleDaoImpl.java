package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class FPGameMatchScheduleDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPGameMatchSchedule> readGameMatchScheduleList(FPGameMatchSchedule gameMatchSchedule) {	
		List<FPGameMatchSchedule> array = getSqlMapClientTemplate().queryForList("GameMatchScheduleSql.readGameMatchScheduleList", gameMatchSchedule);
		return array;
	}

	public FPGameMatchSchedule readGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		FPGameMatchSchedule result = (FPGameMatchSchedule)getSqlMapClientTemplate().queryForObject("GameMatchScheduleSql.readGameMatchSchedule", gameMatchSchedule);
		return result;
	}


	public int createGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("GameMatchScheduleSql.createGameMatchSchedule", gameMatchSchedule);
		return rt_id;
	}
	

	public int deleteGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		return getSqlMapClientTemplate().delete("GameMatchScheduleSql.deleteGameMatchSchedule", gameMatchSchedule);		
	}

	public int updateGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		return getSqlMapClientTemplate().update("GameMatchScheduleSql.updateGameMatchSchedule", gameMatchSchedule);
	}
	
	public int countGameMatchSchedule(FPGameMatchSchedule gameMatchSchedule) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("GameMatchScheduleSql.countGameMatchSchedule", gameMatchSchedule);
		return count;
	}

}
