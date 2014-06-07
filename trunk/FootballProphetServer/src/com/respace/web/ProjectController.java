package com.respace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class ProjectController {

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
	
	@RequestMapping("/cleanup.do")
    public ModelAndView cleanup(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RS_Asset asset = null;
		asset = new RS_Asset();
		List<RS_Asset> assetList = assetService.readAssetList(asset);
		for (RS_Asset a : assetList){
			RS_Asset aa = new RS_Asset();
			aa.setId(aa.getId());
			assetService.deleteAsset(aa);
		}
		
		RS_Project project = null;
		project = new RS_Project();
		List<RS_Project> projectList = projectService.readProjectList(project);
		for (RS_Project a : projectList){
			RS_Project aa = new RS_Project();
			aa.setId(aa.getId());
			projectService.deleteProject(aa);
		}
		
		
		RS_Space space = null;
		space = new RS_Space();
		List<RS_Space> spaceList = spaceService.readSpaceList(space);
		for (RS_Space a : spaceList){
			RS_Space aa = new RS_Space();
			aa.setId(aa.getId());
			spaceService.deleteSpace(aa);
		}
		
		RS_Article article = null;
		article = new RS_Article();
		List<RS_Article> ArticleList = articleService.readArticleList(article);
		for (RS_Article a : ArticleList){
			RS_Article aa = new RS_Article();
			aa.setId(aa.getId());
			articleService.deleteArticle(aa);
		}
		
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/initialize_web.do")
    public ModelAndView initialize_web(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		cleanup(request, response);
		
		add_surface_article(request, response);
		add_user_type_data(request, response);
		add_project_category_data(request, response);
		add_prepared_data(request, response);
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/add_surface_article.do")
    public ModelAndView add_surface_article(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RS_Article article = null;
		
		article = new RS_Article();
		article.setTitle("share your space");
		article.setCategory("index_top_left");
		article.setContent("INITIAL VALUE");
		articleService.createArticle(article);
		
		article = new RS_Article();
		article.setTitle("open your project");
		article.setCategory("index_top_center");
		article.setContent("INITIAL VALUE");
		articleService.createArticle(article);
		
		article = new RS_Article();
		article.setTitle("now playing");
		article.setCategory("index_top_right");
		article.setContent("INITIAL VALUE");
		articleService.createArticle(article);
		
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping("/add_user_type_data.do")
    public ModelAndView add_user_type_data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("user type");
		List<RS_Code> projectCodeList = codeService.readCodeList(code);
		for (RS_Code c : projectCodeList){
			RS_Code cc = new RS_Code();
			cc.setId(c.getId());
			codeService.deleteCode(cc);
		}
		
		int seq_no = 1;
		code = new RS_Code();
		code.setName("일반");
		code.setCode("1");
		code.setCategory("user type");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		code = new RS_Code();
		code.setName("관리자");
		code.setCode("2");
		code.setCategory("user type");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		ModelAndView model = new ModelAndView("index");
		return model;
	}
	
	@RequestMapping("/add_project_category_data.do")
    public ModelAndView add_project_category_data(HttpServletRequest request, HttpServletResponse response) throws Exception {
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("project category");
		List<RS_Code> projectCodeList = codeService.readCodeList(code);
		for (RS_Code c : projectCodeList){
			//code = new RS_Code();
			//code.setCategory("project category");
			RS_Code cc = new RS_Code();
			cc.setId(c.getId());
			codeService.deleteCode(cc);
		}
		
		int seq_no = 1;
		
		code = new RS_Code();
		code.setName("팝업스토어");
		code.setCode("1");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		
		code = new RS_Code();
		code.setName("강연/세미나");
		code.setCode("2");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		code = new RS_Code();
		code.setName("마켓/장터");
		code.setCode("3");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		code = new RS_Code();
		code.setName("전시/공연");
		code.setCode("4");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		code = new RS_Code();
		code.setName("모임/파티");
		code.setCode("5");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		code = new RS_Code();
		code.setName("기타");
		code.setCode("6");
		code.setCategory("project category");
		code.setSeq_no(seq_no++);
		codeService.createCode(code);
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		return model;
	}
	
	@RequestMapping("/add_prepared_data.do")
    public ModelAndView add_prepared_data(HttpServletRequest request, HttpServletResponse response) throws Exception {

		RS_Project project = null;
		RS_Asset asset = null;
		int  rt_project_id = 0;
		RS_Space space = null;
		int rt_space_id = 0;
		
		project = new RS_Project();
		project.setTitle("나이키 뉴 에디션 팝업");
		project.setId_contact(0);
		project.setDescription("나이키 뉴 에디션 설명");
		project.setHost_name("NIKE");
		project.setCode_category("0");
		rt_project_id = projectService.createProject(project);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_1.jpg");
			asset.setReference_id(rt_project_id);
			asset.setReference_category("project");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		
		project = new RS_Project();
		project.setTitle("어플리케이션 시연");
		project.setId_contact(0);
		project.setDescription("어플리케이션 시연 설명");
		project.setHost_name("ST");
		project.setCode_category("0");
		rt_project_id = projectService.createProject(project);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_3.jpg");
			asset.setReference_id(rt_project_id);
			asset.setReference_category("project");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		project = new RS_Project();
		project.setTitle("뭔가 다른 행사");
		project.setId_contact(0);
		project.setDescription("행사의 설명");
		project.setHost_name("ANOTHER");
		project.setCode_category("0");
		rt_project_id = projectService.createProject(project);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_2.jpg");
			asset.setReference_id(rt_project_id);
			asset.setReference_category("project");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		project = new RS_Project();
		project.setTitle("또 행사");
		project.setId_contact(0);
		project.setDescription("또다른 행사의 설명");
		project.setHost_name("MORE");
		project.setCode_category("0");
		rt_project_id = projectService.createProject(project);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_4.jpg");
			asset.setReference_id(rt_project_id);
			asset.setReference_category("project");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
			
			
		space = new RS_Space();
		space.setName("신사동 코노이");
		space.setStatus("V");
		rt_space_id = spaceService.createSpace(space);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_1.jpg");
			asset.setReference_id(rt_space_id);
			asset.setReference_category("space");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		space = new RS_Space();
		space.setName("신촌 명물거리");
		space.setStatus("V");
		rt_space_id = spaceService.createSpace(space);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_2.jpg");
			asset.setReference_id(rt_space_id);
			asset.setReference_category("space");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		space = new RS_Space();
		space.setName("이대 앞 팝업 스토어");
		space.setStatus("V");
		rt_space_id = spaceService.createSpace(space);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_3.jpg");
			asset.setReference_id(rt_space_id);
			asset.setReference_category("space");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		space = new RS_Space();
		space.setName("청담동 갤러리");
		space.setStatus("V");
		rt_space_id = spaceService.createSpace(space);
		
			asset = new RS_Asset();
			asset.setUrl("img/portfolio_4.jpg");
			asset.setReference_id(rt_space_id);
			asset.setReference_category("space");
			asset.setSeq_no(1);
			assetService.createAsset(asset);
		
		
		ModelAndView model = new ModelAndView("index");
		return model;
    }
}
