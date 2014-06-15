package com.respace.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.domain.FPGameResult;
import com.JSCorp.wp.domain.FPGameTeam;
import com.JSCorp.wp.domain.FPUser;
import com.respace.service.FPGameMatchScheduleServiceImpl;
import com.respace.service.FPGameProphetServiceImpl;
import com.respace.service.FPGameResultServiceImpl;
import com.respace.service.FPGameTeamServiceImpl;
import com.respace.util.MyJsonUtil;

@Controller
public class FPGameController {
private Logger logger = Logger.getLogger(getClass());
		
	@Autowired
	private final FPGameMatchScheduleServiceImpl gameMatchScheduleService = null;
	@Autowired
	private final FPGameTeamServiceImpl gameTeamService = null;
	@Autowired
	private final FPGameProphetServiceImpl gameProphetService = null;
	@Autowired
	private final FPGameResultServiceImpl gameResultService = null;
	
	
	@RequestMapping(value="/api.readGameMatchSchedule.do")
    public ResponseEntity<String> readGameMatchSchedule(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		
		int matchId = ServletRequestUtils.getIntParameter(request, "id", 0);
		//String code_category = ServletRequestUtils.getStringParameter(request, "code_category", "");
		//Integer query_start = ServletRequestUtils.getIntParameter(request, "query_start", 0);
		//Integer query_number = ServletRequestUtils.getIntParameter(request, "query_number", 12);
		
		int count_event = gameMatchScheduleService.countGameMatchSchedule(new FPGameMatchSchedule());
		System.out.println("count_event :"+count_event);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_event/query_number);
		int pager_start = 1;
		
		FPGameMatchSchedule gameMatchSchedule = new FPGameMatchSchedule();
		gameMatchSchedule.setId(matchId);
		
		int query_start = ( query_page - 1 ) * query_number;
		List<FPGameMatchSchedule> gameMatchScheduleList = gameMatchScheduleService.readGameMatchScheduleList(gameMatchSchedule);
		
