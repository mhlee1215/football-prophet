package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPGameResult;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class FPGameResultDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPGameResult> readGameResultList(FPGameResult gameResult) {	
		List<FPGameResult> array = getSqlMapClientTemplate().queryForList("GameResultSql.readGameResultList", gameResult);
		return array;
	}

	public FPGameResult readGameResult(FPGameResult gameResult) {
		FPGameResult result = (FPGameResult)getSqlMapClientTemplate().queryForObject("GameResultSql.readGameResult", gameResult);
		return result;
	}


	public int createGameResult(FPGameResult gameResult) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("GameResultSql.createGameResult", gameResult);
		return rt_id;
	}
	

	public int deleteGameResult(FPGameResult gameResult) {
		return getSqlMapClientTemplate().delete("GameResultSql.deleteGameResult", gameResult);		
	}

	public int updateGameResult(FPGameResult gameResult) {
		return getSqlMapClientTemplate().update("GameResultSql.updateGameResult", gameResult);
	}
	
	public int countGameResult(FPGameResult gameResult) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("GameResultSql.countGameResult", gameResult);
		return count;
	}

}
