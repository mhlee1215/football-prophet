package com.respace.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.respace.dao.ArticleDaoImpl;
import com.respace.domain.RS_Article;
import com.respace.domain.RS_Article;



@Service
public class ArticleServiceImpl{

	private Logger logger = Logger.getLogger(getClass());

	@Autowired
	private ArticleDaoImpl		articleDao;
	
	boolean isEncrypt = true;

	public List<RS_Article> readArticleList(RS_Article article) {
		return articleDao.readArticleList(article);
	}

	public int createArticle(RS_Article article) throws Exception {
		return articleDao.createArticle(article);
		
	}

	public RS_Article readArticle(RS_Article article) throws Exception {
		return articleDao.readArticle(article);
	}
	
	public int updateArticle(RS_Article article) {
		return articleDao.updateArticle(article);
	}

	public int deleteArticle(RS_Article article) {
		return articleDao.deleteArticle(article);
	}
	
	public List<RS_Article> readFeaturedArticle(){
		ArrayList<RS_Article> featuredArticle = new ArrayList<RS_Article>();
		RS_Article article = new RS_Article();
		List<RS_Article> list = articleDao.readArticleList(article);
		for(int i = 0 ; i < 4 ; i++)
			featuredArticle.add(list.get(i));
		return featuredArticle;
	}
}
