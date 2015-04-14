package com.example.arise;

import java.util.List;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class category extends ListActivity implements catFetchDataListener{
    private ProgressDialog dialog;
    // for any reference, view: http://www.semurjengkol.com/populating-android-listview-with-json-based-data-fetched-from-mysql-server-using-php/
     
    String song;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);       
        setContentView(R.layout.category);    
        TextView tv = (TextView) findViewById(R.id.textView2);
		tv.setText(getIntent().getExtras().getString("thename"));
		name = tv.getText().toString();
		ListView mlist = (ListView) findViewById(android.R.id.list);
        mlist.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) 
        	{        	    	
        			song = ((TextView) view.findViewById(R.id.textView1)).getText().toString();
        	        Intent in = new Intent(category.this, main.class);
        	        Bundle ext = new Bundle();
        	        ext.putString("username", name);
        	        ext.putString("foldername", song);
        	        in.putExtras(ext);        	               	        
        	        startActivity(in);
        	}
        }); 
        initView();  
    }
 
    private void initView() {
        // show progress dialog
        dialog = ProgressDialog.show(this, "", "Loading...");
         
        String url = "http://arise.host56.com/test/clist.php";
        catFetchDataTask task = new catFetchDataTask(this);
        task.execute(url);
    }
     
    @Override
    public void onFetchComplete(List<categoryapplication> data) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
        // create new adapter
        catapplicationAdapter adapter = new catapplicationAdapter(this, data);
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
