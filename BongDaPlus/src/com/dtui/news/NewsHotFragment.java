package com.dtui.news;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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

public class NewsHotFragment extends Fragment{

	private ArrayList<String> title = new ArrayList<String>();
	private ArrayList<String> link = new ArrayList<String>();
	private ArrayList<String> pubDate = new ArrayList<String>();
	private ArrayList<String> image = new ArrayList<String>();
	
	private String url = "http://www.bongda.com.vn/AFF-Cup/Index.aspx";
	private ProgressDialog mProgressDialog;
	
	private ArrayList<RSSItem> rssItem = new ArrayList<RSSItem>();  

	private static ListView lv3;
	
	
	private  NewsHotFragment instance=null;
	
	
	public  NewsHotFragment getInstance (){
		if( instance!=null) return instance;
		else {
			instance=new NewsHotFragment();
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
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				Intent in = new Intent(getActivity(),
						DisPlayWebPageActivity.class);

				// getting page url
				String page_url = rssItem.get(position).getLink();
				in.putExtra("page_url", page_url);
				startActivity(in);
			}
		});
		
		new ParserHTMLJsoup().execute();
		/*rssItemList.clear();*/
		return rootView;
	}

		

	private class ParserHTMLJsoup extends AsyncTask<String, String, ArrayList<RSSItem>> {	
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mProgressDialog = new ProgressDialog(getActivity());
			mProgressDialog.setMessage("Đang tải...");
			mProgressDialog.setIndeterminate(false);
			mProgressDialog.show();
		}

		protected ArrayList<RSSItem> doInBackground(String... params) {
			try {
				
				// Connect to the web site
				Document doc = Jsoup.connect(url).get();
				Elements info1 = doc.select("a[class=read_more]");
				Elements info2 = doc.select("sup[class=date]");
				Elements info3 = doc.select("img[hspace=3]");
				for(Element e1 : info1){
					title.add(e1.text());
					link.add("http://www.bongda.com.vn/" + e1.attr("href"));
				}
				for(Element e2 : info2){
					pubDate.add(e2.text());
				}
				for(Element e3 : info3){
					image.add(e3.attr("src"));
				}
				
				
				
				for(int i=0; i<image.size(); i++){
					
					/*Log.d("demo" + (i+1) + "Title: ", title.get(i));
					Log.d("demo" + (i+1) + "Link: ", link.get(i));
					Log.d("demo" + (i+1) + "pubDate: ", pubDate.get(i));*/
						
					rssItem.add(new RSSItem(title.get(i), link.get(i), pubDate.get(i), image.get(i)));
					
				}
				
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<RSSItem> result) {
			NewsAdapter adapter = new NewsAdapter(getActivity(), rssItem);
			lv3.setAdapter(adapter);			
			adapter.notifyDataSetChanged();
			mProgressDialog.dismiss();
		}
		
		
	}
	
}
