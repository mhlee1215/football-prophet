package com.JSCorp.wp.domain;

public class FPUser {
	int id;
	String device_id = "";
	int group_id;
	String nickname = "";
	String is_nickname_initialized = "";
	String tag = "";
	String twitter = "";
	String is_twitter_visible = "";
	String facebook = "";
	String is_facebook_visible = "";
	
	int score_dynamic;
	int score_static;
	private int query_start = 0;
	private int query_number = 0;

	public String getIs_nickname_initialized() {
		return is_nickname_initialized;
	}

	public void setIs_nickname_initialized(String is_nickname_initialized) {
		this.is_nickname_initialized = is_nickname_initialized;
	}

	public String getIs_twitter_visible() {
		return is_twitter_visible;
	}

	public void setIs_twitter_visible(String is_twitter_visible) {
		this.is_twitter_visible = is_twitter_visible;
	}

	public String getIs_facebook_visible() {
		return is_facebook_visible;
	}

	public void setIs_facebook_visible(String is_facebook_visible) {
		this.is_facebook_visible = is_facebook_visible;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public int getQuery_start() {
		return query_start;
	}

	public void setQuery_start(int query_start) {
		this.query_start = query_start;
	}

	public int getQuery_number() {
		return query_number;
	}

	public void setQuery_number(int query_number) {
		this.query_number = query_number;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public int getScore_dynamic() {
		return score_dynamic;
	}

	public void setScore_dynamic(int score_dynamic) {
		this.score_dynamic = score_dynamic;
	}

	public int getScore_static() {
		return score_static;
	}

	public void setScore_static(int score_static) {
		this.score_static = score_static;
	}


	public String toStringSealize() {
		return "?id=" + id + "&device_id=" + device_id + "&group_id="
				+ group_id + "&nickname=" + nickname
				+ "&is_nickname_initialized=" + is_nickname_initialized
				+ "&tag=" + tag + "&twitter=" + twitter
				+ "&is_twitter_visible=" + is_twitter_visible + "&facebook="
				+ facebook + "&is_facebook_visible=" + is_facebook_visible
				+ "&score_dynamic=" + score_dynamic + "&score_static="
				+ score_static + "&query_start=" + query_start
				+ "&query_number=" + query_number;
	}

	@Override
	public String toString() {
		return "{\"id\":\"" + id + "\",\"device_id\":\"" + device_id
				+ "\",\"group_id\":\"" + group_id + "\",\"nickname\":\""
				+ nickname + "\",\"is_nickname_initialized\":\""
				+ is_nickname_initialized + "\",\"tag\":\"" + tag
				+ "\",\"twitter\":\"" + twitter
				+ "\",\"is_twitter_visible\":\"" + is_twitter_visible
				+ "\",\"facebook\":\"" + facebook
				+ "\",\"is_facebook_visible\":\"" + is_facebook_visible
				+ "\",\"score_dynamic\":\"" + score_dynamic
				+ "\",\"score_static\":\"" + score_static
				+ "\",\"query_start\":\"" + query_start
				+ "\",\"query_number\":\"" + query_number + "\"}";
	}
	
	
}