		for(FPGameMatchSchedule ms : gameMatchScheduleList){
			if("Y".equalsIgnoreCase(ms.getMatch_finished())) continue;
			LocalDateTime cur_time = new LocalDateTime();//2014, 10, 15, 11, 0);
			int month = Integer.parseInt(ms.getMonth());
			int day = Integer.parseInt(ms.getDay());
			String t = ms.getTime();
			int hour = Integer.parseInt(t.substring(0, 2));
			int min = Integer.parseInt(t.substring(3, 5));
			System.out.println("Match time is "+month+"//"+day+"//"+hour+":"+min);
			LocalDateTime match_time = new LocalDateTime(2014, month, day, hour, min);
			//LocalDateTime match_time_end = match_time.plu
			//System.out.println(time.toDate().);
			
			if(cur_time.toDate().compareTo(match_time.toDate()) == 0){
				//return "fail-time-over";
			} else if(cur_time.toDate().compareTo(match_time.toDate()) > 0){
				//return "fail-time-over";
				ms.setMatch_finished("NOW");
			} else if(cur_time.toDate().compareTo(match_time.toDate()) < 0){
				//System.out.println("a is earlier than b");
			}
		}
		
 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameMatchScheduleList, "GameMatchSchedules"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.readGameProphet.do")
    public ResponseEntity<String> readGameProphet(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		
		String prophet_type = ServletRequestUtils.getStringParameter(request, "prophet_type", "");
		String home_team_win = ServletRequestUtils.getStringParameter(request, "home_team_win", "");
		String away_team_win = ServletRequestUtils.getStringParameter(request, "away_team_win", "");
		String draw = ServletRequestUtils.getStringParameter(request, "draw", "");
		String prophet_result = ServletRequestUtils.getStringParameter(request, "prophet_result", "");	
		String comment = ServletRequestUtils.getStringParameter(request, "comment", "");

		int count_game_prophet = gameProphetService.countGameProphet(new FPGameProphet());
		System.out.println("count_game_prophet :"+count_game_prophet);
		
		int query_number = 12;
		double pager_size = Math.ceil((double)count_game_prophet/query_number);
		int pager_start = 1;
		
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setId(id);
		gameProphet.setUser_id(user_id);
		gameProphet.setMatch_id(match_id);
		
		//System.out.println("한글..");

		//System.out.println(">>>>gameProphet: "+gameProphet);
		
		int query_start = ( query_page - 1 ) * query_number;
		List<FPGameProphet> gameProphetList = gameProphetService.readGameProphetList(gameProphet);
 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameProphetList, "GameProphets"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.readGameProphetCorrect.do")
    public ResponseEntity<String> readGameProphetCorrect(HttpServletRequest request, HttpServletResponse response) {
			
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);
	
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setUser_id(user_id);
		gameProphet.setProphet_result("1");		
		List<FPGameProphet> gameProphetList = gameProphetService.readGameProphetListCorrect(gameProphet);
 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameProphetList, "GameProphets"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.readGameProphetFinished.do")
    public ResponseEntity<String> readGameProphetFinished(HttpServletRequest request, HttpServletResponse response) {
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);		
		
		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setUser_id(user_id);
		
		List<FPGameProphet> gameProphetList = gameProphetService.readGameProphetListFinished(gameProphet);
 
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameProphetList, "GameProphets"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/api.addGameProphet.do")
    public @ResponseBody String addGameProphet(HttpServletRequest request, HttpServletResponse response) {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		
		String prophet_type = ServletRequestUtils.getStringParameter(request, "prophet_type", "");
		String home_team_win = ServletRequestUtils.getStringParameter(request, "home_team_win", "");
		String away_team_win = ServletRequestUtils.getStringParameter(request, "away_team_win", "");
		String draw = ServletRequestUtils.getStringParameter(request, "draw", "");
		String prophet_result = ServletRequestUtils.getStringParameter(request, "prophet_result", "");	
		String comment = ServletRequestUtils.getStringParameter(request, "comment", "");
		
		
		FPGameProphet gameProphet = new FPGameProphet();
		//gameProphet.setId(id);
		gameProphet.setUser_id(user_id);
		gameProphet.setMatch_id(match_id);
		gameProphet.setProphet_type(prophet_type);
		gameProphet.setProphet_result(prophet_result);
		gameProphet.setHome_team_win(home_team_win);
		gameProphet.setAway_team_win(away_team_win);
		gameProphet.setDraw(draw);
		gameProphet.setComment(comment);
		
		try {
			gameProphetService.createGameProphet(gameProphet);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail-"+e.toString();
		}
    }
	
	@RequestMapping(value="/api.setGameProphet.do")
    public @ResponseBody String setGameProphet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		
		String prophet_type = ServletRequestUtils.getStringParameter(request, "prophet_type", "");
		String home_team_win = ServletRequestUtils.getStringParameter(request, "home_team_win", "");
		String away_team_win = ServletRequestUtils.getStringParameter(request, "away_team_win", "");
		String draw = ServletRequestUtils.getStringParameter(request, "draw", "");
		String prophet_result = ServletRequestUtils.getStringParameter(request, "prophet_result", "");	
		String comment = ServletRequestUtils.getStringParameter(request, "comment", "");
		
		comment = URLDecoder.decode(comment, "UTF-8");
		
		FPGameMatchSchedule gameMatchSchedule = new FPGameMatchSchedule();
		gameMatchSchedule.setId(match_id);
		List<FPGameMatchSchedule> gameMatch = gameMatchScheduleService.readGameMatchScheduleList(gameMatchSchedule);
		
		if(gameMatch.size() != 1) return "fail-match-unavailable";
		//Time Check
		LocalDateTime cur_time = new LocalDateTime();//2014, 10, 15, 11, 0);
		int month = Integer.parseInt(gameMatch.get(0).getMonth());
		int day = Integer.parseInt(gameMatch.get(0).getDay());
		String t = gameMatch.get(0).getTime();
		int hour = Integer.parseInt(t.substring(0, 2));
		int min = Integer.parseInt(t.substring(3, 5));
		System.out.println("Match time is "+month+"//"+day+"//"+hour+":"+min);
		LocalDateTime match_time = new LocalDateTime(2014, month, day, hour, min);
		
		//System.out.println(time.toDate().);
		
		if(cur_time.toDate().compareTo(match_time.toDate()) == 0){
			return "fail-time-over";
		} else if(cur_time.toDate().compareTo(match_time.toDate()) > 0){
			return "fail-time-over";
		} else if(cur_time.toDate().compareTo(match_time.toDate()) < 0){
			System.out.println("a is earlier than b");
		}
		
		
		FPGameProphet gameProphetExistCheck = new FPGameProphet();
		gameProphetExistCheck.setId(id);
		gameProphetExistCheck.setUser_id(user_id);
		gameProphetExistCheck.setMatch_id(match_id);
		List<FPGameProphet> gameProphetListExist = gameProphetService.readGameProphetList(gameProphetExistCheck);
		
		
		FPGameProphet gameProphet = new FPGameProphet();
		//gameProphet.setId(id);
		gameProphet.setUser_id(user_id);
		gameProphet.setMatch_id(match_id);
		gameProphet.setProphet_type(prophet_type);
		gameProphet.setProphet_result(prophet_result);
		gameProphet.setHome_team_win(home_team_win);
		gameProphet.setAway_team_win(away_team_win);
		gameProphet.setDraw(draw);
		gameProphet.setComment(comment);
		
		if(gameProphetListExist.size() == 1){
			try {
				gameProphetService.updateGameProphet(gameProphet);
				return "success-update";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
		}else if(gameProphetListExist.size() == 0){
			try {
				gameProphetService.createGameProphet(gameProphet);
				return "success-add";
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
		}else{
			return "fail-unknown";
		}
		
		
    }
	
	@RequestMapping(value="/api.updateGameProphet.do")
    public @ResponseBody String updateGameProphet(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		Integer query_page = ServletRequestUtils.getIntParameter(request, "query_page", 1);
		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer user_id = ServletRequestUtils.getIntParameter(request, "user_id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		
		String prophet_type = ServletRequestUtils.getStringParameter(request, "prophet_type", "");
		String home_team_win = ServletRequestUtils.getStringParameter(request, "home_team_win", "");
		String away_team_win = ServletRequestUtils.getStringParameter(request, "away_team_win", "");
		String draw = ServletRequestUtils.getStringParameter(request, "draw", "");
		String prophet_result = ServletRequestUtils.getStringParameter(request, "prophet_result", "");	
		String comment = ServletRequestUtils.getStringParameter(request, "comment", "");
		
		comment = URLDecoder.decode(comment, "UTF-8");

		FPGameProphet gameProphet = new FPGameProphet();
		gameProphet.setId(id);
		gameProphet.setUser_id(user_id);
		gameProphet.setMatch_id(match_id);
		gameProphet.setProphet_type(prophet_type);
		gameProphet.setProphet_result(prophet_result);
		gameProphet.setHome_team_win(home_team_win);
		gameProphet.setAway_team_win(away_team_win);
		gameProphet.setDraw(draw);
		gameProphet.setComment(comment);
		
		//System.out.println(">>>comment:"+comment);
		//System.out.println(">>>"+gameProphet);
		
		try {
			gameProphetService.updateGameProphet(gameProphet);
			return "success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return "fail-"+e.toString();
		}
    }
	
	@RequestMapping("/api.readGameTeam.do")
    public ResponseEntity<String> readGameTeam(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		String game_group = ServletRequestUtils.getStringParameter(request, "game_group", "");
		
		FPGameTeam s = new FPGameTeam();
		s.setId(id);
		s.setGame_group(game_group);
		List<FPGameTeam> gameTeamList = gameTeamService.readGameTeamList(s);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameTeamList, "GameTeams"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/api.readGameResult.do")
    public ResponseEntity<String> readGameResult(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		String match_type = ServletRequestUtils.getStringParameter(request, "match_type", "");
		Integer home_team_score = ServletRequestUtils.getIntParameter(request, "home_team_score", 0);
		Integer away_team_score = ServletRequestUtils.getIntParameter(request, "away_team_score", 0);
		
		FPGameResult s = new FPGameResult();
		s.setId(id);
		s.setMatch_id(match_id);
		s.setMatch_type(match_type);
		
		List<FPGameResult> gameResultList = gameResultService.readGameResultList(s);
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
		return new ResponseEntity<String>(MyJsonUtil.toString(gameResultList, "GameResults"), responseHeaders, HttpStatus.CREATED);
    }
	
	@RequestMapping("/api.updateGameResult.do")
    public @ResponseBody String updateGameResult(HttpServletRequest request, HttpServletResponse response) throws Exception {		
		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
		Integer match_id = ServletRequestUtils.getIntParameter(request, "match_id", 0);
		String match_type = ServletRequestUtils.getStringParameter(request, "match_type", "");
		Integer home_team_score = ServletRequestUtils.getIntParameter(request, "home_team_score", 0);
		Integer away_team_score = ServletRequestUtils.getIntParameter(request, "away_team_score", 0);
		
		FPGameResult s = new FPGameResult();
		s.setId(id);
		s.setMatch_id(match_id);
		s.setMatch_type(match_type);
		
		FPGameResult gameResults = gameResultService.readGameResult(s);
		
		
			try {
				
				s.setMatch_type(match_type);
				s.setHome_team_score(home_team_score);
				s.setAway_team_score(away_team_score);
				
				String tag = "";
				if(gameResults == null){
					gameResultService.createGameResult(s);
					tag = "-result-created";
				}else{
					s.setId(gameResults.getId());
					gameResultService.updateGameResult(s);
					tag = "-result-updated";
				}
				
				System.out.println(">>>"+s);
				FPGameProphet prophet = new FPGameProphet();
				prophet.setMatch_id(match_id);
				
				if(home_team_score > away_team_score){
					prophet.setHome_team_win("1");
					prophet.setProphet_result("1");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setAway_team_win("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setDraw("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
				}
				else if(home_team_score < away_team_score){
					prophet.setHome_team_win("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setAway_team_win("1");
					prophet.setProphet_result("1");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setDraw("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
				}
				else{ 
					prophet.setHome_team_win("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setAway_team_win("1");
					prophet.setProphet_result("0");
					gameProphetService.updateGameProphetResult(prophet);
					
					prophet.setDraw("1");
					prophet.setProphet_result("1");
					gameProphetService.updateGameProphetResult(prophet);
				}
				
				return "success"+tag;
			} catch (Exception e) {
				// TODO Auto-generated catch block
				return "fail-"+e.toString();
			}
		
    }
	

	public static void main(String[] argv){
		
		System.out.println(Integer.parseInt("01"));
		String a = "17:00";
		System.out.println(a.substring(0, 2)+"//"+a.substring(3, 5));
		
		System.out.println("hi");
		LocalDateTime time = new LocalDateTime();//2014, 10, 15, 11, 0);
		LocalDateTime time3 = time.plusHours(4);
		LocalDateTime time2 = new LocalDateTime(2014, 10, 15, 10, 0);
		
		System.out.println(time.toDate());
		System.out.println(time3.toDate());
		
		if(time.toDate().compareTo(time2.toDate()) == 0){
			System.out.println("same");
		} else if(time.toDate().compareTo(time2.toDate()) > 0){
			System.out.println("a is later than b");
		} else if(time.toDate().compareTo(time2.toDate()) < 0){
			System.out.println("a is earlier than b");
		}
		
		
	}
	
	
//	@RequestMapping("/api.1eventDetail.do")
//    public ResponseEntity<String> eventDetialAPI(HttpServletRequest request, HttpServletResponse response) throws Exception {		
//		Integer id = ServletRequestUtils.getIntParameter(request, "id", 0);
//		
//		FPGameMatchSchedule s = new FPGameMatchSchedule();
//		s.setId(id);
//		FPGameMatchSchedule event = gameMatchScheduleService.readGameMatchSchedule(s);
//		
//		HttpHeaders responseHeaders = new HttpHeaders();
//		responseHeaders.add("Content-Type", "text/html; charset=UTF-8");
//		return new ResponseEntity<String>(event.toString(), responseHeaders, HttpStatus.CREATED);
//    }
//	
	
}
