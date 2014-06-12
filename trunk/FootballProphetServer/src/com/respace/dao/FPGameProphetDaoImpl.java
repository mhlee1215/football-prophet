package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPGameProphet;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class FPGameProphetDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPGameProphet> readGameProphetList(FPGameProphet gameProphet) {	
		List<FPGameProphet> array = getSqlMapClientTemplate().queryForList("GameProphetSql.readGameProphetList", gameProphet);
		return array;
	}
	@SuppressWarnings("unchecked")
	public List<FPGameProphet> readGameProphetListCorrect(FPGameProphet gameProphet) {	
		List<FPGameProphet> array = getSqlMapClientTemplate().queryForList("GameProphetSql.readGameProphetListCorrect", gameProphet);
		return array;
	}
	@SuppressWarnings("unchecked")
	public List<FPGameProphet> readGameProphetListFinished(FPGameProphet gameProphet) {	
		List<FPGameProphet> array = getSqlMapClientTemplate().queryForList("GameProphetSql.readGameProphetListFinished", gameProphet);
		return array;
	}

	public FPGameProphet readGameProphet(FPGameProphet gameProphet) {
		FPGameProphet result = (FPGameProphet)getSqlMapClientTemplate().queryForObject("GameProphetSql.readGameProphet", gameProphet);
		return result;
	}


	public int createGameProphet(FPGameProphet gameProphet) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("GameProphetSql.createGameProphet", gameProphet);
		return rt_id;
	}
	

	public int deleteGameProphet(FPGameProphet gameProphet) {
		return getSqlMapClientTemplate().delete("GameProphetSql.deleteGameProphet", gameProphet);		
	}

	public int updateGameProphet(FPGameProphet gameProphet) {
		return getSqlMapClientTemplate().update("GameProphetSql.updateGameProphet", gameProphet);
	}
	
	public int updateGameProphetResult(FPGameProphet gameProphet) {
		return getSqlMapClientTemplate().update("GameProphetSql.updateGameProphetResult", gameProphet);
	}
	
	public int countGameProphet(FPGameProphet gameProphet) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("GameProphetSql.countGameProphet", gameProphet);
		return count;
	}

}
