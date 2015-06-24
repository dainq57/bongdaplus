package com.dtui.calendar;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.dtui.bongdaplus.R;
import com.dtui.models.CalendarAdapter;
import com.dtui.models.CalendarItem;

public class EnglandCalendarFragment extends Fragment {

	private ListView lvCalendarEngland;
	private TextView seasonBall;
	private ProgressDialog progressDialog;
	private final String url = "http://thethao247.vn/lich-thi-dau-ket-qua/lich-thi-dau-bong-da/lich-thi-dau-bong-da-ngoai-hang-anh-2012-2013-d31035.html";
	private ArrayList<CalendarItem> calendarItem = new ArrayList<CalendarItem>();

	private  EnglandCalendarFragment instance=null;
	
	public  EnglandCalendarFragment getInstance (){
		if( instance!=null) return instance;
		else {
			instance=new EnglandCalendarFragment();
			return instance;
		}
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_calendar_tabs,
				container, false);

		lvCalendarEngland = (ListView) rootView
				.findViewById(R.id.list_calendar_england);
		seasonBall = (TextView) rootView.findViewById(R.id.calendar_season);
		
		lvCalendarEngland.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
			}
		});
		new EnglandCalendarAsyncTask().execute();
		
		return rootView;
	}

	private class EnglandCalendarAsyncTask extends AsyncTask<Void, Void, Void> {

		String season;
		String day;
		String time;
		String team1;
		String team2;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tải...");
			progressDialog.setIndeterminate(false);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				// Connect to the web site
				Document doc = Jsoup.connect(url).get();
				season = doc.select("div[class=header]").text();
				Elements d = doc.select("dl");

				for (Element e : d) {

					int index1 = e.text().indexOf(":");
					int index3 = e.text().indexOf(" v ");

					if (e.text().indexOf("tháng") != -1) {
						int indexDay = e.text().indexOf("tháng");
						day = e.text();
						day = day.replace(" tháng ", "/");
						day = day.replace(" năm 20", "/");
						day = day.substring(indexDay - 3, day.length());
						day = day.trim();
					}else{
						if (index1 != -1) {
							index1 += 3;
							time = e.text().substring(0, index1);
						}

						if (index3 != -1) {
							team1 = e.text().substring(index1 + 1, index3);
							team2 = e.text().substring(index3 + 2,
									e.text().length());
						}

						if (day != null && time != null && team1 != null
								&& team2 != null) {
							calendarItem.add(new CalendarItem(season, day, time, team1,
									team2));
						}
					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {

			progressDialog.dismiss();
			
			if(!calendarItem.isEmpty()){
				seasonBall.setText(calendarItem.get(0).getSeason());
				CalendarAdapter adapter = new CalendarAdapter(getActivity(), calendarItem);
				lvCalendarEngland.setAdapter(adapter);
			}	
			
		}

	}
}
