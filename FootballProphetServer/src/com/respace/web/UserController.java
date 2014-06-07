package com.respace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.respace.domain.RS_Article;
import com.respace.domain.RS_Asset;
import com.respace.domain.RS_Code;
import com.respace.domain.RS_Project;
import com.respace.domain.RS_Space;
import com.respace.domain.RS_User;
import com.respace.service.ArticleServiceImpl;
import com.respace.service.AssetServiceImpl;
import com.respace.service.CodeServiceImpl;
import com.respace.service.ProjectServiceImpl;
import com.respace.service.SpaceServiceImpl;
import com.respace.service.UserService;




@Controller
public class UserController {

	private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final ProjectServiceImpl projectService = null;
	
	@Autowired
	private final SpaceServiceImpl spaceService = null;
	
	@Autowired
	private final CodeServiceImpl codeService = null;
	
	@Autowired
	private final ArticleServiceImpl articleService = null;
	
	@Autowired
	private final AssetServiceImpl assetService = null;
	
	@RequestMapping("/projectOpen.do")
    public ModelAndView projectOpen(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("projectOpen");
		model.addObject("active", "project");
		return model;
    }
	
	@RequestMapping("/projectDoor.do")
    public ModelAndView projectDoor(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("projectDoor");
		model.addObject("active", "project");
		return model;
    }
	
	@RequestMapping("/spaceBooking.do")
    public ModelAndView spaceBooking(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("spaceBooking");
		model.addObject("active", "space");
		return model;
    }
	
	@RequestMapping("/spaceDoor.do")
    public ModelAndView spaceDoor(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("spaceDoor");
		model.addObject("active", "space");
		return model;
    }
	
	
	@RequestMapping("/spaceShare.do")
    public ModelAndView spaceShare(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("spaceShare");
		model.addObject("active", "space");
		return model;
    }
	
	@RequestMapping("/space.do")
    public ModelAndView space(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		//String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_space = spaceService.countSpace(new RS_Space());
		System.out.println("count_space :"+count_space);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_space/query_number);
		int pager_start = 1;
		
		RS_Space space = new RS_Space();
		int query_start = ( query_page - 1 ) * query_number;
		//space.setCode_category(code_category);
		space.setQuery_start(query_start);
		space.setQuery_number(query_number);
		List<RS_Space> spaceList = spaceService.readSpaceList(space);
 
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("space category");
		List<RS_Code> spaceCodeList = codeService.readCodeList(code);
		
