package com.JSCorp.wp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.JSCorp.wp.domain.FPAppInfo;
import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPGameMatchScheduleBin;
import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.domain.FPGameProphetBin;
import com.JSCorp.wp.domain.FPGameResult;
import com.JSCorp.wp.domain.FPGameTeam;
import com.JSCorp.wp.domain.FPGameTeamBin;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.var.Env;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class GameService {

	public static int RESULT_SUCCESS = 2;
	public static int RESULT_ADD = 1;
	public static int RESULT_UPDATE = 2;
	public static int RESULT_FAIL = -1;
	public static int RESULT_FAIL_TIME_OVER = -2;
	
	
	
	public static ArrayList<FPGameMatchSchedule> getGameMatchSchedules() {
		return getGameMatchSchedules(0);
	}
	
	public static ArrayList<FPGameMatchSchedule> getGameMatchSchedules(int user_id) {
		
		ArrayList<FPGameTeam> teams = getGameTeam();
		
		Map<Integer, String> teamNameMap = new HashMap<Integer, String>();
		Map<Integer, String> teamGroupMap = new HashMap<Integer, String>();
		for(FPGameTeam team : teams){
			if("kor".equals(Env.lang))
				teamNameMap.put(team.getId(), team.getTeam_name_kor());
			else
				teamNameMap.put(team.getId(), team.getTeam_name());
			
			teamGroupMap.put(team.getId(), team.getGame_group());
		}
		
		ArrayList<FPGameMatchSchedule> matches = new ArrayList<FPGameMatchSchedule>();
		try {
			
			InputStream in = new URL(Env.url + "api.readGameMatchSchedule.do").openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			FPGameMatchScheduleBin matchBin = gson.fromJson(reader, FPGameMatchScheduleBin.class);   ;
			matches = (ArrayList<FPGameMatchSchedule>) matchBin.getGameMatchSchedules();
			
			for(FPGameMatchSchedule matchSchedule : matches){
					int home_team_id = matchSchedule.getHome_team_id();//Integer.parseInt(str);
					matchSchedule.setHome_team_id(home_team_id);
					matchSchedule.setHome_team_name(teamNameMap.get(home_team_id));
					matchSchedule.setGameGroup(teamGroupMap.get(home_team_id));
					
					int away_team_id = matchSchedule.getAway_team_id();//Integer.parseInt(str);
					matchSchedule.setAway_team_id(away_team_id);
					matchSchedule.setAway_team_name(teamNameMap.get(away_team_id));
			}
			
			
			if( user_id > 0){
				FPGameProphet prophet = new FPGameProphet();
				prophet.setUser_id(user_id);
				List<FPGameProphet> gameProphets = GameService.getGameProphet(prophet);
				//System.out.println("P>>"+gameProphets);
				for(FPGameProphet p : gameProphets){
					int match_id = p.getMatch_id();
					if(match_id ==0) continue;
					
					if(FPGameProphet.FLAG_TRUE.equals(p.getHome_team_win()))
						matches.get(match_id-1).setProphet_home_win(1);
					else if(FPGameProphet.FLAG_TRUE.equals(p.getAway_team_win()))
						matches.get(match_id-1).setProphet_away_win(1);
					else if(FPGameProphet.FLAG_TRUE.equals(p.getDraw()))
						matches.get(match_id-1).setProphet_draw(1);
				}
			}
			
			
			return matches;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return null;
	}
	
	public static ArrayList<FPGameTeam> getGameTeam() {
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameTeam> teams = new ArrayList<FPGameTeam>();
		try {
			
			InputStream in = new URL(Env.url + "api.readGameTeam.do").openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			FPGameTeamBin gameTeamBin = gson.fromJson(reader, FPGameTeamBin.class);   ;
			teams = (ArrayList<FPGameTeam>) gameTeamBin.getGameTeams();
		
			return teams;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}
	
	public static FPAppInfo getAppInfo() {
		
		FPAppInfo appInfo = null;
		try {
			
			InputStream in = new URL(Env.url + "api.readAppInfo.do").openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			appInfo = gson.fromJson(reader, FPAppInfo.class);   ;
		
			return appInfo;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return appInfo;
	}
	
	public static ArrayList<FPGameProphet> getGameProphet() {
		return getGameProphet(null);
	}
	
	public static ArrayList<FPGameProphet> getGameProphet(FPGameProphet prophet) {
		if(prophet == null)
			prophet = new FPGameProphet();

		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {
					
			InputStream in = new URL(Env.url + "api.readGameProphet.do"+prophet.toStringSealize()).openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			FPGameProphetBin prophetBin = gson.fromJson(reader, FPGameProphetBin.class);   ;
			prophets = (ArrayList<FPGameProphet>) prophetBin.getGameProphets();
			
			return prophets;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		return null;
	}
	
	public static int getGameProphetFinished(int user_id) {
		
		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {

			InputStream in = new URL(Env.url + "api.readGameProphet.do"+"?user_id="+Integer.toString(user_id)).openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			FPGameProphetBin prophetBin = gson.fromJson(reader, FPGameProphetBin.class);   ;
			prophets = (ArrayList<FPGameProphet>) prophetBin.getGameProphets();
			
			return prophets.size();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return 0;
	}
	
public static int getGameProphetCorrect(int user_id) {
		
		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {
			InputStream in = new URL(Env.url + "api.readGameCorrect.do"+"?user_id="+Integer.toString(user_id)).openStream();
			JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
			Gson gson = new Gson();
			FPGameProphetBin prophetBin = gson.fromJson(reader, FPGameProphetBin.class);   ;
			prophets = (ArrayList<FPGameProphet>) prophetBin.getGameProphets();
			
			return prophets.size();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 

		return 0;
	}
	
	public static int setGameProphet(FPGameProphet gameProphet){
		
		try {
			gameProphet.setComment(URLEncoder.encode(gameProphet.getComment(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.setGameProphet.do"
					+ gameProphet.toStringSealize());
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if (line.startsWith("fail-time-over")) {
						// Error handling
						return RESULT_FAIL_TIME_OVER;
					}else if (line.startsWith("fail")) {
						// Error handling
						return RESULT_FAIL;
					} else if (line.equals("success")) {
						return RESULT_SUCCESS;
					} else {
						return RESULT_FAIL;
					}
				}
			}
			httpget.abort();
			httpclient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return RESULT_FAIL;
	}
	
	public static int setGameProphetOld(FPGameProphet gameProphet){
		

		try {
			gameProphet.setComment(URLEncoder.encode(gameProphet.getComment(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<FPGameProphet> readProphet = getGameProphet(gameProphet);
		
		System.out.println("readProphet size : "+readProphet.size());
		
		if(readProphet.size() == 1){
			updateGameProphet(gameProphet);
			return RESULT_UPDATE;
		}else if(readProphet.size() == 0){
			addGameProphet(gameProphet);
			return RESULT_ADD;
		}else{
			return RESULT_FAIL;
		}
	}
	
	public static boolean addGameProphet(FPGameProphet gameProphet) {
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.addGameProphet.do"
					+ gameProphet.toStringSealize());
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if (line.startsWith("fail")) {
						// Error handling
						return false;
					} else if (line.equals("success")) {
						return true;
					} else {
						return false;
					}
				}
			}
			httpget.abort();
			httpclient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return false;
	}
	
	public static boolean updateGameProphet(FPGameProphet gameProphet) {
		
		
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.updateGameProphet.do"
					+ gameProphet.toStringSealize());
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if (line.startsWith("fail")) {
						// Error handling
						return false;
					} else if (line.equals("success")) {
						return true;
					} else {
						return false;
					}
				}
			}
			httpget.abort();
			httpclient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return false;
	}
	
public static boolean updateGameResult(FPGameResult gameResult) {
		
		
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.updateGameResult.do"
					+ gameResult.toStringSealize());
			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if (line.startsWith("fail")) {
						// Error handling
						return false;
					} else if (line.startsWith("success")) {
						return true;
					} else {
						return false;
					}
				}
			}
			httpget.abort();
			httpclient.getConnectionManager().shutdown();

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return false;
	}
	
	
	public static ArrayList<FPGameResult> getGameResults(FPGameResult gameResult_params) {
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameResult> results = new ArrayList<FPGameResult>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameResult.do"+gameResult_params.toStringSealize());

			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			//System.out.println("----------------------------------------");
			// ?? ??
			//System.out.println(response.getStatusLine());
			if (entity != null) {
				//System.out.println("Response content length: "
				//		+ entity.getContentLength());
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					JSONParser j = new JSONParser();
					//System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("GameResults");

					

					for (int i = 0; i < lang.size(); i++) {

						// System.out.println("The " + i
						// + " element of the array: " + lang.get(i));
						JSONObject o2 = (JSONObject) lang.get(i);
						//System.out.println(o2.get("reference_time"));

						FPGameResult gameResult = new FPGameResult();
						String str;
						if ((str = (String) o2.get("id")) != null) {
							gameResult.setId(Integer.parseInt(str));
						}
						
						if ((str = (String) o2.get("match_id")) != null) {
							gameResult.setMatch_id(Integer.parseInt(str));
						}
						
						if ((str = (String) o2.get("match_type")) != null) {
							gameResult.setMatch_type(str);
						}
						
						if ((str = (String) o2.get("home_team_score")) != null) {
							gameResult.setHome_team_score(Integer.parseInt(str));
						}
						
						if ((str = (String) o2.get("away_team_score")) != null) {
							gameResult.setAway_team_score(Integer.parseInt(str));
						}
						
						
						

						results.add(gameResult);
					}

					// Map response2 = (Map)o.get("GameMatchSchedules");
					// JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
					// System.out.println(o2.entrySet().size());

					// //System.out.println(response2.get("FPGameMatchSchedule"));
				}

			}
			httpget.abort();
			//System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();
			return results;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		return null;
	}
	
	public static void manualUpdateGameResults(){
		FPGameResult gameResult = null;
		int match_id = 1;
		
		//1
		gameResult = new FPGameResult(match_id, "1", 3, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 5);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 3, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 3, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//6
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 3);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 3, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//11
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 0, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 4, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//16
		
		gameResult = new FPGameResult(match_id, "1", 1, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 0, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 4, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 0, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 3, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 0, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 0, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//
		gameResult = new FPGameResult(match_id, "1", 2, 5);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//31
		gameResult = new FPGameResult(match_id, "1", 1, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 4, 2);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 4, 1);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 1, 3);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 3, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		gameResult = new FPGameResult(match_id, "1", 2, 0);
		match_id++;
		GameService.updateGameResult(gameResult);
		
		//36
		
	}
	
	
	public static void manualDummyUserAdd(){
		int dummyCount=10;
		
		for(int i=200 ; i < 200+dummyCount ; i++){
			FPUser user = new FPUser();
			user.setNickname("한글닉넴_"+Integer.toString(i));
			user.setDevice_id(String.format("%010d", i));
			try {
				UserService.addUser(user);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		for(int j = 200 ; j < 200+dummyCount ; j++){
			for(int i = 1 ; i <= 48 ; i++){
				int prophet_case = ((int)(Math.random()*100))%3+1;
				
				FPGameProphet gameProphet = new FPGameProphet();
				gameProphet.setUser_id(j);
				gameProphet.setMatch_id(i);
				gameProphet.setProphet_type("1");
				if(prophet_case == 1)
					gameProphet.setHome_team_win("1");
				else if(prophet_case == 2)
					gameProphet.setAway_team_win("1");	
				else if(prophet_case == 3)
					gameProphet.setDraw("1");	
				gameProphet.setProphet_result("");
				gameProphet.setComment("");
				int rtn = GameService.setGameProphet(gameProphet);
				
				if(rtn == GameService.RESULT_FAIL_TIME_OVER){
					System.out.println("time is late!");
				}
			}
		}
		
		
		
		
	}
	

	public static void main(String[] args) throws ParseException {


		//System.out.println(GameService.getGameMatchSchedules(62));
		//System.out.println(GameService.getGameTeam());
		//System.out.println(GameService.getAppInfo());
		GameService.manualDummyUserAdd();
		GameService.manualUpdateGameResults();
		if(1==1) return;

		//System.out.println(GameService.getGameMatchSchedules(62));
		//if(1==1)return;
		//GameService.getGameMatchSchedules(62);
		
		
		{
		FPGameResult gameResult = new FPGameResult();
		gameResult.setMatch_id(8);
		gameResult.setMatch_type("1");
		
		int home_score = 1;//((int)(Math.random()*100))%3+1;
		gameResult.setHome_team_score(home_score);
		int away_score = 2;//((int)(Math.random()*100))%3+1;
		gameResult.setAway_team_score(away_score);
		GameService.updateGameResult(gameResult);
		}
		
		if(1==1) return;
		
		//ADD GAME PROPHET
		
		for(int j = 68 ; j < 70 ; j++){
			for(int i = 1 ; i <= 10 ; i++){
				int prophet_case = ((int)(Math.random()*100))%3+1;
				
				FPGameProphet gameProphet = new FPGameProphet();
				gameProphet.setUser_id(j);
				gameProphet.setMatch_id(i);
				gameProphet.setProphet_type("1");
				if(prophet_case == 1)
					gameProphet.setHome_team_win("1");
				else if(prophet_case == 2)
					gameProphet.setAway_team_win("1");	
				else if(prophet_case == 3)
					gameProphet.setDraw("1");	
				gameProphet.setProphet_result("");
				gameProphet.setComment("1111뭔가 한글 코멘트..123123");
				int rtn = GameService.setGameProphet(gameProphet);
				
				if(rtn == GameService.RESULT_FAIL_TIME_OVER){
					System.out.println("time is late!");
				}
			}
		}
		
		if(1==1) return;
		
		for(int i = 1; i <= 15; i++)
		{
			FPGameResult gameResult = new FPGameResult();
			gameResult.setMatch_id(i);
			gameResult.setMatch_type("1");
			
			int home_score = ((int)(Math.random()*100))%3+1;
			gameResult.setHome_team_score(home_score);
			int away_score = ((int)(Math.random()*100))%3+1;
			gameResult.setAway_team_score(away_score);
			GameService.updateGameResult(gameResult);
		}
		
//		FPGameProphet gameProphet = new FPGameProphet();
//		List<FPGameProphet> readGameProphet = GameService.getGameProphet(gameProphet);
//		System.out.println(readGameProphet);
		
		
		//System.out.println(GameService.getGameMatchSchedules(62));
		
		
//		System.out.println(GameService.getGameProphetFinished(1));
//		System.out.println(GameService.getGameProphetCorrect(1));
//		System.out.println(GameService.getGameResults(new FPGameResult()));
		
		
		
		
		//System.out.println(encodeResult);
//		

		
		
		
//		FPGameProphet updateGameProphet = new FPGameProphet();
//		updateGameProphet.setUser_id(11);
//		updateGameProphet.setMatch_id(101);
//		updateGameProphet.setHome_team_win("0");
//		updateGameProphet.setAway_team_win("0");
//		updateGameProphet.setDraw("1");
//		updateGameProphet.setProphet_result("1");
//		updateGameProphet.setComment("blahblah...adsfadsfsdfafdadfdsaf..");
//		System.out.println(GameService.updateGameProphet(updateGameProphet));
		
		
		
		
		
		
		
//<<<<<<< .mine
//		
//		System.out.println(GameService.getGameMatchSchedules());
//		//System.out.println(GameService.getGameMatchSchedules().size());
//		
//		System.out.println(GameService.getGameTeam().size());
//=======
//		System.out.println(GameService.getGameMatchSchedules());
//		//System.out.println(GameService.getGameTeam().size());
//>>>>>>> .r50
		// // HttpClient ??
		// HttpClient httpclient = new DefaultHttpClient();
		// try {
		// // HttpGet??
		// HttpGet httpget = new HttpGet(
		// Env.url + "api.readGameMatchSchedule.do");
		//
		// System.out.println("executing request " + httpget.getURI());
		// HttpResponse response = httpclient.execute(httpget);
		// HttpEntity entity = response.getEntity();
		//
		// System.out.println("----------------------------------------");
		// // ?? ??
		// System.out.println(response.getStatusLine());
		// if (entity != null) {
		// System.out.println("Response content length: "
		// + entity.getContentLength());
		// BufferedReader rd = new BufferedReader(new InputStreamReader(
		// response.getEntity().getContent()));
		//
		// String line = "";
		// while ((line = rd.readLine()) != null) {
		// JSONParser j = new JSONParser();
		// System.out.println("line:" + line);
		// JSONObject o = (JSONObject) j.parse(line);
		// JSONArray lang = (JSONArray) o.get("GameMatchSchedules");
		//
		// ArrayList<FPGameMatchSchedule> matches = new
		// ArrayList<FPGameMatchSchedule>();
		//
		// for (int i = 0; i < lang.size(); i++) {
		//
		// //System.out.println("The " + i
		// // + " element of the array: " + lang.get(i));
		// JSONObject o2 = (JSONObject) lang.get(i);
		// System.out.println(o2.get("reference_time"));
		//
		// FPGameMatchSchedule matchSchedule = new FPGameMatchSchedule();
		// String str;
		// if((str = (String) o2.get("id"))!=null){
		// matchSchedule.setId(Integer.parseInt(str));
		// }
		// if((str = (String) o2.get("type"))!=null){
		// matchSchedule.setType(str);
		// }
		// if((str = (String) o2.get("home_team_id"))!=null){
		// matchSchedule.setAway_team_id(Integer.parseInt(str));
		// }
		// if((str = (String) o2.get("away_team_id"))!=null){
		// matchSchedule.setAway_team_id(Integer.parseInt(str));
		// }
		// if((str = (String) o2.get("city"))!=null){
		// matchSchedule.setCity(str);
		// }
		// if((str = (String) o2.get("time"))!=null){
		// matchSchedule.setTime(str);
		// }
		// if((str = (String) o2.get("reference_time"))!=null){
		// matchSchedule.setReference_time(str);
		// }
		//
		// matches.add(matchSchedule);
		// }
		//
		// // Map response2 = (Map)o.get("GameMatchSchedules");
		// // JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
		// // System.out.println(o2.entrySet().size());
		//
		// // //System.out.println(response2.get("FPGameMatchSchedule"));
		// }
		//
		// return matches;
		// }
		// httpget.abort();
		// System.out.println("----------------------------------------");
		// httpclient.getConnectionManager().shutdown();
		//
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// } finally {
		// httpclient.getConnectionManager().shutdown();
		// }
		//
		// return null;
	}

}