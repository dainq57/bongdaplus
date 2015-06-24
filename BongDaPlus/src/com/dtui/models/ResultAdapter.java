package com.dtui.models;

import java.util.ArrayList;

import com.dtui.bongdaplus.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ResultAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<ResultItem> resultItem;
	
	public ResultAdapter(Context context, ArrayList<ResultItem> resultItem){
		this.context = context;
		this.resultItem = resultItem;
	}

	@Override
	public int getCount() {
		return resultItem.size();
	}

	@Override
	public Object getItem(int position) {
		return resultItem.get(position);
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
            convertView = mInflater.inflate(R.layout.draw_list_result, null);
        }
		
		TextView day   = (TextView) convertView.findViewById(R.id.result_day);
		TextView team1 = (TextView) convertView.findViewById(R.id.result_team1);
		TextView team2 = (TextView) convertView.findViewById(R.id.result_team2);
		TextView result = (TextView) convertView.findViewById(R.id.result_ratio);
		
		day.setText(resultItem.get(position).getDay());
		team1.setText(resultItem.get(position).getTeam1());
		team2.setText(resultItem.get(position).getTeam2());
		result.setText(resultItem.get(position).getResult());
		
		return convertView;
	}
	
}
