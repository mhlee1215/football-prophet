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
import com.respace.domain.RS_Event;
import com.respace.service.ArticleServiceImpl;
import com.respace.service.AssetServiceImpl;
import com.respace.service.CodeServiceImpl;
import com.respace.service.ProjectServiceImpl;
import com.respace.service.EventServiceImpl;
import com.respace.service.UserService;
import com.respace.util.MyJsonUtil;

@Controller
public class EventController {
private Logger logger = Logger.getLogger(getClass());
	
	@Autowired
	private final UserService userService = null;
	
	@Autowired
	private final ProjectServiceImpl projectService = null;
	
	@Autowired
	private final EventServiceImpl eventService = null;
	
	@Autowired
	private final CodeServiceImpl codeService = null;
	
	@Autowired
	private final ArticleServiceImpl articleService = null;
	
	@Autowired
	private final AssetServiceImpl assetService = null;
	
	@RequestMapping("/event.do")
    public ModelAndView event(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		//String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_event = eventService.countEvent(new RS_Event());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		RS_Event event = new RS_Event();
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		event.setQuery_start(query_start);
		event.setQuery_number(query_number);
		List<RS_Event> eventList = eventService.readEventList(event);
 
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("event category");
		List<RS_Code> eventCodeList = codeService.readCodeList(code);
		
		ModelAndView model = new ModelAndView("event");
		model.addObject("active", "event");
		model.addObject("eventCodeList", eventCodeList);
		model.addObject("eventList", eventList);
		model.addObject("query_page", query_page);
		model.addObject("pager_start", pager_start);
		model.addObject("pager_size", pager_size);
		return model;
    }
	
	@RequestMapping(value="/api.event.do")
    public ResponseEntity<String> eventListAPI(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		//String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_event = eventService.countEvent(new RS_Event());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		RS_Event event = new RS_Event();
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		event.setQuery_start(query_start);
		event.setQuery_number(query_number);
		List<RS_Event> eventList = eventService.readEventList(event);
 
		RS_Code code = null;
		code = new RS_Code();
		code.setCategory("event category");
		//List<RS_Code> eventCodeList = codeService.readCodeList(code);
		
		//ModelAndView model = new ModelAndView();
		//model.addObject("eventList", eventList);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(eventList, "Events"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/api.eventDetail.do")
    public ResponseEntity<String> eventDetialAPI(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Event s = new RS_Event();
		s.setId(id);
		RS_Event event = eventService.readEvent(s);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(event.toString(), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/eventDetail.do")
    public ModelAndView eventDetial(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Event s = new RS_Event();
		s.setId(id);
		RS_Event event = eventService.readEvent(s);
	
		ModelAndView model = new ModelAndView("eventDetail");
		model.addObject("active", "event");
		model.addObject("event", event);
		
		
		return model;
    }
	
	@RequestMapping("/eventImageSlider.do")
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
	
	@RequestMapping("/eventRegister.do")
    public ModelAndView eventRegister(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Event s = new RS_Event();
		RS_Event event = null;
		if (id > 0){
			s.setId(id);
			event = eventService.readEvent(s);
		}else{
			s.setStatus("U");
			int new_id = eventService.createEvent(s);
			s.setId(new_id);
			event = eventService.readEvent(s);
		}
	
		ModelAndView model = new ModelAndView("eventEdit");
		model.addObject("active", "event");
		model.addObject("event", event);

		return model; 
    }
	
	@RequestMapping("/eventEdit.do")
    public ModelAndView eventEdit(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String test = ServletRequestUtils.getStringParameter(request, "test", "");
		
		System.out.println("<<<<<<<<<<<<<<<<<<"+test);
		
		RS_Event s = new RS_Event();
		s.setId(id);
		RS_Event event = eventService.readEvent(s);
	
		ModelAndView model = new ModelAndView("eventEdit");
		model.addObject("active", "event");
		model.addObject("event", event);
		return model;
    }
	
	@RequestMapping(value="/eventEditSubmit.do", method = RequestMethod.POST)
    public @ResponseBody String eventEditSubmit(@ModelAttribute RS_Event event, BindingResult result ) throws Exception {
		System.out.println(event);
		//Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		//Integer id = 0;
		
		//s.setId(id);
		int rtn = eventService.updateEvent(event);
		//RS_Event event = eventService.readEvent(s);
	
		//ModelAndView model = new ModelAndView("eventEdit");
		//model.addObject("active", "event");
		//model.addObject("event", event);
		return "success";
    }

	
	@RequestMapping("/eventDelete.do")
    public @ResponseBody String eventDelete(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		RS_Event s = new RS_Event();
		s.setId(id);
		eventService.deleteEvent(s);
		//RS_Event event = eventService.readEvent(s);
	
		ModelAndView model = new ModelAndView("redirect:event.do");
		model.addObject("active", "event");
		return "success";
    }
}
