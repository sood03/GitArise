package com.example.arise;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnBufferingUpdateListener;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class Play extends Activity implements OnClickListener, OnTouchListener, OnCompletionListener, OnBufferingUpdateListener{

	private ImageButton buttonPlayPause;
	private SeekBar seekBarProgress;
	public EditText editTextSongURL;
	private MediaPlayer mediaPlayer;
	private int mediaFileLengthInMilliseconds; // this value contains the song duration in milliseconds. Look at getDuration() method in MediaPlayer class
	private final Handler handler = new Handler();
	/** Called when the activity is first created. */

	String folder;
	String songname;
	String URL;
	String response;
	int length;
	int flag = 0;
	@Override

	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.play);
		Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        folder = extra.getString("foldername");
        songname = extra.getString("song");	
        TextView tt = (TextView) findViewById(R.id.textView1);
        tt.setText(songname);
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setVisibility(View.INVISIBLE);
        initView();

	}
	
/** This method initialise all the views in project*/

	private void initView() {
		
		buttonPlayPause = (ImageButton)findViewById(R.id.ButtonTestPlayPause);
		buttonPlayPause.setOnClickListener(this);
		seekBarProgress = (SeekBar)findViewById(R.id.seekBar1); 
		seekBarProgress.setMax(99); // It means 100% .0-99
		seekBarProgress.setOnTouchListener(this);		
		mediaPlayer = new MediaPlayer();
		mediaPlayer.setOnBufferingUpdateListener(this);
		mediaPlayer.setOnCompletionListener(this);
	}

/** Method which updates the SeekBar primary progress by current song playing position*/

	@Override

	public void onClick(View v) {
		if(v.getId() == R.id.ButtonTestPlayPause){
			if(flag==0){
				Toast.makeText(getApplicationContext(), "Audio will play in a moment", Toast.LENGTH_LONG).show();
				flag = 1;
			}
			
			
			
		/** ImageButton onClick event handler. Method which start/pause mediaplayer playing */
		try {	        
			URL = "http://arise.host56.com/music/"+folder+"/"+songname+".mp3";
			mediaPlayer.setDataSource(URL); // setup song from http://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3 URL to mediaplayer data source
			mediaPlayer.prepare(); // you must call this method after setup the datasource in setDataSource method. After calling prepare() the instance of MediaPlayer starts load data from URL to internal buffer.
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		mediaFileLengthInMilliseconds = mediaPlayer.getDuration(); // gets the song length in milliseconds from URL
		length = mediaFileLengthInMilliseconds/1000;
		int minutes = length/60;
		int seconds = length%60;
		TextView tv = (TextView) findViewById(R.id.textView2);
		tv.setVisibility(View.VISIBLE);
		tv.setText(String.valueOf(minutes)+":"+String.valueOf(seconds));
		if(!mediaPlayer.isPlaying()){
			mediaPlayer.start();
			buttonPlayPause.setImageResource(R.drawable.pause);
		}else {
			mediaPlayer.pause();
			buttonPlayPause.setImageResource(R.drawable.play);
		}
		
		primarySeekBarProgressUpdater();
	}
}

 

@Override

	public boolean onTouch(View v, MotionEvent event) {
		if(v.getId() == R.id.seekBar1){
			/** Seekbar onTouch event handler. Method which seeks MediaPlayer to seekBar primary progress position*/
			if(mediaPlayer.isPlaying()){
				SeekBar sb = (SeekBar)v;
				int playPositionInMillisecconds = (mediaFileLengthInMilliseconds / 100) * sb.getProgress();
				mediaPlayer.seekTo(playPositionInMillisecconds);
			}
		}
		return false;
	}

 

@Override

	public void onCompletion(MediaPlayer mp) {
	/** MediaPlayer onCompletion event handler. Method which calls then song playing is complete*/
		buttonPlayPause.setImageResource(R.drawable.play);
	}

@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_BACK) {
    		mediaPlayer.pause();
    		super.onBackPressed();
    		return true;
    	}
    	return super.onKeyDown(keyCode, event);
	}
 

@Override

	public void onBufferingUpdate(MediaPlayer mp, int percent) {
		/** Method which updates the SeekBar secondary progress by current song loading from URL position*/
		seekBarProgress.setSecondaryProgress(percent);
	}

	private void primarySeekBarProgressUpdater() {

	seekBarProgress.setProgress((int)(((float)mediaPlayer.getCurrentPosition()/mediaFileLengthInMilliseconds)*100)); // This math construction give a percentage of "was playing"/"song length"
	if (mediaPlayer.isPlaying()) {
		Runnable notification = new Runnable() {

			public void run() {
				primarySeekBarProgressUpdater();
			}
		};	
		handler.postDelayed(notification,1000);
	}
}
}
