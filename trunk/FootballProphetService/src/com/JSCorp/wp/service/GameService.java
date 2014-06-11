package com.JSCorp.wp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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
import com.JSCorp.wp.var.Env;

public class GameService {

	public static ArrayList<FPGameMatchSchedule> getGameMatchSchedules() {
		// HttpClient ??
		HttpClient httpclient = new DefaultHttpClient();
		ArrayList<FPGameMatchSchedule> matches = new ArrayList<FPGameMatchSchedule>();
		try {
			// HttpGet??
			HttpGet httpget = new HttpGet(Env.url
					+ "api.readGameMatchSchedule.do");

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
							matchSchedule
									.setAway_team_id(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("away_team_id")) != null) {
							matchSchedule
									.setAway_team_id(Integer.parseInt(str));
						}
						if ((str = (String) o2.get("city")) != null) {
							matchSchedule.setCity(str);
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
			System.out.println("----------------------------------------");
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

	public static void main(String[] args) throws ParseException {

		System.out.println(GameService.getGameMatchSchedules().size());
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