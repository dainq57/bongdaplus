package com.dtui.footballfunny;

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
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtui.bongdaplus.R;
import com.dtui.models.VideoAdapter;
import com.dtui.models.VideoItem;

public class VideoFunnyFragment extends Fragment{

	private ViewPager viewPager;
	private VideoAdapter adapter;
	
	private ArrayList<VideoItem> listItem = new ArrayList<VideoItem>();
	
	private String url = "http://trollbongda.com/video.html";
	
	private ProgressDialog progressDialog;
	
	private VideoFunnyFragment instance = null;

	public VideoFunnyFragment getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new VideoFunnyFragment();
			return instance;
		}
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_football_video, container, false);
		
		viewPager = (ViewPager) rootView.findViewById(R.id.video_funny);
		
		new VideoFunnyAsyncTask().execute();
		
		
		
		return rootView;
	}

	
	private class VideoFunnyAsyncTask extends AsyncTask<String, String, String>{

		private String image;
		private String title;
		private String youtubeId;
		
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tải...");
			progressDialog.setIndeterminate(false);
			progressDialog.show();
		}
		
		@Override
		protected String doInBackground(String... result) {
			try {

				// Connect to the web site
				Document doc = Jsoup.connect(url).get();
				Elements e1 = doc.getElementsByClass("catItemBody");
				for(Element e : e1){
					
					title = e.getElementsByClass("video_title_2").text();
					image = e.getElementsByClass("img-responsive").attr("src");
					youtubeId =  e.getElementsByClass("num-view").attr("youtubeid");
					
					if(title != null && (image.indexOf(".jpg") != -1 || image.indexOf(".png") != -1) && youtubeId != null){

						listItem.add(new VideoItem(image, title, youtubeId));
					}
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			
			progressDialog.dismiss();
			
			if(!listItem.isEmpty()){
				adapter = new VideoAdapter(getActivity(), listItem);
				viewPager.setAdapter(adapter);
							}
			
		}

		
	}
}
