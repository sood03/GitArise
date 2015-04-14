package com.example.arise;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class catapplicationAdapter extends ArrayAdapter<categoryapplication> {
	
	    private List<categoryapplication> items;
	     
	    public catapplicationAdapter(Context context, List<categoryapplication> items) {
	        super(context, R.layout.cat_custom_list);
	        this.items = items;
	    }
	     
	    @Override
	    public int getCount() {
	        return items.size();
	    }
	     
	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View v = convertView;
	         
	        if(v == null) {
	            LayoutInflater li = LayoutInflater.from(getContext());
	            v = li.inflate(R.layout.cat_custom_list, null);           
	        }
	         
	        categoryapplication app = items.get(position);
	         
	        if(app != null) {
	            TextView titleText = (TextView)v.findViewById(R.id.textView1);	            
	            TextView dlText = (TextView)v.findViewById(R.id.textView2);
	             
	            	             
	            if(titleText != null) titleText.setText(app.getTitle());
	            if(dlText != null) dlText.setText(app.getLength());
	            }
	         
	        return v;
	    }
	}
