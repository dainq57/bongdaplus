package com.dtui.rank;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtui.bongdaplus.R;

public class InfoRankFragment extends Activity{
	
	private ImageButton back;
	private TextView title;
	
	private TextView team;
	private ImageView image;
	
	private TextView rank;
	private TextView numberPlay;
	private TextView win;			
	private TextView draw;		
	private TextView lose;		
	private TextView numberGoal;	
	private TextView rippedNet;	
	private TextView subGoal; 	
	private TextView point; 		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_rank);
		getActionBar().hide();
		
		back  = (ImageButton) findViewById(R.id.ibBack);
		title = (TextView) findViewById(R.id.tvBack);
		
		team = (TextView) findViewById(R.id.team_back);
		image  = (ImageView) findViewById(R.id.icon_back);
		
		rank = (TextView) findViewById(R.id.rank_back);
		numberPlay = (TextView) findViewById(R.id.numberPlay_back);
		win = (TextView) findViewById(R.id.win_back);
		draw = (TextView) findViewById(R.id.draw_back);
		lose = (TextView) findViewById(R.id.lose_back);
		numberGoal = (TextView) findViewById(R.id.numberGoal_back);
		rippedNet = (TextView) findViewById(R.id.rippedNet_back);
		subGoal = (TextView) findViewById(R.id.subGoal_back);
		point = (TextView) findViewById(R.id.point_back);
		
		
		Intent in = getIntent();

		team.setText(in.getStringExtra("Team"));
		title.setText(in.getStringExtra("Team"));
		image.setImageResource(Integer.parseInt(in.getStringExtra("Icon")));
		
		rank.setText("Vị trí hạng: " + in.getStringExtra("Rank"));
		numberPlay.setText("Số trận đấu: " + in.getStringExtra("NumberPlay"));
		win.setText("Thắng: " + in.getStringExtra("Win"));
		draw.setText("Hòa: " + in.getStringExtra("Draw"));
		lose.setText("Thua: " + in.getStringExtra("Lose"));
		numberGoal.setText("Số bàn thắng: " + in.getStringExtra("NumberGoal"));
		rippedNet.setText("Thủng lưới: " + in.getStringExtra("RippedNet"));
		subGoal.setText("Hiệu số bàn: " + in.getStringExtra("SubGoal"));
		point.setText("Điểm: " + in.getStringExtra("Point"));

		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
}
