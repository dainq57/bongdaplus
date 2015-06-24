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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dtui.bongdaplus.R;
import com.dtui.footballfunny.ZoomImageFragment;
import com.squareup.picasso.Picasso;

public class ImageAdapter extends PagerAdapter {
	// Declare Variables
	private Context context;
	private ArrayList<ImageFunnyItem> listItem;
	
	/*private ImageLoader mLoader;*/
	
	public ImageAdapter(Context context, ArrayList<ImageFunnyItem> listItem) {
		this.context = context;
		this.listItem = listItem;
	}

	@Override
	public int getCount() {
		return listItem.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		return view == ((LinearLayout) object);
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View itemView = inflater.inflate(R.layout.fragment_football_image_viewpager_item, container,
				false);
		/*mLoader = new ImageLoader(context);*/

		TextView title = (TextView) itemView.findViewById(R.id.title_image_funny);
		ImageView image = (ImageView) itemView.findViewById(R.id.icon_image_funny);

		title.setText(listItem.get(position).getTitle());
		/*image.setImageResource(R.drawable.atletico_madrid);*/
		/*mLoader.DisplayImage(listItem.get(position).getIcon(), image);*/
		// Load image from web
		Picasso.with(context).load(listItem.get(position).getIcon()).into(image);
		
		/*ZoomableImageView touch = (ZoomableImageView)itemView.findViewById(R.id.icon_image_funny);*/
		
		/*image.setOnTouchListener(new Touch());*/
		
		final String url = listItem.get(position).getIcon();
		image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(context, ZoomImageFragment.class);
				i.putExtra("url", url);
				context.startActivity(i);
			}
		});

		((ViewPager) container).addView(itemView);

		return itemView;
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// Remove viewpager_item.xml from ViewPager
		((ViewPager) container).removeView((LinearLayout) object);

	}
}
