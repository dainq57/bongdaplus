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
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dtui.bongdaplus.R;
import com.dtui.models.ImageAdapter;
import com.dtui.models.ImageFunnyItem;

public class ImageFunnyFragment extends Fragment {

	private ViewPager viewPager;
	private PagerAdapter adapter;

	private ArrayList<ImageFunnyItem> listItem = new ArrayList<ImageFunnyItem>();

	private String url = "http://trollbongda.com/moi-nhat.html";

	private ProgressDialog progressDialog;

	private ImageFunnyFragment instance = null;

	public ImageFunnyFragment getInstance() {
		if (instance != null)
			return instance;
		else {
			instance = new ImageFunnyFragment();
			return instance;
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_football_image,
				container, false);

		viewPager = (ViewPager) rootView
				.findViewById(R.id.image_funny_view_pager);

		new ImageFunnyAsyncTask().execute();

		return rootView;
	}

	private class ImageFunnyAsyncTask extends AsyncTask<String, String, String> {

		String image;
		String title;

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
				Elements e1 = doc.getElementsByClass("catItemTitle");
				Elements e2 = e1.select("a[target=_blank]");
				for (Element e : e2) {
					image = "http://trollbongda.com" + e.attr("title");
					image = image.replace("[/", "/");
					image = image.replace("]", " ");
					image = image.trim();

					title = e.text();

					if (title != null
							&& (image.indexOf(".jpg") != -1 || image
									.indexOf(".png") != -1)) {
						listItem.add(new ImageFunnyItem(image, title));
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

			if (!listItem.isEmpty()) {
				adapter = new ImageAdapter(getActivity(), listItem);
				viewPager.setAdapter(adapter);
			}

		}

	}

}
