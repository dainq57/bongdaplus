package com.dtui.rank;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.TypedArray;
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
import com.dtui.models.RankAdapter;
import com.dtui.models.RankItem;

public class GermanyRankFragment extends Fragment {

	private ListView lvRankEngland;
	private RankAdapter adapter;
	
	private TextView seasonBall;
	private TypedArray listIcons;
	
	private ProgressDialog progressDialog;
	
	private final String url = "http://thethao247.vn/bundesliga-live96/";
	private ArrayList<RankItem> listRank = new ArrayList<RankItem>();

	private  GermanyRankFragment instance=null;
	
	public  GermanyRankFragment getInstance (){
		if( instance!=null) return instance;
		else {
			instance=new GermanyRankFragment();
			return instance;
		}
	}
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_rank_tabs,
				container, false);

		lvRankEngland = (ListView) rootView
				.findViewById(R.id.list_rank_tabs);
		seasonBall = (TextView) rootView.findViewById(R.id.rank_season);
		
		listIcons = getResources().obtainTypedArray(R.array.rank_germany);
		
		lvRankEngland.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long arg3) {
				
				Intent intent = new Intent(getActivity(), InfoRankFragment.class);
				
				if(!listRank.isEmpty()){
					intent.putExtra("Rank", listRank.get(position).getRank());
					intent.putExtra("Team", listRank.get(position).getTeam());
					intent.putExtra("NumberPlay", listRank.get(position).getNumberPlay());
					intent.putExtra("Win", listRank.get(position).getWin());
					intent.putExtra("Draw", listRank.get(position).getDraw());
					intent.putExtra("Lose", listRank.get(position).getLose());
					intent.putExtra("NumberGoal", listRank.get(position).getNumberGoal());
					intent.putExtra("RippedNet", listRank.get(position).getRippedNet());
					intent.putExtra("SubGoal", listRank.get(position).getSubGoal());
					intent.putExtra("Point", listRank.get(position).getPoint());
					
					intent.putExtra("Icon", listRank.get(position).getIcon() + "");
				}
				
				startActivity(intent);
			}
		});
		new GermanyRankAsyncTask().execute();
		
		return rootView;
	}

	private class GermanyRankAsyncTask extends AsyncTask<Void, Void, Void> {

		String season;
		String rank;
		String team;
		int icon = -1;
		String numberPlay;
		String win;
		String draw;
		String lose;
		String numberGoal;
		String rippedNet;
		String subGoal;
		String point;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Đang tải ...");
			progressDialog.setIndeterminate(false);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(Void... params) {
			try {

				// Connect to the web site
				Document doc = Jsoup.connect(url).get();
				season = doc.select("div[class=header]").select("h2").text();
				Elements d = doc.select("tr");

				for (Element e : d) {
					rank = e.getElementsByClass("ltid").text();
					team = e.getElementsByClass("ltn").text();
					numberPlay = e.getElementsByClass("ltg").text();
					win = e.getElementsByClass("ltw").text();
					draw = e.getElementsByClass("ltd").text();
					lose = e.getElementsByClass("ltl").text();
					numberGoal = e.getElementsByClass("ltgf").text();
					rippedNet = e.getElementsByClass("ltga").text();
					subGoal = e.getElementsByClass("ltgd").text();
					point = e.getElementsByClass("ltp").text();
					
					icon = getIcon(team.toLowerCase());
					
					if (season != null && rank != null && icon != -1 && team != null && numberPlay != null
							&& win != null && draw != null && lose != null
							&& numberGoal != null && rippedNet != null
							&& subGoal != null && point != null) {
							listRank.add(new RankItem(season, rank, icon, team, numberPlay, win, draw, lose, numberGoal, rippedNet, subGoal, point));
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
			
			if(!listRank.isEmpty()){
				seasonBall.setText(listRank.get(1).getSeason());
				adapter = new RankAdapter(getActivity(), listRank);
				lvRankEngland.setAdapter(adapter);
			}
		}

	}
	
	public int getIcon(String icon){
		int iconId = -1;
		
		for(int i=0; i<listIcons.length(); i++){
			String image = listIcons.getText(i) + "";
			image = image.replace("res/drawable-ldpi/", " ");
			image = image.replace(".png", " ");
			image = image.trim();
			
			if(image.indexOf("_") != -1){
				image = image.replace("_", " ");
			}

			if(icon.indexOf(image) != -1){
				iconId = listIcons.getResourceId(i, -1);
			}
		}
		
		return iconId;
	}
}
