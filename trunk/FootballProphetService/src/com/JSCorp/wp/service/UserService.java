package com.JSCorp.wp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.rmi.dgc.VMID;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

import com.JSCorp.wp.domain.FPGameProphet;
import com.JSCorp.wp.domain.FPGameResult;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.var.Env;

public class UserService {

	/**
	 * @param args
	 * @throws ParseException
	 */

	static String NICK_ALREADY_INITIALIZED = "Y";
	static String NICK_NOT_INITIALIZED = "N";

	public static boolean isUserExistByNickname(String nickname) {

		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.isUserExist.do"
					+ "?nickname=" + nickname);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				
				String lineAll = "";
				String line = "";
				while ((line = rd.readLine()) != null) {
					lineAll += line;
				}
				
				
				if (lineAll.startsWith("fail")) {
					// Error handling
				} else if (lineAll.equals("1")) {
					return true;
				} else if (lineAll.equals("0")) {
					return false;
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
		return true;
	}

	public static boolean isUserExistByDeviceId(String divice_id) {
		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.isUserExist.do"
					+ "?device_id=" + divice_id);

			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String line = "";
				while ((line = rd.readLine()) != null) {
					if (line.startsWith("fail")) {
						// Error handling
					} else if (line.equals("1")) {
						return true;
					} else if (line.equals("0")) {
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
		return true;
	}

	public static boolean addUser(FPUser user)
			throws UnsupportedEncodingException {

		user.setNickname(URLEncoder.encode(user.getNickname(), "UTF-8"));
		user.setTag(URLEncoder.encode(user.getTag(), "UTF-8"));

		if (isUserExistByDeviceId(user.getDevice_id()))
			return false;

		if (user.getNickname() == null || "".equals(user.getNickname())) {
			// Generate pseudo-random string
			String str = UUID.randomUUID().toString();// new
														// java.rmi.dgc.VMID().toString();
			str = str.replace(":", "").replace("-", "").toUpperCase();
			user.setNickname("NONAME_"
					+ str.toString().substring(str.length() - 10, str.length()));
		}

		if (user.getIs_nickname_initialized() == null
				|| "".equals(user.getIs_nickname_initialized())) {
			user.setIs_nickname_initialized("N");
		}

		HttpClient httpclient = new DefaultHttpClient();
		try {

			HttpGet httpget = new HttpGet(Env.url + "api.addUser.do"
					+ user.toStringSealize());

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

	public static FPUser getUserByDeviceIdEx(String device_id) {
		FPUser user = getUserByDeviceId(device_id);

		ArrayList<FPGameResult> gameResults = GameService
				.getGameResults(new FPGameResult());

		user.setFinished_game_num(gameResults.size());
		user.setProphet_num(GameService.getGameProphetFinished(user.getId()));
		user.setRight_prophet_num(GameService.getGameProphetCorrect(user
				.getId()));

		return user;
	}

	public static FPUser getUserByDeviceId(String device_id) {
		FPUser user = null;

		HttpClient httpclient = new DefaultHttpClient();

		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.readUser.do"
					+ "?device_id=" + device_id);

			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// ?? ??
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				
				String linePart = "";
				String line = "";
				while ((linePart = rd.readLine()) != null) {
					line += linePart;
				}
				
				
					JSONParser j = new JSONParser();
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("users");
					for (int i = 0; i < lang.size(); i++) {
						user = new FPUser();
						JSONObject o2 = (JSONObject) lang.get(i);

						user.setId(Integer.parseInt((String) o2.get("id")));
						if (Integer.parseInt((String) o2.get("id")) == 0)
							return null;

						user.setDevice_id((String) o2.get("device_id"));
						user.setNickname((String) o2.get("nickname"));
						user.setTag((String) o2.get("tag"));
						user.setScore_dynamic(Integer.parseInt((String) o2
								.get("score_dynamic")));
						user.setScore_static(Integer.parseInt((String) o2
								.get("score_static")));
						user.setTwitter((String) o2.get("twitter"));
						user.setFacebook((String) o2.get("facebook"));

						user.setIs_nickname_initialized((String) o2
								.get("is_nickname_initialized"));
						user.setIs_twitter_visible((String) o2
								.get("is_twitter_visible"));
						user.setIs_facebook_visible((String) o2
								.get("is_facebook_visible"));

					

					// Map response2 = (Map)o.get("GameMatchSchedules");
					// JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
					// System.out.println(o2.entrySet().size());

					// //System.out.println(response2.get("FPGameMatchSchedule"));
				}
			}
			httpget.abort();
			System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();

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

		return user;
	}

	public static List<FPUser> getUsers() {
		FPUser user = null;

		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPUser> users = new ArrayList<FPUser>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.readUser.do");

			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// ?? ??
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				
				
				String linePart = "";
				String line = "";
				while ((linePart = rd.readLine()) != null) {
					line += linePart;
				}
					JSONParser j = new JSONParser();
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("users");
					for (int i = 0; i < lang.size(); i++) {
						user = new FPUser();
						JSONObject o2 = (JSONObject) lang.get(i);

						user.setId(Integer.parseInt((String) o2.get("id")));
						if (Integer.parseInt((String) o2.get("id")) == 0)
							return null;

						user.setDevice_id((String) o2.get("device_id"));
						user.setNickname((String) o2.get("nickname"));
						user.setTag((String) o2.get("tag"));
						user.setScore_dynamic(Integer.parseInt((String) o2
								.get("score_dynamic")));
						user.setScore_static(Integer.parseInt((String) o2
								.get("score_static")));
						user.setTwitter((String) o2.get("twitter"));
						user.setFacebook((String) o2.get("facebook"));

						users.add(user);
					}

					// Map response2 = (Map)o.get("GameMatchSchedules");
					// JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
					// System.out.println(o2.entrySet().size());

					// //System.out.println(response2.get("FPGameMatchSchedule"));
				
			}
			httpget.abort();
			System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();
			return users;
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

		return users;
	}

