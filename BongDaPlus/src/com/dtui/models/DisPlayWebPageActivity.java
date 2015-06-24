package com.dtui.models;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dtui.bongdaplus.R;

public class DisPlayWebPageActivity extends Activity {

	private WebView webview;
	private ProgressDialog progressDialog;

	private String page_url;

	private ImageButton back;
	private TextView title;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview);
		getActionBar().hide();

		Intent in = getIntent();
		page_url = in.getStringExtra("page_url");

		page_url = page_url.replace("http://", "http://m.");

		webview = (WebView) findViewById(R.id.webpage);
		back = (ImageButton) findViewById(R.id.ibBack);
		title = (TextView) findViewById(R.id.tvBack);

		new WebAsyncTask().execute();

		title.setText("Nội dung tin tức");
		
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	private class DisPlayWebPageActivityClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {

			webview.loadData(page_url, "text/html; charset=UTF-8", null);
			return true;
		}
	}

	private class WebAsyncTask extends AsyncTask<String, String, String> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(DisPlayWebPageActivity.this);
			progressDialog.setMessage("Đang tải ...");
			progressDialog.setIndeterminate(false);
			progressDialog.setCancelable(false);
			progressDialog.show();

		}

		@Override
		protected String doInBackground(String... params) {
			try {
				Document doc = Jsoup.connect(page_url).get();
				doc.select("div#add").remove();
				Elements e = doc.select("div[id=main-detail]");
				page_url = e.html();
				page_url += "<p style=\"text-align:right; font-size: 13px; font-style: italic; color: #999; margin: 10px 0;\">Nguồn Thethao247.vn - ĐSPL </p>";

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);

			progressDialog.dismiss();

			webview.getSettings().setJavaScriptEnabled(true);
			webview.getSettings().setLayoutAlgorithm(
					LayoutAlgorithm.SINGLE_COLUMN);

			webview.removeOnAttachStateChangeListener(null);
			/* webview.loadUrl(page_url); */

			webview.loadData(page_url, "text/html; charset=UTF-8", null);
			webview.setWebViewClient(new DisPlayWebPageActivityClient());

		}

	}
	
}
