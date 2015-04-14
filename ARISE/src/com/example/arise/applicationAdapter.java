package com.example.arise;

import java.util.List;


import android.widget.ArrayAdapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class applicationAdapter extends ArrayAdapter<application> {
	private List<application> items;
    
    public applicationAdapter(Context context, List<application> items) {
        super(context, R.layout.custom_list, items);
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
            v = li.inflate(R.layout.custom_list, null);           
        }
         
        application app = items.get(position);
         
        if(app != null) {
            
            TextView titleText = (TextView)v.findViewById(R.id.textView1);
            TextView lengthText = (TextView) v.findViewById(R.id.textView2);            
                        
            if(titleText != null) titleText.setText(app.getTitle());            
            if(lengthText != null) lengthText.setText(app.getLength());        
            
        }         
        return v;
    }
}


