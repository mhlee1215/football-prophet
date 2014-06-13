package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.JSCorp.wp.domain.FPUser;
import com.ibatis.sqlmap.client.SqlMapClient;


@Repository
public class FPUserDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPUser> readUserList(FPUser user) {	
		List<FPUser> array = getSqlMapClientTemplate().queryForList("UserSql.readUserList", user);
		return array;
	}
	
	@SuppressWarnings("unchecked")
	public List<FPUser> readUserRankingList(FPUser user) {	
		List<FPUser> array = getSqlMapClientTemplate().queryForList("UserSql.readUserRankingList", user);
		return array;
	}
	
	public FPUser readUser(FPUser user) {
		FPUser result = (FPUser)getSqlMapClientTemplate().queryForObject("UserSql.readUser", user);
		return result;
	}


	public int createUser(FPUser user) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("UserSql.createUser", user);
		return rt_id;
	}
	

	public int deleteUser(FPUser user) {
		return getSqlMapClientTemplate().delete("UserSql.deleteUser", user);		
	}

	public int updateUser(FPUser user) {
		return getSqlMapClientTemplate().update("UserSql.updateUser", user);
	}
	
	public int countUser(FPUser user) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("UserSql.countUser", user);
		return count;
	}

}
