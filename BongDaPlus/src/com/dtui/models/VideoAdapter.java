package com.dtui.models;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.dtui.bongdaplus.R;
import com.dtui.footballfunny.PlayVideo;
import com.squareup.picasso.Picasso;

public class VideoAdapter extends PagerAdapter {
	// Declare Variables
	private Context context;
	private ArrayList<VideoItem> listItem;
	
	static private String youtubeID;
	static private String youtubeTITLE;
	
	/*private ImageLoader mLoader;*/
	private ImageButton play;
	
	public VideoAdapter(Context context, ArrayList<VideoItem> listItem) {
		this.context = context;
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		return listItem.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((RelativeLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.draw_list_video, container,
				false);
		/*mLoader = new ImageLoader(context);*/

		/*TextView title = (TextView) itemView.findViewById(R.id.title_funny);*/
		ImageView image = (ImageView) itemView.findViewById(R.id.image_funny);
		play = (ImageButton) itemView.findViewById(R.id.play_video);

		/*title.setText(listItem.get(position).getTitle());*/
		/*mLoader.DisplayImage(listItem.get(position).getImage(), image);*/
		
		Picasso.with(context).load(listItem.get(position).getImage()).into(image);

		youtubeID = listItem.get(position).getYoutubeID();
		youtubeTITLE = listItem.get(position).getTitle();
		
		play.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, PlayVideo.class);
				
				intent.putExtra("youtubeID", youtubeID);
				intent.putExtra("youtubeTITLE", youtubeTITLE);
				
				context.startActivity(intent);
			}
		});
		
		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((RelativeLayout) object);

	}
}
