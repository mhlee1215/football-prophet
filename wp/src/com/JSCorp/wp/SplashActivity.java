package com.JSCorp.wp;

import com.JSCorp.wp.SplashActivity.GetUserInfo;
import com.JSCorp.wp.domain.FPAppInfo;
import com.JSCorp.wp.domain.FPUser;
import com.JSCorp.wp.service.GameService;
import com.JSCorp.wp.service.UserService;
import com.JSCorp.wp.var.GlobalVars;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
 
public class SplashActivity extends Activity {
	
	boolean isUserChecked = false;
	boolean isAppChecked = false;
 
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
		//System.out.println(user);
	}
    
    public void goMainActivity(){
    	// METHOD 1     
        
        /****** Create Thread that will sleep for 5 seconds *************/        
       Thread background = new Thread() {
           public void run() {
               
        	   
        	   
               try {
            	   sleep((long) (1.0*1000));
                   // Thread will sleep for 5 seconds
            	   boolean isCheckedAll = true;
            	   
            	   do{
	            	   if(!isAppChecked) isCheckedAll = false;
	            	   if(!isUserChecked) isCheckedAll = false;
	            	   
	                   sleep((long) (0.5*1000));
                   
            	   }while(!isCheckedAll);
                    
                   // After 5 seconds redirect to another intent
            	   
            	   if("Y".equals(GlobalVars.user.getIs_nickname_initialized())){
            		   Intent i=new Intent(getBaseContext(),MainActivity.class);
                       startActivity(i);
                       //Remove activity
                       finish();   
            	   }else{ 
            		   Intent i=new Intent(getBaseContext(),EditNicknameActivity.class);
            		   
            		   Bundle b = new Bundle();
            		   b.putString("isAfterSplash", "Y"); //Your id
            		   i.putExtras(b); //Put your id to your next Intent
            		   
                       startActivity(i);
                       //Remove activity
                       finish();
            	   }
            	   
                   
                    
               } catch (Exception e) {
                
               }
           }
       };
        
       // start thread
       background.start();
    }
    
    @SuppressLint("NewApi")
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
				
				Display display = ((WindowManager) this.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
				Point size = new Point();
		        display.getSize(size); 
		        int width = size.x;
		        int height = size.y;
				WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
				
				final Dialog dialog = new Dialog(this);
			    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
			    dialog.setContentView(R.layout.update_notification_dialog);
			     
			    lp.copyFrom(dialog.getWindow().getAttributes());
			    lp.width = WindowManager.LayoutParams.MATCH_PARENT;
			    lp.height = 400;//height - (height / 3);
			    lp.y = 0;//height/2;// - lp.height/2;
			    System.out.println("height: " + lp.height);
			    
			    dialog.getWindow().setAttributes(lp);
			    
			    LinearLayout layout = (LinearLayout) dialog.findViewById(R.id.update_linearlayout);
			    
			    Button updateConfirmButton = (Button) layout.findViewById(R.id.btnUpdateConfirm);
			    
			    updateConfirmButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String appPackageName = "com.JSCorp.wp";
						String url = "https://play.google.com/store/apps/details?id=" + appPackageName;
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(url));
						startActivity(intent);
					}
				});
			    
			    
				
			    dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
			    dialog.show();
			    
//				String appPackageName = "com.JSCorp.wc";
//				String url = "https://play.google.com/store/apps/details?id=" + appPackageName;
//				Intent intent = new Intent(Intent.ACTION_VIEW);
//				intent.setData(Uri.parse(url));
//				startActivity(intent);
				//Toast.makeText(getApplicationContext(), "Need to update!!!", Toast.LENGTH_SHORT).show();
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
			GlobalVars.appInfo = appInfo;
			isAppChecked = true;
			
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
			//String deviceId = "arsenal12";
			//deviceId = "test_device_id2";
			System.out.println("device Id :"+deviceId);
			
			user = UserService.getUserByDeviceIdEx(deviceId);
			if(GlobalVars.isDebugMode)
				//System.out.println("user: "+user);
			
			if(user == null){
				//add user
				if(GlobalVars.isDebugMode)
					Log.i(GlobalVars.WP_INFO_TAG, "ADD USER");
				FPUser addUser = new FPUser();
				//System.out.println(addUser);
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
				
				user = UserService.getUserByDeviceIdEx(deviceId);
				GlobalVars.user = user;
			}else{
				Log.i(GlobalVars.WP_INFO_TAG, "USER ALREADY EXISTS");
				GlobalVars.user = user;
				if(GlobalVars.isDebugMode)
					System.out.println("USER ALREADY EXISTS//user: "+user);
			}
			isUserChecked = true;
			
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