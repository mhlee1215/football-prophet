package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPGameTeam;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class FPGameTeamDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPGameTeam> readGameTeamList(FPGameTeam gameTeam) {	
		List<FPGameTeam> array = getSqlMapClientTemplate().queryForList("GameTeamSql.readGameTeamList", gameTeam);
		return array;
	}

	public FPGameTeam readGameTeam(FPGameTeam gameTeam) {
		FPGameTeam result = (FPGameTeam)getSqlMapClientTemplate().queryForObject("GameTeamSql.readGameTeam", gameTeam);
		return result;
	}


	public int createGameTeam(FPGameTeam gameTeam) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("GameTeamSql.createGameTeam", gameTeam);
		return rt_id;
	}
	

	public int deleteGameTeam(FPGameTeam gameTeam) {
		return getSqlMapClientTemplate().delete("GameTeamSql.deleteGameTeam", gameTeam);		
	}

	public int updateGameTeam(FPGameTeam gameTeam) {
		return getSqlMapClientTemplate().update("GameTeamSql.updateGameTeam", gameTeam);
	}
	
	public int countGameTeam(FPGameTeam gameTeam) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("GameTeamSql.countGameTeam", gameTeam);
		return count;
	}

}