		ModelAndView model = new ModelAndView("space");
		model.addObject("active", "space");
		model.addObject("spaceCodeList", spaceCodeList);
		model.addObject("spaceList", spaceList);
		model.addObject("query_page", query_page);
		model.addObject("pager_start", pager_start);
		model.addObject("pager_size", pager_size);
		return model;
    }
	
	@RequestMapping("/spaceDetail.do")
    public ModelAndView spaceDetial(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Space s = new RS_Space();
		s.setId(id);
		RS_Space space = spaceService.readSpace(s);
	
		ModelAndView model = new ModelAndView("spaceDetail");
		model.addObject("active", "space");
		model.addObject("space", space);
		
		
		return model;
    }
	
	@RequestMapping("/imageSlider.do")
    public ModelAndView imageSlider(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String type = ServletRequestUtils.getStringParameter(request, "type", "");
		String readOnly = ServletRequestUtils.getStringParameter(request, "readOnly", "Y");
		
		RS_Asset a = new RS_Asset();
		a.setReference_category(type);
		a.setReference_id(id);
		List<RS_Asset> assetList = assetService.readAssetList(a);
		
		ModelAndView model = new ModelAndView("imageSlider");	
		model.addObject("id", id);
		model.addObject("type", type);
		model.addObject("readOnly", readOnly);
		model.addObject("assetList", assetList);
		
		return model;
    }
	
	@RequestMapping("/spaceRegister.do")
    public ModelAndView spaceRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Space s = new RS_Space();
		RS_Space space = null;
		if (id > 0){
			s.setId(id);
			space = spaceService.readSpace(s);
		}else{
			s.setStatus("U");
			int new_id = spaceService.createSpace(s);
			s.setId(new_id);
			space = spaceService.readSpace(s);
		}
	
		ModelAndView model = new ModelAndView("spaceEdit");
		model.addObject("active", "space");
		model.addObject("space", space);

		return model; 
    }
	
	@RequestMapping("/spaceEdit.do")
    public ModelAndView spaceEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String test = ServletRequestUtils.getStringParameter(request, "test", "");
		
		System.out.println("<<<<<<<<<<<<<<<<<<"+test);
		
		RS_Space s = new RS_Space();
		s.setId(id);
		RS_Space space = spaceService.readSpace(s);
	
		ModelAndView model = new ModelAndView("spaceEdit");
		model.addObject("active", "space");
		model.addObject("space", space);
		return model;
    }
	
	@RequestMapping(value="/spaceEditSubmit.do", method = RequestMethod.POST)
    public @ResponseBody String spaceEditSubmit(@ModelAttribute RS_Space space, BindingResult result ) throws Exception {
		System.out.println(space);
		//Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		//Integer id = 0;
		
		//s.setId(id);
		int rtn = spaceService.updateSpace(space);
		//RS_Space space = spaceService.readSpace(s);
	
		//ModelAndView model = new ModelAndView("spaceEdit");
		//model.addObject("active", "space");
		//model.addObject("space", space);
		return "success";
    }

	
	@RequestMapping("/spaceDelete.do")
    public @ResponseBody String spaceDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Space s = new RS_Space();
		s.setId(id);
		spaceService.deleteSpace(s);
		//RS_Space space = spaceService.readSpace(s);
	
		ModelAndView model = new ModelAndView("redirect:space.do");
		model.addObject("active", "space");
		return "success";
    }
	
	@RequestMapping("/project.do")
    public ModelAndView project(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_project = projectService.countProject(new RS_Project());
		System.out.println("count_project :"+count_project);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_project/query_number);
		int pager_start = 1;
		
		RS_Project project = new RS_Project();
		int query_start = ( query_page - 1 ) * query_number;
		project.setCode_category(code_category);
		project.setQuery_start(query_start);
		project.setQuery_number(query_number);
		List<RS_Project> projectList = projectService.readProjectList(project);
 
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("project category");
		List<RS_Code> projectCodeList = codeService.readCodeList(code);
		
		ModelAndView model = new ModelAndView("project");
		model.addObject("active", "project");
		model.addObject("projectCodeList", projectCodeList);
		model.addObject("projectList", projectList);
		model.addObject("query_page", query_page);
		model.addObject("pager_start", pager_start);
		model.addObject("pager_size", pager_size);
		
		return model;
    }
	
	@RequestMapping("/projectDetail.do")
    public ModelAndView projectDetial(HttpServletRequest request, HttpServletResponse response) {		
		Integer project_id = ServletRequestUtils.getIntParameter(request, "id", 0);
	
		ModelAndView model = new ModelAndView("projectDetail");
		model.addObject("active", "project");
		
		
		return model;
    }
	
	@RequestMapping("/join.do")
    public ModelAndView join(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("join");
		model.addObject("active", "join");
		return model;
    }
	
	@RequestMapping("/contact.do")
    public ModelAndView contact(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView model = new ModelAndView("contact");
		model.addObject("active", "contact");
		return model;
    }
	
	
	
	@RequestMapping("/index.do")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String submittedUserId = ServletRequestUtils.getStringParameter(request, "submittedUserId", "");
		String loginComplete = ServletRequestUtils.getStringParameter(request, "loginComplete", "false");
		String loginFail = ServletRequestUtils.getStringParameter(request, "loginFail", "false");
		String logoutComplete = ServletRequestUtils.getStringParameter(request, "logoutComplete", "false");
		String registerComplete = ServletRequestUtils.getStringParameter(request, "registerComplete", "false");
		String registerFail = ServletRequestUtils.getStringParameter(request, "registerFail", "false");
	    String userid = (String)request.getSession().getAttribute("userid");
	    String user_type = (String) request.getSession().getAttribute("user_type");
	    
	    List<RS_Project> featuredProjectList = projectService.readFeaturedProject();
	    List<RS_Space> featuredSpaceList = spaceService.readFeaturedSpace(); 
	    
	    RS_Article article = null;
	    
	    article = new RS_Article();
	    article.setCategory("index_top_left");
	    RS_Article article_top_left = articleService.readArticle(article);
	    
	    article.setCategory("index_top_center");
	    RS_Article article_top_center = articleService.readArticle(article);
	    
	    article.setCategory("index_top_right");
	    RS_Article article_top_right = articleService.readArticle(article);
	    
	    if(userid == null){
    		String remoteHost = request.getRemoteHost();
    		//UserIdMap userIdMap = new UserIdMap();
    		//UserIdMap result = userService.getUserIdMap(remoteHost);
    		
//    		if(result == null){
//    			int nextId = userService.getNextUserIdMap();
//    			userIdMap.setExternalId(remoteHost);
//    			userIdMap.setInternalId(nextId);
//    			userService.createUserIdMap(userIdMap);
//    			User user = new User();
//    			user.setId(Integer.toString(userIdMap.getInternalId()));
//    			user.setInternalid((userIdMap.getInternalId()));
//    			user.setPassword("N/A");
//    			userService.createUser(user);
//    			
//    			result = userService.getUserIdMap(remoteHost);
//    		}
    		
    		//request.getSession().setAttribute("userid", Integer.toString(result.getInternalId()));
    		request.getSession().setAttribute("externalid", remoteHost);
    		
    		
	    }
	    String language = (String)request.getSession().getAttribute("lang");
		//LanguagePack lang = LanguageServiceImpl.getLangPack(language);
		
		
		ModelAndView model = new ModelAndView("index");
		//model.addObject("page_title", lang.getStringHazardReportingSystem());
		model.addObject("loginComplete", loginComplete);
		model.addObject("loginFail", loginFail);
		model.addObject("logoutComplete", logoutComplete);
		model.addObject("registerComplete", registerComplete);
		model.addObject("registerFail", registerFail);
		model.addObject("submittedUserId", submittedUserId);
		model.addObject("isUseController", "true");
		model.addObject("user_type", user_type);
		
		model.addObject("featuredProjectList", featuredProjectList);
		model.addObject("featuredSpaceList", featuredSpaceList);
		model.addObject("index_top_left", article_top_left);
		model.addObject("index_top_center", article_top_center);
		model.addObject("index_top_right", article_top_right);
		
		model.addObject("active", "index");
				
		return model;
    }
	
	@RequestMapping("/login.do")
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		logger.debug("public ModelAndView login");
		logger.debug("===[S]======================");
		logger.debug("email : "+email);
		logger.debug("password : "+password);
		RS_User user = new RS_User();
		user.setEmail(email);
		user.setPassword(password);
		
		int result = userService.readUser(user);
		
		
		ModelAndView model = null;
		
		if(result == RS_User.STATUS_NOT_FOUNDED){
			model = new ModelAndView("join");
			System.out.println("User does not exist! or password is wrong.");
			model.addObject("loginFail", "true");
		}
		else if(result == RS_User.STATUS_FOUNDED){
			model = new ModelAndView("redirect:index.do");
			System.out.println("User is founded!");
						
			request.getSession().setAttribute("email", user.getEmail());
			request.getSession().setAttribute("islogin", "true");
			model.addObject("loginComplete", "true");
		}
		model.addObject("email", email);
		logger.debug("===[S]======================");
		return model;
    }
