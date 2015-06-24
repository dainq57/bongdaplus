package com.dtui.models;

import java.util.ArrayList;

import com.dtui.bongdaplus.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CalendarAdapter extends BaseAdapter{
	
	private Context context;
	private ArrayList<CalendarItem> calendarItem;
	
	public CalendarAdapter(Context context, ArrayList<CalendarItem> calendarItem){
		this.context = context;
		this.calendarItem = calendarItem;
	}

	@Override
	public int getCount() {
		return calendarItem.size();
	}

	@Override
	public Object getItem(int position) {
		return calendarItem.get(position);
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
            convertView = mInflater.inflate(R.layout.draw_list_calendar, null);
        }
		
		TextView time = (TextView) convertView.findViewById(R.id.calendar_time);
		TextView day = (TextView) convertView.findViewById(R.id.calendar_day);
		TextView team1 = (TextView) convertView.findViewById(R.id.calendar_team1);
		TextView team2 = (TextView) convertView.findViewById(R.id.calendar_team2);
		
		time.setText(calendarItem.get(position).getTimeFight());
		day.setText(calendarItem.get(position).getDayFight());
		team1.setText(calendarItem.get(position).getTeamOneFight());
		team2.setText(calendarItem.get(position).getTeamTwoFight());
		
		return convertView;
	}
	
}
