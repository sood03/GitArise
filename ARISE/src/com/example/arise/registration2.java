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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class registration2 extends Activity {
	
	EditText mail;
	EditText number;
	EditText uname;
	EditText paswd;
	EditText cnpwd;
	String f_mail;
	String f_num;
	String f_uname;
	String f_paswd;
	String f_cnpwd;
	String response;
	String check;
	String check2;
	String check3;
	
	private void isValidEmail(String email)
	 {
	     String emailRegex ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";	     
	     check = (email.matches(emailRegex)) ? "ok" : "not ok";	     
	 }
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration2);		
		
		mail = (EditText) findViewById(R.id.editText1);
		number = (EditText) findViewById(R.id.editText2);
		uname = (EditText) findViewById(R.id.editText3);
		paswd = (EditText) findViewById(R.id.editText4);
		cnpwd = (EditText) findViewById(R.id.editText5);
		
		Button b = (Button) findViewById(R.id.button1);
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				check3="ok";
				f_mail = mail.getText().toString();
				f_num = number.getText().toString();
				f_uname = uname.getText().toString();
				f_paswd = paswd.getText().toString();
				f_cnpwd = cnpwd.getText().toString();
				
				isValidEmail(f_mail);
				
				if(f_uname.length() == 0 || f_paswd.length() == 0)
				{
					Toast.makeText(getApplicationContext(), "Username or password is empty", Toast.LENGTH_LONG).show();
					check3 = "not ok";
				}
				
				if(f_paswd.equals(f_cnpwd)){
					check2 = "ok";
				}
				else{
					check2 = "not ok";
				}
				
				if(check.equals("ok") & check2.equals("ok") & (!check3.equals("not ok")))
				{	
					try
		            {
			            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
					nameValuePairs.add(new BasicNameValuePair("email", f_mail));
					nameValuePairs.add(new BasicNameValuePair("contact", f_num));
					nameValuePairs.add(new BasicNameValuePair("uname", f_uname));
					nameValuePairs.add(new BasicNameValuePair("pwd", f_paswd));
					
					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost("http://arise.host56.com/test/reg2.php");
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
	            
					startActivity(new Intent(registration2.this, registration3.class));
					finish();
				}				
				else{
					if(check.equals("not ok"))
						Toast.makeText(getApplicationContext(), "Enter valid email address", Toast.LENGTH_LONG).show();
					if(check2.equals("not ok"))
						Toast.makeText(getApplicationContext(), "Passwords donot match!!", Toast.LENGTH_LONG).show();
				}
				
		}
			
		});
	
	}
}
