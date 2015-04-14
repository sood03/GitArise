package com.example.arise;

import java.util.List;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;


public class main extends ListActivity implements FetchDataListener{
    private ProgressDialog dialog;
     
       
    String song_name;
    String username;
    String folder;
    
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.group1); 
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        folder = extra.getString("foldername");
        username = extra.getString("username");
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(username);
//        Toast.makeText(getApplicationContext(), folder , Toast.LENGTH_LONG).show();
        ListView mlist = (ListView) findViewById(android.R.id.list);
        mlist.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
        	{        	    	
        			song_name = ((TextView) view.findViewById(R.id.textView1)).getText().toString();
        	        Intent in = new Intent(main.this, Play.class);        	        
        	        Bundle ext = new Bundle();
        	        ext.putString("song", song_name);
        	        ext.putString("foldername", folder);
        	        in.putExtras(ext);
        	        startActivity(in);
        	}
        }); 
        initView();          
        }
 
    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");
         
        String url = "http://arise.host56.com/test/list1.php";
        FetchDataTask task = new FetchDataTask(this);
        
        
        task.execute(url,folder);        
    }
     
    @Override
    public void onFetchComplete(List<application> data) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        applicationAdapter adapter = new applicationAdapter(this, data);
        // set the adapter to list
        setListAdapter(adapter);
        
       
    }
 
    @Override
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // show failure message
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();       
    }
}
 