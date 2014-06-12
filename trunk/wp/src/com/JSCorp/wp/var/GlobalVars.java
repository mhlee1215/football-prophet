package com.JSCorp.wp.var;

import java.util.List;

import android.app.ProgressDialog;

import com.JSCorp.wp.domain.FPGameMatchSchedule;
import com.JSCorp.wp.domain.FPUser;

public class GlobalVars {
	public static FPUser user;
	public static List<FPGameMatchSchedule> matches;
	public static String WP_INFO_TAG = "WP_INFO_TAG";
	public static ProgressDialog dynamicBracketDialog;
}
