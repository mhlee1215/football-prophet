package com.respace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.respace.domain.RS_Article;

@Repository
public class ArticleDaoImpl extends SqlMapClientDaoSupport {
	
	 @Resource(name="sqlMapClient")
	 protected void initDAO(SqlMapClient sqlMapClient) {        
		 this.setSqlMapClient(sqlMapClient);
	 } 
	
	@SuppressWarnings("unchecked")
	public List<RS_Article> readArticleList(RS_Article article) {	
		List<RS_Article> array = getSqlMapClientTemplate().queryForList("ArticleSql.readArticleList", article);
		return array;
	}

	public RS_Article readArticle(RS_Article Article) {
		RS_Article result = (RS_Article)getSqlMapClientTemplate().queryForObject("ArticleSql.readArticle", Article);
		return result;
	}


	public int createArticle(RS_Article Article) {
		Integer rt_id = (Integer) getSqlMapClientTemplate().insert("ArticleSql.createArticle", Article);
		return rt_id;
	}


	public int deleteArticle(RS_Article Article) {
		return getSqlMapClientTemplate().delete("ArticleSql.deleteArticle", Article);		
	}

	public int updateArticle(RS_Article Article) {
		return getSqlMapClientTemplate().update("ArticleSql.updateArticle", Article);
	}

}
