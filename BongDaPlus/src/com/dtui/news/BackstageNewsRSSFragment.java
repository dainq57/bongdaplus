package com.dtui.news;

import java.util.ArrayList;
import java.util.List;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dtui.bongdaplus.R;
import com.dtui.models.DisPlayWebPageActivity;
import com.dtui.models.NewsAdapter;
import com.dtui.models.RSSItem;
import com.dtui.models.RSSParser;

public class BackstageNewsRSSFragment extends Fragment {

	private static ArrayList<RSSItem> listRSS = new ArrayList<RSSItem>();
	
	private ListView lv3;
	private RSSParser rssParser = new RSSParser();
	private List<RSSItem> rssItems = new ArrayList<RSSItem>();
	
	private NewsAdapter adapter;
	
	private ProgressDialog progressDialog;

	private BackstageNewsRSSFragment instance = null;
	
	public BackstageNewsRSSFragment getInstance(){
		if(instance != null){
			return instance;
		}else{
			instance = new BackstageNewsRSSFragment();
			return instance;
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_news_tabs,
				container, false);
		lv3 = (ListView) rootView.findViewById(R.id.list);
		lv3.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Intent in = new Intent(getActivity(),
						DisPlayWebPageActivity.class);

				// getting page url
				String page_url = rssItems.get(arg2).getLink();
				in.putExtra("page_url", page_url);
				startActivity(in);
			}
		});

		new PostTask().execute("http://thethao247.vn/hau-truong-c49.rss");
		
		/*if(listRSS.isEmpty()){
			new PostTask().execute("http://thethao247.vn/bong-da-quoc-te-c2.rss");
		}else{
			
			adapter = new NewsAdapter(getActivity(), listRSS);
			lv3.setAdapter(adapter);
		}*/
		listRSS.clear();
		
		/*rssItemList.clear();*/
		return rootView;
	}



	private class PostTask extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tải ...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			String url1 = params[0];
			// list of rss items
			rssItems = rssParser.getRSSFeedItems(url1);

			// looping through each item
			for (RSSItem item : rssItems) {
				listRSS.add(new RSSItem(item.getTitle(), item.getLink(), item.getImage(), item.getPubdate()));
			}
			setListRSS(listRSS);
			
			return null;
		}

		@Override
		protected void onProgressUpdate(String... values) {
			super.onProgressUpdate(values);
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			progressDialog.dismiss();
			
			if(!listRSS.isEmpty()){
				adapter = new NewsAdapter(getActivity(), listRSS);
				lv3.setAdapter(adapter);
			}
		}
	}



	public static ArrayList<RSSItem> getListRSS() {
		return listRSS;
	}

	public static void setListRSS(ArrayList<RSSItem> listRSS) {
		BackstageNewsRSSFragment.listRSS = listRSS;
	}
	
	
}
