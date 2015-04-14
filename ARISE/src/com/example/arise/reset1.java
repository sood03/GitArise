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
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class reset1 extends Activity {
	
	EditText uname;
	EditText email;
	String user_name;
	String e_mail;
	String response;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.reset1);
        uname = (EditText) findViewById(R.id.editText1);
        email = (EditText) findViewById(R.id.editText2);
        
        Button next = (Button) findViewById(R.id.button1);
        next.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				user_name = uname.getText().toString();
				e_mail = email.getText().toString();
				
				try
	            {
	            	
	            	ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("uname", user_name));
					nameValuePairs.add(new BasicNameValuePair("mail", e_mail));
					
				//	response = CustomHttpClient.executeHttpPost("http://arise.host56.com/test/login.php", nameValuePairs);
		            HttpClient client = new DefaultHttpClient(); 			        
			        HttpPost httppost = new HttpPost("http://arise.host56.com/test/reset1.php");
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
			        
			        if(response.startsWith("1")){
			        
			        Intent intent = new Intent(reset1.this, reset2.class);
			        intent.putExtra("thename", user_name);
			        startActivity(intent);
			     
			        }
			        else 
			        	Toast.makeText(getApplicationContext(), "Invalid Username or Email id", Toast.LENGTH_LONG).show();

		            } 
	            catch(Exception e)
	            {}
			}
		});

}
}
