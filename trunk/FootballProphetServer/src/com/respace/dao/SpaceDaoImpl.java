package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Space;
import com.respace.domain.RS_Space;

@Repository
public class SpaceDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<RS_Space> readSpaceList(RS_Space space) {	
		List<RS_Space> array = getSqlMapClientTemplate().queryForList("SpaceSql.readSpaceList", space);
		return array;
	}

	public RS_Space readSpace(RS_Space Space) {
		RS_Space result = (RS_Space)getSqlMapClientTemplate().queryForObject("SpaceSql.readSpace", Space);
		return result;
	}


	public int createSpace(RS_Space Space) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("SpaceSql.createSpace", Space);
		return rt_id;
	}
	

	public int deleteSpace(RS_Space Space) {
		return getSqlMapClientTemplate().delete("SpaceSql.deleteSpace", Space);		
	}

	public int updateSpace(RS_Space Space) {
		return getSqlMapClientTemplate().update("SpaceSql.updateSpace", Space);
	}
	
	public int countSpace(RS_Space space) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("SpaceSql.countSpace", space);
		return count;
	}

}
