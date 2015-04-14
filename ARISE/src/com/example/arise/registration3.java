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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

public class registration3 extends Activity {
	
	
	EditText country;
	EditText qual;
	String f_country;
	String f_qual;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.registration3);
		
		Button b = (Button) findViewById(R.id.button1);
		
		final AutoCompleteTextView tv = (AutoCompleteTextView) findViewById(R.id.autocomplete1);
		String[] countries = getResources().getStringArray(R.array.countries_array);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countries);
		tv.setAdapter(adapter);
		
		qual = (EditText) findViewById(R.id.editText2);
		b.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				f_country = tv.getText().toString();
				f_qual = qual.getText().toString();
				
				try
				{
		            HttpClient client = new DefaultHttpClient();  
		            f_country = URLEncoder.encode(f_country, "utf-8");
		            f_qual = URLEncoder.encode(f_qual, "utf-8");
		            String getURL = "http://arise.host56.com/test/reg3.php"+"?county="+f_country+"&quali="+f_qual;
			        HttpGet get = new HttpGet(getURL);
			        HttpResponse responseGet = client.execute(get);  
			        HttpEntity resEntityGet = responseGet.getEntity();  
			        if (resEntityGet != null) 
			        {  
			                    //do something with the response
		                		String response = EntityUtils.toString(resEntityGet);
			                    Log.i("see names",response);
			                    Log.d("adding names", response);
			        }	
			        else {}
				}   
		        catch(Exception e)
		        {}
				startActivity(new Intent(registration3.this, MainActivity.class));
				finish();
			}
		});
	}

}
