package com.dtui.bongdaplus;

import java.util.ArrayList;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.dtui.calendar.CalendarFragment;
import com.dtui.footballfunny.FootballFunnyFragment;
import com.dtui.news.NewsFragment;
import com.dtui.rank.RankFragment;
import com.dtui.result.ResultFragment;
import com.dtui.search.SearchNewsFragment;
import com.dtui.slidingmenu.NavDrawerItem;
import com.dtui.slidingmenu.NavDrawerListAdapter;

public class MainActivity extends FragmentActivity {
	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;

	// slide menu items
	private String[] navMenuTitles;
	private TypedArray navMenuIcons;

	private ArrayList<NavDrawerItem> navDrawerItems;
	private NavDrawerListAdapter adapter;
	
	private ImageButton ibActionBar;
	private TextView tvActionBar;
	private ImageView ivActionBar;
	
	private boolean change = true;
	private boolean pressed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getActionBar().hide();
		/*requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);*/
		
		
        setContentView(R.layout.activity_main);
        
		
		ibActionBar = (ImageButton) findViewById(R.id.ibIcon);
		tvActionBar = (TextView) findViewById(R.id.tvActionBar);
		ivActionBar = (ImageView) findViewById(R.id.ivIcon);

		// load slide menu items
		navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);

		// nav drawer icons from resources
		navMenuIcons = getResources()
				.obtainTypedArray(R.array.nav_drawer_icons);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.list_slidermenu);

		navDrawerItems = new ArrayList<NavDrawerItem>();
		
		
		// adding nav drawer items to array
		// Home
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons
				.getResourceId(0, -1)));
		// Find People
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons
				.getResourceId(1, -1)));
		// Photos
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons
				.getResourceId(2, -1)));
		// Communities, Will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons
				.getResourceId(3, -1)));
		// Pages
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons
				.getResourceId(4, -1)));
		// What's hot, We will add a counter here
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons
				.getResourceId(5, -1)));
		navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons
				.getResourceId(6, -1)));

		// Recycle the typed array
		navMenuIcons.recycle();

		mDrawerList.setOnItemClickListener(new SlideMenuClickListener());

		// setting the nav drawer list adapter
		adapter = new NavDrawerListAdapter(getApplicationContext(),
				navDrawerItems);
		mDrawerList.setAdapter(adapter);
		
		if (savedInstanceState == null) {
			// on first time display view for first nav item
			displayView(0);
		}

		ibActionBar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				if(change == true){
					mDrawerLayout.openDrawer(mDrawerList);
					change = false;
				}else{
					mDrawerLayout.closeDrawer(mDrawerList);
					change = true;
				}
			}
		});
		
	}

	/**
	 * Slide menu item click listener
	 * */
	private class SlideMenuClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// display view for selected nav drawer item
			mDrawerLayout.closeDrawer(mDrawerList);
			change = true;
			displayView(position);
		}
	}

	/**
	 * Diplaying fragment view for selected nav drawer list item
	 * */
	private void displayView(int position) {
		// update the main content by replacing fragments
		Fragment fragment = null;
		switch (position) {
		case 0:
			fragment = new NewsFragment();
			ivActionBar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frame_container, new NewsFragment()).commit();
				}
			});
			break;
		case 1:
			fragment = new SearchNewsFragment();
			break;
		case 2:
			fragment = new ResultFragment();
			ivActionBar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frame_container, new ResultFragment()).commit();
				}
			});
			break;
		case 3:
			fragment = new RankFragment();
			ivActionBar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frame_container, new RankFragment()).commit();
				}
			});
			break;
		case 4:
			fragment = new CalendarFragment();
			ivActionBar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frame_container, new CalendarFragment()).commit();
				}
			});
			break;
		case 5:
			fragment = new FootballFunnyFragment();
			ivActionBar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
				getSupportFragmentManager().beginTransaction()
						.replace(R.id.frame_container, new FootballFunnyFragment()).commit();
				}
			});
			break;

		case 6:
			finishAffinity();
			MainActivity.this.finish();
			break;
		default:
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			fragmentManager.beginTransaction()
					.replace(R.id.frame_container, fragment).commit();

			// update selected item and title, then close the drawer
			mDrawerList.setItemChecked(position, true);
			mDrawerList.setSelection(position);
			tvActionBar.setText(Html.fromHtml("<font color='#ffffff'>"
					+ navMenuTitles[position] + "</font>"));
			ivActionBar.setImageResource(navDrawerItems.get(position).getIcon());
			
			
		} else {
			
			// error in creating fragment
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK && pressed == false) {
			Toast.makeText(getApplicationContext(), " Nhấn lại lần nữa ! ", Toast.LENGTH_SHORT).show();
			 pressed = true;
			return true;
		}else if (keyCode == KeyEvent.KEYCODE_BACK && pressed == true){
			finish();
		}
		return super.onKeyDown(keyCode, event);
	}
	
	

}
