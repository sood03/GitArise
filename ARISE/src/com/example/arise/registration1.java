package com.example.arise;

import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;


public class registration1 extends Activity implements OnCheckedChangeListener {
	
	
	EditText fname;
	EditText lname;
	String f_name;
	String l_name;
	String response;
	String sex;
	String date;
	RadioGroup rg;
	DatePicker dp;
	int year;
	int month;
	int day;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration1);		       
        
        rg = (RadioGroup) findViewById(R.id.radiogrp);
        rg.setOnCheckedChangeListener(this);       		
        
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		
		switch (checkedId){
		case R.id.radioMale:
			sex = "male";
			break;
		case R.id.radioFemale:
			sex = "female";
			break;
		}
		dp = (DatePicker) findViewById(R.id.datePicker1);
		Button b = (Button) findViewById(R.id.button1);
		fname = (EditText)findViewById(R.id.editText1);
        lname = (EditText)findViewById(R.id.editText2);
		
		b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				f_name = fname.getText().toString();
				l_name = lname.getText().toString();
				year = dp.getYear();
				month = dp.getMonth()+1;
				day = dp.getDayOfMonth();
				date = ""+year+"-"+month+"-"+day;
				
				
				// Using HttpGet
				try
				{
		            HttpClient client = new DefaultHttpClient();  
		            f_name = URLEncoder.encode(f_name, "utf-8");
		            l_name = URLEncoder.encode(l_name, "utf-8");
		            sex = URLEncoder.encode(sex, "utf-8");
		            date = URLEncoder.encode(date, "utf-8");
			        String getURL = "http://arise.host56.com/test/reg1.php"+"?fname="+f_name+"&lname="+l_name+"&sex="+sex+"&date="+date;
			        HttpGet get = new HttpGet(getURL);
			        HttpResponse responseGet = client.execute(get);  
			        HttpEntity resEntityGet = responseGet.getEntity();  
			        if (resEntityGet != null) 
			        {  
			                    //do something with the response
		                		response = EntityUtils.toString(resEntityGet);
			                    Log.i("see names",response);
			                    Log.d("adding names", response);
			        }			        
			        else {}
			        
				}   
		        catch(Exception e)
		        {}
				
				startActivity(new Intent(registration1.this, registration2.class));
				finish();
				
			}
		});
		
	}
}
