package com.dtui.search;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.dtui.bongdaplus.R;
import com.dtui.models.DisPlayWebPageActivity;
import com.dtui.models.NewsAdapter;
import com.dtui.models.RSSItem;
import com.dtui.news.BackstageNewsRSSFragment;
import com.dtui.news.NewsHotRSSFragment;

public class SearchNewsFragment extends Fragment {

	private EditText key;
	private ImageView search;

	private static ArrayList<RSSItem> listItem = new ArrayList<RSSItem>();

	private ListView lvSearch;
	private NewsAdapter adapter;

	private ProgressDialog progressDialog;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_search, container,
				false);

		lvSearch = (ListView) rootView.findViewById(R.id.list_search);

		key = (EditText) rootView.findViewById(R.id.plot_search);
		search = (ImageView) rootView.findViewById(R.id.icon_search);

		search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (key.getText().length() != 0) {
					if(!listItem.isEmpty()){
						listItem.clear();
					}
					new SearchAsyncTask().execute();
				} else {

					Toast.makeText(getActivity(), "Bạn chưa nhập từ khóa",
							Toast.LENGTH_LONG).show();
				}
			}
		});

		return rootView;
	}

	private class SearchAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tìm kiếm tin ...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return null;
		}

		@SuppressWarnings("static-access")
		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			progressDialog.dismiss();

			
			searchNews(new NewsHotRSSFragment().getInstance()
					.getListRSS());
			searchNews(new BackstageNewsRSSFragment().getInstance()
					.getListRSS());

			/*backstageItem = getList(new BackstageNewsRSSFragment()
					.getInstance().getListRSS());
			listItem = searchNews(backstageItem);*/
			
			

			if (!listItem.isEmpty()) {
				adapter = new NewsAdapter(getActivity(), listItem);
				lvSearch.setAdapter(adapter);

				lvSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View view,
							int position, long arg3) {
						Intent in = new Intent(getActivity(),
								DisPlayWebPageActivity.class);

						// getting page url String page_url =
						String page_url = listItem.get(position).getLink();
						in.putExtra("page_url", page_url);
						startActivity(in);
					}
				});
				
			} else {
				Toast.makeText(getActivity(), "Không tìm thấy",
						Toast.LENGTH_LONG).show();
			}
		}

	}

	public void searchNews(ArrayList<RSSItem> list) {

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().indexOf(key.getText().toString().trim()) != -1) {
				listItem.add(new RSSItem(list.get(i).getTitle(), list.get(i)
						.getLink(), list.get(i).getPubdate(), list.get(i)
						.getImage()));
			}
		}
	}

	public ArrayList<RSSItem> getList(ArrayList<RSSItem> list) {

		for (int i = 0; i < list.size(); i++) {
			listItem.add(new RSSItem(list.get(i).getTitle(), list.get(i)
					.getLink(), list.get(i).getPubdate(), list.get(i)
					.getImage()));

		}
		return listItem;
	}
}
