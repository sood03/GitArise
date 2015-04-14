package com.example.arise;

import java.util.ArrayList;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import android.app.AlertDialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface;

import android.view.View.OnClickListener;



public class MainActivity extends Activity {

	public void isInternetOn() 
	{
		boolean connected = false;
        ConnectivityManager connectivitymanager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkinfo = connectivitymanager.getActiveNetworkInfo();
        connected = ((networkinfo != null) && (networkinfo.isAvailable()) && (networkinfo.isConnected()));
        Log.v("Message ", connected + "");
        if (connected == false) {
        	//Toast.makeText(getApplicationContext(), "No internet connection!", Toast.LENGTH_LONG).show();
        	AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        	alertDialog.setTitle("Internet");
        	alertDialog.setMessage("No Internet Connection");
        	alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int which) {
        	// here you can add functions
        	}
        	});
        	alertDialog.setButton2("Settings", new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int which) {
            		startActivity(new Intent(Settings.ACTION_SETTINGS));
            	}
            	});
        
        	//alertDialog.setIcon(R.drawable.icon);
        	alertDialog.show();
            //finish();
        } else {
            //
        }
	}
	
	
    EditText uname;
	EditText pwd;
	Button login;
	String user_name;
	String user_pwd;
	String response;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        isInternetOn();
        uname = (EditText)findViewById(R.id.editText1);
        pwd = (EditText)findViewById(R.id.editText2);
        login = (Button)findViewById(R.id.button1);
        
        
        
        Button login = (Button) findViewById(R.id.button1);
        login.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {				
				
				//Toast.makeText(getApplicationContext(), "Checking validity", Toast.LENGTH_SHORT).show();
				
	            
	            try
	            {
	        		user_name = uname.getText().toString();
		            user_pwd = pwd.getText().toString();
		            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("uname", user_name));
					nameValuePairs.add(new BasicNameValuePair("pwd", user_pwd));
					
				//	response = CustomHttpClient.executeHttpPost("http://arise.host56.com/test/login.php", nameValuePairs);
		            HttpClient client = new DefaultHttpClient(); 			        
			        HttpPost httppost = new HttpPost("http://arise.host56.com/test/login.php");
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse httpResponse = client.execute(httppost);
			        HttpEntity resEntityGet = httpResponse.getEntity();    
			        
			        if (resEntityGet != null) 
			        {  
			                    //do something with the response
	                    		response = EntityUtils.toString(resEntityGet);
			                    Log.i("login checking",response);
			                    Log.d("response", response);
			        }
			       } 
	            catch(Exception e)
	            {
	            	if(e.getMessage().equals("empty"))					
	    				e.printStackTrace();
	            }
	           
	          //Using HttpGet
	            if(response.startsWith("1")){
			        
			        Intent intent = new Intent(MainActivity.this, category.class);
			        intent.putExtra("thename", user_name);
			        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        startActivity(intent);
			     
			        }
			        else 
			        {				        	
			        	Toast.makeText(getApplicationContext(), "Invalid User ID or Password", Toast.LENGTH_LONG).show();
			        }
	            
	            
			}
		});
        
        TextView tt = (TextView) findViewById(R.id.textView2);
        tt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, registration1.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
			}
		});
        
        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, reset1.class);
				//intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				
			}
		});      
       
    }
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	         //android.os.Process.killProcess(android.os.Process.myPid());// Kill the app on click of back button.
	    	finish();
	    	System.exit(0);
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
		
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
