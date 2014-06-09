package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.FPUserGroup;

@Repository
public class FPUserGroupDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<FPUserGroup> readUserGroupList(FPUserGroup userGroup) {	
		List<FPUserGroup> array = getSqlMapClientTemplate().queryForList("UserGroupSql.readUserGroupList", userGroup);
		return array;
	}

	public FPUserGroup readUserGroup(FPUserGroup userGroup) {
		FPUserGroup result = (FPUserGroup)getSqlMapClientTemplate().queryForObject("UserGroupSql.readUserGroup", userGroup);
		return result;
	}


	public int createUserGroup(FPUserGroup userGroup) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("UserGroupSql.createUserGroup", userGroup);
		return rt_id;
	}
	

	public int deleteUserGroup(FPUserGroup userGroup) {
		return getSqlMapClientTemplate().delete("UserGroupSql.deleteUserGroup", userGroup);		
	}

	public int updateUserGroup(FPUserGroup userGroup) {
		return getSqlMapClientTemplate().update("UserGroupSql.updateUserGroup", userGroup);
	}
	
	public int countUserGroup(FPUserGroup userGroup) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("UserGroupSql.countUserGroup", userGroup);
		return count;
	}

}
