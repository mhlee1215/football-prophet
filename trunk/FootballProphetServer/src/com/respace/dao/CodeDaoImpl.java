package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Code;

@Repository
public class CodeDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<RS_Code> readCodeList(RS_Code code) {	
		List<RS_Code> array = getSqlMapClientTemplate().queryForList("CodeSql.readCodeList", code);
		return array;
	}

	public RS_Code readCode(RS_Code Code) {
		RS_Code result = (RS_Code)getSqlMapClientTemplate().queryForObject("CodeSql.readCode", Code);
		return result;
	}


	public RS_Code createCode(RS_Code Code) {
		RS_Code c_code = (RS_Code) getSqlMapClientTemplate().insert("CodeSql.createCode", Code);
		return c_code;
	}


	public int deleteCode(RS_Code Code) {
		return getSqlMapClientTemplate().delete("CodeSql.deleteCode", Code);		
	}

	public int updateCode(RS_Code Code) {
		return getSqlMapClientTemplate().update("CodeSql.updateCode", Code);
	}

}