	public static List<FPUser> getRankingUsers() {
		return getRankingUsers(new FPUser());
	}

	public static List<FPUser> getRankingUsers(FPUser userParam) {
		FPUser user = null;

		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPUser> users = new ArrayList<FPUser>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.readUserRanking.do"
					+ userParam.toStringSealize());

			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// ?? ??
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				String lineAll = "";
				String line = "";
				while ((line = rd.readLine()) != null) {
					lineAll += line;
				}
				JSONParser j = new JSONParser();
				System.out.println("lineAll:" + lineAll);
				JSONObject o = (JSONObject) j.parse(lineAll);
				JSONArray lang = (JSONArray) o.get("users");
				for (int i = 0; i < lang.size(); i++) {
					user = new FPUser();
					JSONObject o2 = (JSONObject) lang.get(i);

					user.setId(Integer.parseInt((String) o2.get("id")));
					if (Integer.parseInt((String) o2.get("id")) == 0)
						return null;

					user.setRank(Integer.parseInt((String) o2.get("rank")));
					user.setRight_prophet_num(Integer.parseInt((String) o2
							.get("right_prophet_num")));
					user.setRight_prophet_ratio(Float.parseFloat((String) o2
							.get("right_prophet_ratio")));
					user.setProphet_num(Integer.parseInt((String) o2
							.get("prophet_num")));
					user.setDevice_id((String) o2.get("device_id"));
					user.setNickname((String) o2.get("nickname"));
					user.setTag((String) o2.get("tag"));
					user.setScore_dynamic(Integer.parseInt((String) o2
							.get("score_dynamic")));
					user.setScore_static(Integer.parseInt((String) o2
							.get("score_static")));
					user.setTwitter((String) o2.get("twitter"));
					user.setFacebook((String) o2.get("facebook"));
					user.setIs_facebook_visible((String) o2
							.get("is_facebook_visible"));
					user.setIs_twitter_visible((String) o2
							.get("is_twitter_visible"));

					users.add(user);

					// Map response2 = (Map)o.get("GameMatchSchedules");
					// JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
					// System.out.println(o2.entrySet().size());

					// //System.out.println(response2.get("FPGameMatchSchedule"));
				}
			}
			httpget.abort();
			System.out.println("----------------------------------------");
			httpclient.getConnectionManager().shutdown();
			return users;
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

		return users;
	}

	public static boolean initializeUser(FPUser user)
			throws UnsupportedEncodingException {

		System.out.println("Here is initialization. :" + user);

		FPUser curUser = getUserByDeviceId(user.getDevice_id());
		System.out.println(">>>" + curUser);
		if (NICK_ALREADY_INITIALIZED.equals(curUser
				.getIs_nickname_initialized())) {
			return false;
		}

		user.setIs_nickname_initialized(NICK_ALREADY_INITIALIZED);
		return updateUser(user);
	}

	public static boolean updateUser(FPUser user)
			throws UnsupportedEncodingException {
		user.setNickname(URLEncoder.encode(user.getNickname(), "UTF-8"));
		user.setTag(URLEncoder.encode(user.getTag(), "UTF-8"));
		// FPUser user = new FPUser();

		HttpClient httpclient = new DefaultHttpClient();

		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.updateUser.do"
					+ user.toStringSealize());

			System.out.println("executing request " + httpget.getURI());
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();

			System.out.println("----------------------------------------");
			// ?? ??
			System.out.println(response.getStatusLine());
			if (entity != null) {
				System.out.println("Response content length: "
						+ entity.getContentLength());
				BufferedReader rd = new BufferedReader(new InputStreamReader(
						response.getEntity().getContent()));

				
				String linePart = "";
				String line = "";
				while ((linePart = rd.readLine()) != null) {
					line += linePart;
				}
				
				
					if (line.startsWith("fail")) {
						// Error handling
						return false;
					} else if (line.equals("success")) {
						return true;
					} else {
						return false;
					}
				
			}
			httpget.abort();
			System.out.println("----------------------------------------");
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

	public static void main(String[] args) throws ParseException,
			UnsupportedEncodingException {

		// System.out.println(UserService.isUserExistByNickname("abcde"));
		// System.out.println(UserService.isUserExistByDeviceId("qwert"));
		// System.out.println(UserService.getUserByDeviceId("qwert"));
		//

		// System.out.println(UserService.isUserExistByDeviceId(String.format("%010d",
		// 100)));

		int test_case = 3;

		if (test_case == 1) {
			// 0000000140
			FPUser user = UserService.getUserByDeviceIdEx("0000000140");
			System.out.println(user);

		} else if (test_case == 2) {
			FPUser userupdate = new FPUser();
			userupdate.setDevice_id("000000000000000");
			// userupdate.setNickname("한글닉넴이지롱2555521");
			userupdate.setTag("tag123한글도 있음 ");
			updateUser(userupdate);
			// initializeUser(userupdate);
		} else if (test_case == 3) {
			System.out.println(UserService.getRankingUsers());
		} else if (test_case == 4) {
			for (int i = 200; i < 250; i++) {
				FPUser user = new FPUser();
				user.setNickname("한글닉넴_" + Integer.toString(i));
				user.setDevice_id(String.format("%010d", i));
				UserService.addUser(user);
			}
		} else if (test_case == 5) {
			FPUser user = new FPUser();
			user.setId(67);
			System.out.println(UserService.getRankingUsers(user));
		}

		// UserService.updateUser(userupdate);

		// for(int i = 0 ; i < 10 ; i++){
		// String str = new java.rmi.dgc.VMID().toString();
		// System.out.println(str.substring(str.length()-10, str.length()));
		// }

		// List<FPUser> users = UserService.getUsers();
		// System.out.println(users);
		//
		// //if(1==1) return;
		// FPUser userUpdate = new FPUser();
		// userUpdate.setNickname("aaaabc345345de");
		// userUpdate.setDevice_id("000000005");
		// userUpdate.setScore_dynamic(100);
		// userUpdate.setScore_static(1000);
		//
		// UserService.updateUser(userUpdate);

		// FPUser readUser = UserService.getUserByDeviceId("1111");
		// System.out.println(readUser);

		// // HttpClient ??
		// HttpClient httpclient = new DefaultHttpClient();
		// try {
		// // HttpGet??
		// HttpGet httpget = new HttpGet(
		// Env.url + "api.readUser.do");
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
		// for (int i = 0; i < lang.size(); i++) {
		//
		// //System.out.println("The " + i
		// // + " element of the array: " + lang.get(i));
		// JSONObject o2 = (JSONObject) lang.get(i);
		// System.out.println(o2.get("reference_time11"));
		//
		// }
		//
		// // Map response2 = (Map)o.get("GameMatchSchedules");
		// // JSONObject o2 = (JSONObject) o.get("GameMatchSchedules");
		// // System.out.println(o2.entrySet().size());
		//
		// // //System.out.println(response2.get("FPGameMatchSchedule"));
		// }
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
	}

}