//	
	@RequestMapping("/register.do")
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		if( email == null || password == null){
			model.addObject("reason", "parameter_incomplete");
			model.addObject("registerFail", "true");
		}else{
			RS_User user = new RS_User();
			user.setEmail(email);
			user.setPassword(password);
			user.setType("1");	//Set as normal user
			int result = userService.createUser(user);
			if(result == RS_User.STATUS_ALREADY_REGISTEREDED){
				System.out.println("The ID requested to register is already exists!");
				model.addObject("reason", "already_exist");
				model.addObject("registerFail", "true");
				//model.addObject("submittedUserId", id);
			}else if(result == RS_User.STATUS_SUCCESS_REGISTER){
				
	//			UserIdMap userIdMap = new UserIdMap();
	//			userIdMap.setExternalId(id);
	//			userIdMap.setInternalId(nextId);
	//			userService.createUserIdMap(userIdMap);
	//			model.addObject("registerComplete", "true");
	//			model.addObject("submittedUserId", id);
				
				model.addObject("registerComplete", "true");
			}
		}
		return model;
    }
	
	@RequestMapping("/logout.do")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		
		ModelAndView model = new ModelAndView("redirect:index.do");
		model.addObject("logoutComplete", "true");
		return model;
    }
//	
//	@RequestMapping("/findPassword.do")
//    public ModelAndView findPassword(HttpServletRequest request, HttpServletResponse response) {
//		
//		request.getSession().removeAttribute("userid");
//		
//		ModelAndView model = new ModelAndView("index");
//		model.addObject("logoutComplete", "true");
//		return model;
//    }
//	
//	
//	@RequestMapping("/changePassword.do")
//    public ModelAndView changePassword(HttpServletRequest request, HttpServletResponse response) {
//		
//		request.getSession().removeAttribute("userid");
//		
//		ModelAndView model = new ModelAndView("index");
//		model.addObject("logoutComplete", "true");
//		return model;
//    }
//	
}
