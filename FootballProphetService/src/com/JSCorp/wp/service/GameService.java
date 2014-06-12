package com.JSCorp.wp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
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

import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.domain.FPGameResult;
import com.JSCorp.wp.domain.FPGameTeam;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.var.Env;

public class GameService {

	public static int RESULT_ADD = 1;
	public static int RESULT_UPDATE = 2;
	public static int RESULT_FAIL = -1;
	
	
	public static ArrayList<FPGameMatchSchedule> getGameMatchSchedules() {
		
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
		
		
		
		
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameMatchSchedule> matches = new ArrayList<FPGameMatchSchedule>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameMatchSchedule.do");

			//System.out.println("executing request " + httpget.getURI());
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
					JSONArray lang = (JSONArray) o.get("GameMatchSchedules");

					

					for (int i = 0; i < lang.size(); i++) {

						// System.out.println("The " + i
						// + " element of the array: " + lang.get(i));
						JSONObject o2 = (JSONObject) lang.get(i);
						//System.out.println(o2.get("reference_time"));

						FPGameMatchSchedule matchSchedule = new FPGameMatchSchedule();
						String str;
						if ((str = (String) o2.get("id")) != null) {
							matchSchedule.setId(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("type")) != null) {
							matchSchedule.setType(str);
						}
						if ((str = (String) o2.get("home_team_id")) != null) {
							int home_team_id = Integer.parseInt(str);
							matchSchedule.setHome_team_id(home_team_id);
							matchSchedule.setHome_team_name(teamNameMap.get(home_team_id));
							matchSchedule.setGameGroup(teamGroupMap.get(home_team_id));
						}
						if ((str = (String) o2.get("away_team_id")) != null) {
							int away_team_id = Integer.parseInt(str);
							matchSchedule.setAway_team_id(away_team_id);
							matchSchedule.setAway_team_name(teamNameMap.get(away_team_id));
						}
						if ((str = (String) o2.get("city")) != null) {
							matchSchedule.setCity(str);
						}
						if ((str = (String) o2.get("month")) != null) {
							matchSchedule.setMonth(str);
						}
						if ((str = (String) o2.get("day")) != null) {
							matchSchedule.setDay(str);
						}
						if ((str = (String) o2.get("time")) != null) {
							matchSchedule.setTime(str);
						}
						if ((str = (String) o2.get("reference_time")) != null) {
							matchSchedule.setReference_time(str);
						}

						matches.add(matchSchedule);
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
			return matches;
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
	
	public static ArrayList<FPGameTeam> getGameTeam() {
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameTeam> teams = new ArrayList<FPGameTeam>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameTeam.do");

			//System.out.println("executing request " + httpget.getURI());
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
					JSONArray lang = (JSONArray) o.get("GameTeams");

					

					for (int i = 0; i < lang.size(); i++) {

						// System.out.println("The " + i
						// + " element of the array: " + lang.get(i));
						JSONObject o2 = (JSONObject) lang.get(i);
						//System.out.println(o2.get("reference_time"));

						FPGameTeam gameTeam = new FPGameTeam();
						String str;
						if ((str = (String) o2.get("id")) != null) {
							gameTeam.setId(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("team_name")) != null) {
							gameTeam.setTeam_name(str);
						}
						if ((str = (String) o2.get("team_name_kor")) != null) {
							gameTeam.setTeam_name_kor(str);
						}
						if ((str = (String) o2.get("game_group")) != null) {
							gameTeam.setGame_group(str);
						}
						

						teams.add(gameTeam);
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
			return teams;
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
	
	public static ArrayList<FPGameProphet> getGameProphet() {
		return getGameProphet(null);
	}
	
	public static ArrayList<FPGameProphet> getGameProphet(FPGameProphet prophet) {
		if(prophet == null)
			prophet = new FPGameProphet();
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameProphet.do"+prophet.toStringSealize());

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
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("GameProphets");

					

					for (int i = 0; i < lang.size(); i++) {

						// System.out.println("The " + i
						// + " element of the array: " + lang.get(i));
						JSONObject o2 = (JSONObject) lang.get(i);
						//System.out.println(o2.get("reference_time"));

						FPGameProphet gameProphet = new FPGameProphet();
						String str;
						if ((str = (String) o2.get("id")) != null) {
							gameProphet.setId(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("user_id")) != null) {
							gameProphet.setUser_id(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("match_id")) != null) {
							gameProphet.setMatch_id(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("prophet_type")) != null) {
							gameProphet.setProphet_type(str);
						}
						if ((str = (String) o2.get("home_team_win")) != null) {
							gameProphet.setHome_team_win(str);
						}
						if ((str = (String) o2.get("away_team_win")) != null) {
							gameProphet.setAway_team_win(str);
						}
						if ((str = (String) o2.get("draw")) != null) {
							gameProphet.setDraw(str);
						}
						if ((str = (String) o2.get("prophet_result")) != null) {
							gameProphet.setProphet_result(str);
						}
						if ((str = (String) o2.get("comment")) != null) {
							gameProphet.setComment(str);
						}
						
						
						prophets.add(gameProphet);
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
			return prophets;
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
	
	public static int getGameProphetFinished(int user_id) {
		
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameProphetFinished.do"+"?user_id="+Integer.toString(user_id));

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
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("GameProphets");

					return lang.size();
				}

			}
			httpget.abort();
			//System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();
			return 0;
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

		return 0;
	}
	
public static int getGameProphetCorrect(int user_id) {
		
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameProphet> prophets = new ArrayList<FPGameProphet>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameProphetCorrect.do"+"?user_id="+Integer.toString(user_id));

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
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("GameProphets");

					return lang.size();
				}

			}
			httpget.abort();
			//System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();
			return 0;
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

		return 0;
	}
	
	
	public static int setGameProphet(FPGameProphet gameProphet){
		

		try {
			gameProphet.setComment(URLEncoder.encode(gameProphet.getComment(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<FPGameProphet> readProphet = getGameProphet(gameProphet);
		
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
	

	public static void main(String[] args) throws ParseException {

//		FPGameProphet gameProphet = new FPGameProphet();
//		List<FPGameProphet> readGameProphet = GameService.getGameProphet(gameProphet);
//		System.out.println(readGameProphet);
		
		
		
		System.out.println(GameService.getGameProphetFinished(1));
		System.out.println(GameService.getGameProphetCorrect(1));
		System.out.println(GameService.getGameResults(new FPGameResult()));
		
		
		
		//ADD GAME PROPHET
		/*
		for(int j = 1 ; j < 10 ; j++){
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
				gameProphet.setComment("뭔가 한글 코멘트..");
				System.out.println(GameService.setGameProphet(gameProphet));
			}
		}
		*/
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
//		//System.out.println(GameService.getGameTeam().size());
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