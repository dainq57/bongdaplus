package com.dtui.footballfunny;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dtui.bongdaplus.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.Provider;
import com.google.android.youtube.player.YouTubePlayerView;

public class PlayVideo extends YouTubeBaseActivity implements
		YouTubePlayer.OnInitializedListener {

	private TextView title;
	private ImageButton back;
	
	static private final String DEVELOPER_KEY = "AIzaSyB96Ak0ZN7wj11ZZaNGihxLy2-JeJ0mdg4";
	static private String VIDEO;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.videoview);

		getActionBar().hide();
		
		title = (TextView) findViewById(R.id.title_video_funny);
		back = (ImageButton) findViewById(R.id.ibBack);
		
		Intent in = getIntent();
		
		VIDEO = in.getStringExtra("youtubeID") ;
		title.setText(in.getStringExtra("youtubeTITLE"));
		
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		YouTubePlayerView youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);
		youTubeView.initialize(DEVELOPER_KEY, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onInitializationFailure(Provider arg0,
			YouTubeInitializationResult error) {
		Toast.makeText(this, "Error : " + error.toString(), Toast.LENGTH_LONG)
				.show();

	}

	@Override
	public void onInitializationSuccess(Provider arg0, YouTubePlayer player,
			boolean arg2) {
		player.loadVideo(VIDEO);

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
