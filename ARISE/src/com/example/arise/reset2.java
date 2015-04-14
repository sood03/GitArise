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
import android.widget.TextView;
import android.widget.Toast;

public class reset2 extends Activity {
	
	EditText pass1;
	EditText pass2;
	String password1;
	String password2;
	String response;
	String name;
	
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.reset2);
        TextView tv = (TextView) findViewById(R.id.textView3);
        tv.setText(getIntent().getExtras().getString("thename"));
        name = tv.getText().toString();
        
        pass1 = (EditText) findViewById(R.id.editText1);
        pass2 = (EditText) findViewById(R.id.editText2);
        
        Button sub = (Button) findViewById(R.id.button1);
        sub.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				password1 = pass1.getText().toString();
				password2 = pass2.getText().toString();
				
				if(password1.equals(password2))
				{
					try
		            {
			            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("name", name));
					nameValuePairs.add(new BasicNameValuePair("password", password1));
					
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://arise.host56.com/test/reset2.php");
					httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					HttpResponse httpResponse = httpclient.execute(httppost);
			        HttpEntity resEntityGet = httpResponse.getEntity();  
			        	
			        if (resEntityGet != null) 
			        {  
			                    //do something with the response
	                    		response = EntityUtils.toString(resEntityGet);

			                    Log.i("GET RESPONSE",response);
			                    Log.d("reg2", response);
			        }
			        

		           } 
					catch(Exception e){}
							        
				        Intent intent = new Intent(reset2.this, MainActivity.class);
				        startActivity(intent);
				    
					}
				
				else
				{
					Toast.makeText(getApplicationContext(), "Passwords donot match", Toast.LENGTH_LONG).show();
				}
				}
			
		});

}
}
