package com.dtui.models;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dtui.bongdaplus.R;
import com.squareup.picasso.Picasso;

public class NewsAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<RSSItem> rssItem;
	/*private ImageLoader mLoader;*/
	
	public NewsAdapter(Context context, ArrayList<RSSItem> rssItem){
		this.context = context;
		this.rssItem = rssItem;
	}


	@Override
	public int getCount() {
		return rssItem.size();
		
	}

	@Override
	public Object getItem(int position) {
		return rssItem.get(position);
		
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.draw_list_items_news_tabs, null);
        }
		
		TextView title = (TextView) convertView.findViewById(R.id.title_news);
		TextView pubDate = (TextView) convertView.findViewById(R.id.pubDate_news);
		/*mLoader = new ImageLoader(context);*/
		
		title.setText(rssItem.get(position).getTitle());
		pubDate.setText(rssItem.get(position).getPubdate());
		ImageView imageView = (ImageView) convertView.findViewById(R.id.icon_news);
		
		/*mLoader.DisplayImage(rssItem.get(position).getImage(), imageView);*/
		
		Picasso.with(context).load(rssItem.get(position).getImage()).into(imageView);
		
		/*new DownloadImageTask((ImageView) convertView.findViewById(R.id.icon_news))
				.execute(rssItem.get(position).getImage());*/
		
		return convertView;
	}
	
	
	
}
