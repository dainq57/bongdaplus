package com.dtui.result;

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
import android.widget.ListView;
import android.widget.TextView;

import com.dtui.bongdaplus.R;
import com.dtui.models.ResultAdapter;
import com.dtui.models.ResultItem;

public class EnglandResultFragment extends Fragment{
	
	private String url = "http://thethao247.vn/ket-qua-bong-da-ngoai-hang-anh-live52/";
	private ProgressDialog mProgressDialog;
	
	private ArrayList<ResultItem> resultItem = new ArrayList<ResultItem>();  

	private static ListView listResult;
	private TextView seasonBall;
	
	private  EnglandResultFragment instance=null;
	
	
	public  EnglandResultFragment getInstance (){
		if( instance!=null) return instance;
		else {
			instance=new EnglandResultFragment();
			return instance;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_result_tabs,
				container, false);
		
		listResult = (ListView) rootView.findViewById(R.id.list_result_tabs);
		seasonBall = (TextView) rootView.findViewById(R.id.result_season);
		
		new EnglandResultAsyncTask().execute();
		
		return rootView;
	}

		

	private class EnglandResultAsyncTask extends AsyncTask<String, String, ArrayList<ResultItem>> {	
		
		String season;
		String day;
		String team1;
		String team2;
		String result;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(getActivity());
			mProgressDialog.setMessage("Đang tải...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		protected ArrayList<ResultItem> doInBackground(String... params) {
			try {
				
				Document doc = Jsoup.connect(url).get();
				season = doc.select("div[class=header]").select("h2").text();
				Elements d = doc.select("dl");

				for (Element e : d) {

					int index = e.text().indexOf(" - ");

					if (e.text().indexOf("tháng") != -1) {
						int indexDay = e.text().indexOf("tháng");
						day = e.text();
						day = day.replace(" tháng ", "/");
						day = day.replace(" năm 20", "/");
						day = day.substring(indexDay - 3, day.length());
						day = day.trim();
					}else{
						if (index != -1) {
							team1 = e.text().substring(4, index - 2);
							team2 = e.text().substring(index + 5, e.text().length());
							result = e.text().substring(index - 2, index + 5);
							result = result.trim();
						}

						if (day != null && team1 != null && team2 != null && result != null) {
							
							resultItem.add(new ResultItem(season, day, team1,
									team2, result));
							
						}
					}
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<ResultItem> result) {
			
			mProgressDialog.dismiss();
			
			if(!resultItem.isEmpty()){
				seasonBall.setText(resultItem.get(0).getSeason());
				ResultAdapter adapter = new ResultAdapter(getActivity(), resultItem);
				listResult.setAdapter(adapter);
			}
		}
		
		
	}
	
}
