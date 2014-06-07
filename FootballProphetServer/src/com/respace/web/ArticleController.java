package com.respace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.respace.domain.RS_Article;
import com.respace.domain.RS_Asset;
import com.respace.domain.RS_Code;
import com.respace.domain.RS_Project;
import com.respace.domain.RS_Space;
import com.respace.service.ArticleServiceImpl;
import com.respace.service.AssetServiceImpl;
import com.respace.service.CodeServiceImpl;
import com.respace.service.ProjectServiceImpl;
import com.respace.service.SpaceServiceImpl;




@Controller
public class ArticleController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final ProjectServiceImpl projectService = null;
	
	@Autowired
	private final SpaceServiceImpl spaceService = null;
	
	@Autowired
	private final CodeServiceImpl codeService = null;
	
	@Autowired
	private final AssetServiceImpl assetService = null;
	
	@Autowired
	private final ArticleServiceImpl articleService = null;
	
	@RequestMapping("/read_articles.do")
    public ModelAndView read_articles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/update_articles.do")
    public ModelAndView update_articles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/delete_articles.do")
    public ModelAndView delete_articles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/write_articles.do")
    public ModelAndView write_articles(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String id = ServletRequestUtils.getStringParameter(request, "id", "");
		String name = ServletRequestUtils.getStringParameter(request, "id", "");
		String email = ServletRequestUtils.getStringParameter(request, "id", "");
		String contents = ServletRequestUtils.getStringParameter(request, "id", "");
		
		ModelAndView model = new ModelAndView("redirect:contact.do");
		return model;
	}
	
	@RequestMapping("/leave_message.do")
    public ModelAndView leave_message(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		String email = ServletRequestUtils.getStringParameter(request, "email", "");
		String website = ServletRequestUtils.getStringParameter(request, "website", "");
		String content = ServletRequestUtils.getStringParameter(request, "content", "");
		
		RS_Article article = new RS_Article();
		article.setAuthor(name);
		article.setEmail(email);
		article.setContent(content);
		article.setWebsite(website);
		article.setCategory("message");
		int rt_id = articleService.createArticle(article);
		
		ModelAndView model = new ModelAndView("contact");
		model.addObject("send_message_complete", "true");
		return model;
	}
}
