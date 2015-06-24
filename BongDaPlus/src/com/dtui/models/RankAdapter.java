package com.dtui.models;

import java.util.ArrayList;

import com.dtui.bongdaplus.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RankAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<RankItem> listRank;

	public RankAdapter(Context context, ArrayList<RankItem> listRank) {
		this.context = context;
		this.listRank = listRank;
	}

	@Override
	public int getCount() {
		return listRank.size();
	}

	@Override
	public Object getItem(int position) {
		return listRank.get(position);
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
			convertView = mInflater.inflate(R.layout.draw_list_rank, null);
		}
		
		ImageView icon   = (ImageView) convertView.findViewById(R.id.rank_icon);
		TextView Rank = (TextView) convertView.findViewById(R.id.rank_rank);
		TextView Point   = (TextView) convertView.findViewById(R.id.rank_point);
		
		icon.setImageResource(listRank.get(position).getIcon());
		Rank.setText("Vị trí: " + listRank.get(position).getRank());
		Point.setText("Điểm : " + listRank.get(position).getPoint());

		return convertView;
	}

}
