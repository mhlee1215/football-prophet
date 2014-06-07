package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Project;

@Repository
public class ProjectDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<RS_Project> readProjectList(RS_Project project) {	
		List<RS_Project> array = getSqlMapClientTemplate().queryForList("ProjectSql.readProjectList", project);
		return array;
	}

	public RS_Project readProject(RS_Project project) {
		RS_Project result = (RS_Project)getSqlMapClientTemplate().queryForObject("ProjectSql.readProject", project);
		return result;
	}


	public int createProject(RS_Project project) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("ProjectSql.createProject", project);
		return rt_id; 
	}


	public int deleteProject(RS_Project project) {
		return getSqlMapClientTemplate().delete("ProjectSql.deleteProject", project);		
	}

	public int updateProject(RS_Project project) {
		return getSqlMapClientTemplate().update("ProjectSql.updateProject", project);
	}
	
	public int countProject(RS_Project project) {
		Integer count = (Integer) getSqlMapClientTemplate().queryForObject("ProjectSql.countProject", project);
		return count;
	}

}
