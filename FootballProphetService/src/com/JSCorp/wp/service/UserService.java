package com.JSCorp.wp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.var.Env;

public class UserService {

	/**
	 * @param args
	 * @throws ParseException
	 */

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
	
	public static boolean addUser(FPUser user) {
		
		if(isUserExistByDeviceId(user.getDevice_id()))
			return false;
		
		if(user.getNickname() == null || "".equals(user.getNickname())){
			//Generate pseudo-random string
			String str = UUID.randomUUID().toString();//new java.rmi.dgc.VMID().toString();
			str = str.replace(":", "").replace("-", "").toUpperCase();
			user.setNickname("NONAME_"+str.toString().substring(str.length()-10, str.length()));
		}
		
		if(user.getIs_nickname_initialized() == null || "".equals(user.getIs_nickname_initialized())){
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
	
	

	public static FPUser getUserByDeviceId(String device_id) {
		FPUser user = null;
		
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.readUser.do"+"?device_id="+device_id);

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

				String line = "";
				while ((line = rd.readLine()) != null) {
					JSONParser j = new JSONParser();
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("users");
					for (int i = 0; i < lang.size(); i++) {
						user = new FPUser();
						JSONObject o2 = (JSONObject) lang.get(i);
						
						user.setId(Integer.parseInt((String)o2.get("id")));
						if(Integer.parseInt((String)o2.get("id")) == 0)
							return null;
						
						
						user.setDevice_id((String)o2.get("device_id"));
						user.setNickname((String)o2.get("nickname"));
						user.setTag((String)o2.get("tag"));
						user.setScore_dynamic(Integer.parseInt((String)o2.get("score_dynamic")));
						user.setScore_static(Integer.parseInt((String)o2.get("score_static")));
						user.setTwitter((String)o2.get("twitter"));
						user.setFacebook((String)o2.get("facebook"));
					}

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

				String line = "";
				while ((line = rd.readLine()) != null) {
					JSONParser j = new JSONParser();
					System.out.println("line:" + line);
					JSONObject o = (JSONObject) j.parse(line);
					JSONArray lang = (JSONArray) o.get("users");
					for (int i = 0; i < lang.size(); i++) {
						user = new FPUser();
						JSONObject o2 = (JSONObject) lang.get(i);
						
						user.setId(Integer.parseInt((String)o2.get("id")));
						if(Integer.parseInt((String)o2.get("id")) == 0)
							return null;
						
						
						user.setDevice_id((String)o2.get("device_id"));
						user.setNickname((String)o2.get("nickname"));
						user.setTag((String)o2.get("tag"));
						user.setScore_dynamic(Integer.parseInt((String)o2.get("score_dynamic")));
						user.setScore_static(Integer.parseInt((String)o2.get("score_static")));
						user.setTwitter((String)o2.get("twitter"));
						user.setFacebook((String)o2.get("facebook"));
						
						users.add(user);
					}

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
	
	public static boolean updateUser(FPUser user) {
		//FPUser user = new FPUser();
		
		HttpClient httpclient = new DefaultHttpClient();
		
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url + "api.updateUser.do"+user.toStringSealize());

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
	
	

	public static void main(String[] args) throws ParseException {

		//System.out.println(UserService.isUserExistByNickname("abcde"));
		//System.out.println(UserService.isUserExistByDeviceId("qwert"));
		//System.out.println(UserService.getUserByDeviceId("qwert"));
//		
		
		//System.out.println(UserService.isUserExistByDeviceId(String.format("%010d", 100)));
		
		for(int i=140 ; i < 150 ; i++){
			FPUser user = new FPUser();
//			user.setNickname("abcdeee4");
			user.setDevice_id(String.format("%010d", i));
			UserService.addUser(user);	
		}
		
//		for(int i = 0 ; i < 10 ; i++){
//			String str = new java.rmi.dgc.VMID().toString();
//		System.out.println(str.substring(str.length()-10, str.length()));
//		}
		
		
//		List<FPUser> users = UserService.getUsers();
//		System.out.println(users);
//		
//		//if(1==1) return;
//		FPUser userUpdate = new FPUser();
//		userUpdate.setNickname("aaaabc345345de");
//		userUpdate.setDevice_id("000000005");
//		userUpdate.setScore_dynamic(100);
//		userUpdate.setScore_static(1000);
//		
//		UserService.updateUser(userUpdate);
		
		
		//FPUser readUser = UserService.getUserByDeviceId("1111");
		//System.out.println(readUser);

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