package com.respace.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.respace.domain.RS_Asset;
import com.respace.domain.RS_Code;
import com.respace.domain.FPGameMatchSchedule;
import com.respace.service.ArticleServiceImpl;
import com.respace.service.AssetServiceImpl;
import com.respace.service.CodeServiceImpl;
import com.respace.service.ProjectServiceImpl;
import com.respace.service.FPGameMatchScheduleServiceImpl;
import com.respace.service.UserService;
import com.respace.util.MyJsonUtil;

@Controller
public class FPGameMatchScheduleController {
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final ProjectServiceImpl projectService = null;
	
	@Autowired
	private final FPGameMatchScheduleServiceImpl gameMatchScheduleService = null;
	
	@Autowired
	private final CodeServiceImpl codeService = null;
	
	@Autowired
	private final ArticleServiceImpl articleService = null;
	
	@Autowired
	private final AssetServiceImpl assetService = null;
	
	@RequestMapping(value="/api.gameMatchSchedule.do")
    public ResponseEntity<String> eventListAPI(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		//String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_event = gameMatchScheduleService.countGameMatchSchedule(new FPGameMatchSchedule());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		FPGameMatchSchedule gameMatchSchedule = new FPGameMatchSchedule();
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		//event.setQuery_start(query_start);
		//event.setQuery_number(query_number);
		List<FPGameMatchSchedule> gameMatchScheduleList = gameMatchScheduleService.readGameMatchScheduleList(gameMatchSchedule);
 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameMatchScheduleList, "GameMatchSchedules"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/api.1eventDetail.do")
    public ResponseEntity<String> eventDetialAPI(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		FPGameMatchSchedule s = new FPGameMatchSchedule();
		s.setId(id);
		FPGameMatchSchedule event = gameMatchScheduleService.readGameMatchSchedule(s);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(event.toString(), responseHeaders, HttpStatus.CREATED);
    }
	
	
}
