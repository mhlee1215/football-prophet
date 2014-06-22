package com.JSCorp.wp;

import com.JSCorp.wp.SplashActivity.GetUserInfo;
import com.JSCorp.wp.domain.FPAppInfo;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
 
public class SplashActivity extends Activity {
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        new GetAppInfo().execute(this);
        new GetUserInfo().execute(this);
        
        //if(1==1) return;
    }
     
    @Override
    protected void onDestroy() {
         
        super.onDestroy();
         
    }
    
    public void doPrintUser(FPUser user){
		System.out.println(user);
	}
    
    public void goMainActivity(){
    	// METHOD 1     
        
        /****** Create Thread that will sleep for 5 seconds *************/        
       Thread background = new Thread() {
           public void run() {
                
               try {
                   // Thread will sleep for 5 seconds
                   sleep(3*1000);
                    
                   // After 5 seconds redirect to another intent
                   Intent i=new Intent(getBaseContext(),MainActivity.class);
                   startActivity(i);
                    
                   //Remove activity
                   finish();
                    
               } catch (Exception e) {
                
               }
           }
       };
        
       // start thread
       background.start();
    }
    
    public void doVersionCheck(FPAppInfo appInfo){
    	
    	
    	PackageInfo pInfo = null;
		try {
			pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(pInfo != null){
			String version = pInfo.versionName;
			if(GlobalVars.isDebugMode){
				System.out.println("VERSION CHECK!"+appInfo);
				System.out.println("ANDROID CHECK!"+pInfo.versionCode+", "+pInfo.versionName);
			}
			
			if(!version.equalsIgnoreCase(appInfo.getVersion_name())){
				//Do update!
				Toast.makeText(getApplicationContext(),
	        		      "Need to update!!!", Toast.LENGTH_SHORT).show();
			}else{
				goMainActivity();
			}
			
		}else{
			//Fail to get app info.
			if(GlobalVars.isDebugMode){
				System.err.println("Fail to get appinfo from device");
			}
		}
    	
    }
    
    public class GetAppInfo extends AsyncTask {

    	FPAppInfo appInfo;
    	SplashActivity tContext;
    	
		@Override
		protected FPAppInfo doInBackground(Object... arg0) {
			tContext = (SplashActivity) arg0[0];
			appInfo = GameService.getAppInfo();
			if(GlobalVars.isDebugMode)
				System.out.println("appInfo : "+appInfo);
			return appInfo;
		}
    	
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tContext.doVersionCheck(appInfo);
		}
    }
	
	public class GetUserInfo extends AsyncTask {

		SplashActivity tContext;
		FPUser user;
		@Override
		protected Object doInBackground(Object... arg0) {
			tContext = (SplashActivity) arg0[0];
			// TODO Auto-generated method stub
			
			TelephonyManager tManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
			String deviceId = tManager.getDeviceId();
			System.out.println("device Id :"+deviceId);
			
			user = UserService.getUserByDeviceId(deviceId);
			if(GlobalVars.isDebugMode)
				System.out.println("user: "+user);
			
			if(user == null){
				//add user
				if(GlobalVars.isDebugMode)
					Log.i(GlobalVars.WP_INFO_TAG, "ADD USER");
				FPUser addUser = new FPUser();
				System.out.println(addUser);
				addUser.setDevice_id(deviceId);
				boolean result;
				try {
					result = UserService.addUser(addUser);
					if(GlobalVars.isDebugMode)
						System.out.println("result : "+result);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				user = UserService.getUserByDeviceId(deviceId);
				GlobalVars.user = user;
			}else{
				Log.i(GlobalVars.WP_INFO_TAG, "USER ALREADY EXISTS");
				GlobalVars.user = user;
				if(GlobalVars.isDebugMode)
					System.out.println("USER ALREADY EXISTS//user: "+user);
			}
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Object result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			//tContext.doPrint(matches);
		}
		
	}
}