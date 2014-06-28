package com.respace.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;







import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.domain.FPUserGroup;
import com.respace.service.FPGameMatchScheduleServiceImpl;
import com.respace.service.FPGameTeamServiceImpl;
import com.respace.service.FPUserGroupServiceImpl;
import com.respace.service.FPUserServiceImpl;
import com.respace.util.MyJsonUtil;

@Controller
public class FPUserController {
private Logger logger = Logger.getLogger(getClass());
	
	
	
	@Autowired
	private final FPUserGroupServiceImpl userGroupService = null;
	@Autowired
	private final FPUserServiceImpl userService = null;
		
	
	@RequestMapping(value="/api.readUser.do")
    public ResponseEntity<String> readUser(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String nickname = ServletRequestUtils.getStringParameter(request, "nickname", "");
		String device_id = ServletRequestUtils.getStringParameter(request, "device_id", "");
		
		int count_event = userService.countUser(new FPUser());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		FPUser user = new FPUser();
		user.setId(id);
		user.setNickname(nickname);
		user.setDevice_id(device_id);
	
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		//event.setQuery_start(query_start);
		//event.setQuery_number(query_number);
		List<FPUser> userList = userService.readUserList(user);
 
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(userList, "users"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.readUserRanking.do")
    public ResponseEntity<String> readUserRanking(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		
		int count_event = userService.countUser(new FPUser());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		FPUser user = new FPUser();
		user.setId(id);
		//user.setNickname(nickname);
		//user.setDevice_id(device_id);
	
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		//event.setQuery_start(query_start);
		//event.setQuery_number(query_number);
		List<FPUser> userList = userService.readUserRankingList(user);
		
		int rank = 1;
		for(int i = 0 ; i < userList.size() ; i++){
			FPUser u = userList.get(i);
			
			u.setPosition(i+1);
			
			if(i > 0){
				FPUser u_prev = userList.get(i-1);
				if(u.getRight_prophet_num() == u_prev.getRight_prophet_num() && u.getProphet_num() == u_prev.getProphet_num())
					rank--;
			}
			
			u.setRank(rank);
			
			
			rank++;
		}
 
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(userList, "users"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.isUserExist.do")
    public @ResponseBody String isUserExist(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String nickname = ServletRequestUtils.getStringParameter(request, "nickname", "");
		String device_id = ServletRequestUtils.getStringParameter(request, "device_id", "");
		
		nickname = URLDecoder.decode(nickname, "UTF-8");
		
		int count_event = userService.countUser(new FPUser());
		System.out.println("count_event :"+count_event);
		
		if(nickname.isEmpty() && device_id.isEmpty())
			return "fail-params-empty";
				
		FPUser user = new FPUser();
		user.setId(id);
		user.setNickname(nickname);
		user.setDevice_id(device_id);
	
		List<FPUser> userList = userService.readUserList(user);
		if(userList.size() == 1)
			return "1";
		else if(userList.size() == 0)
			return "0";
		else
			return "many-user-found-error";
    }
	
	@RequestMapping(value="/api.addUser.do")
    public @ResponseBody String addUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String device_id = ServletRequestUtils.getStringParameter(request, "device_id", "");
		String nickname = ServletRequestUtils.getStringParameter(request, "nickname", "");
		

		String is_nickname_initialized = ServletRequestUtils.getStringParameter(request, "is_nickname_initialized", "");
		
		String tag = ServletRequestUtils.getStringParameter(request, "tag", "");
		Integer group_id = ServletRequestUtils.getIntParameter(request, "group_id", 0);
		Integer score_dynamic = ServletRequestUtils.getIntParameter(request, "score_dynamic", 0);
		Integer score_static = ServletRequestUtils.getIntParameter(request, "score_static", 0);
		String twitter = ServletRequestUtils.getStringParameter(request, "twitter", "");
		String facebook = ServletRequestUtils.getStringParameter(request, "facebook", "");
		String is_twitter_visible = ServletRequestUtils.getStringParameter(request, "is_twitter_visible", "");
		String is_facebook_visible = ServletRequestUtils.getStringParameter(request, "is_facebook_visible", "");
		
		
		nickname = URLDecoder.decode(nickname, "UTF-8");
		tag = URLDecoder.decode(tag, "UTF-8");
		
		FPUser user = new FPUser();
		user.setNickname(nickname);
		user.setDevice_id(device_id);
		List<FPUser> userList = userService.readUserList(user);
		
		if(nickname.isEmpty())
			return "fail-param-nickname-empty";
		else if(device_id.isEmpty())
			return "fail-param-device_id-empty"+"한글나옴?";
		else if(userList.size() > 0)
			return "fail-user-exist";
		else{
			FPUser addUser = new FPUser();
			addUser.setNickname(nickname);
			addUser.setDevice_id(device_id);

			addUser.setIs_nickname_initialized(is_nickname_initialized);
			
			addUser.setTag(tag);
			addUser.setGroup_id(group_id);
			addUser.setScore_dynamic(score_dynamic);
			addUser.setScore_static(score_static);
			
			addUser.setTwitter(twitter);
			addUser.setIs_twitter_visible(is_twitter_visible);
			addUser.setFacebook(facebook);
			addUser.setIs_facebook_visible(is_facebook_visible);
			
			try {
				userService.createUser(addUser);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
			
			user = new FPUser();
			user.setNickname(nickname);
			user.setDevice_id(device_id);
			userList = userService.readUserList(user);
			if(userList.size() == 1)
				return "success";
			else
				return "fail-unknown";
		}
    }
	
	@RequestMapping(value="/api.updateUser.do")
    public @ResponseBody String updateUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String device_id = ServletRequestUtils.getStringParameter(request, "device_id", "");
		String nickname = ServletRequestUtils.getStringParameter(request, "nickname", "");
		String is_nickname_initialized = ServletRequestUtils.getStringParameter(request, "is_nickname_initialized", "");
		
		String tag = ServletRequestUtils.getStringParameter(request, "tag", "");
		Integer group_id = ServletRequestUtils.getIntParameter(request, "group_id", 0);
		Integer score_dynamic = ServletRequestUtils.getIntParameter(request, "score_dynamic", 0);
		Integer score_static = ServletRequestUtils.getIntParameter(request, "score_static", 0);
		String twitter = ServletRequestUtils.getStringParameter(request, "twitter", "");
		String facebook = ServletRequestUtils.getStringParameter(request, "facebook", "");
		String is_twitter_visible = ServletRequestUtils.getStringParameter(request, "is_twitter_visible", "");
		String is_facebook_visible = ServletRequestUtils.getStringParameter(request, "is_facebook_visible", "");
		

		nickname = URLDecoder.decode(nickname, "UTF-8");
		tag = URLDecoder.decode(tag, "UTF-8");
		
		FPUser user = new FPUser();
		//user.setNickname(nickname);
		user.setId(id);
		user.setDevice_id(device_id);
		List<FPUser> userList = userService.readUserList(user);
		System.out.println(userList);
		if(userList.size() != 1){
			return "fail-unmatched-number-of-user";
		}else{
			FPUser updateUser = new FPUser();
			updateUser.setId(userList.get(0).getId());
			//updateUser.setDevice_id(device_id);
			
			updateUser.setNickname(nickname);
			updateUser.setIs_nickname_initialized(is_nickname_initialized);
			
			updateUser.setTag(tag);
			updateUser.setGroup_id(group_id);
			updateUser.setScore_dynamic(score_dynamic);
			updateUser.setScore_static(score_static);
			
			updateUser.setTwitter(twitter);
			updateUser.setIs_twitter_visible(is_twitter_visible);
			updateUser.setFacebook(facebook);
			updateUser.setIs_facebook_visible(is_facebook_visible);
			System.out.println(updateUser);
			userService.updateUser(updateUser);
			
			return "success";
		}
    }
	
	@RequestMapping(value="/api.readUserGroup.do")
    public ResponseEntity<String> readUserGroup(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		
		int count_event = userGroupService.countUserGroup(new FPUserGroup());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		FPUserGroup userGroup = new FPUserGroup();
		userGroup.setName(name);
			
		int query_start = ( query_page - 1 ) * query_number;
		//event.setCode_category(code_category);
		//event.setQuery_start(query_start);
		//event.setQuery_number(query_number);
		List<FPUserGroup> userGroupList = userGroupService.readUserGroupList(userGroup);
 
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(userGroupList, "userGroups"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.addUserGroup.do")
    public @ResponseBody String addUserGroup(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		
		name = URLDecoder.decode(name, "UTF-8");
		
		FPUserGroup userGroup = new FPUserGroup();
		userGroup.setName(name);
		List<FPUserGroup> userGroupList = userGroupService.readUserGroupList(userGroup);
		
		if(name.isEmpty())
			return "fail-param-name-empty";
		if(userGroupList.size() > 0)
			return "fail-group-exist";
		else{
			FPUserGroup addUserGroup = new FPUserGroup();
			addUserGroup.setName(name);
			try {
				userGroupService.createUserGroup(addUserGroup);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
			
			userGroup = new FPUserGroup();
			userGroup.setName(name);
			userGroupList = userGroupService.readUserGroupList(userGroup);
			if(userGroupList.size() == 1)
				return "success";
			else
				return "fail-unknown";
		}
    }
	
	@RequestMapping(value="/api.deleteUserGroup.do")
    public @ResponseBody String deleteUserGroup(HttpServletRequest request, HttpServletResponse response) {
		String name = ServletRequestUtils.getStringParameter(request, "name", "");
		
		FPUserGroup userGroup = new FPUserGroup();
		userGroup.setName(name);
		List<FPUserGroup> userGroupList = userGroupService.readUserGroupList(userGroup);
		
		if(name.isEmpty())
			return "fail-param-name-empty";
		if(userGroupList.size() == 0)
			return "fail-already-non-exist";
		else{
			FPUserGroup deleteUserGroup = new FPUserGroup();
			deleteUserGroup.setName(name);
			try {
				userGroupService.deleteUserGroup(deleteUserGroup);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
			
			userGroup = new FPUserGroup();
			userGroup.setName(name);
			userGroupList = userGroupService.readUserGroupList(userGroup);
			if(userGroupList.size() == 0)
				return "success";
			else
				return "fail-unknown";
		}
		

    }
}
