package com.dtui.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtui.news.BackstageNewsRSSFragment;
import com.dtui.news.NewsHotRSSFragment;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {

	
	
	public NewsPagerAdapter(FragmentManager fm) {
		super(fm);
		//newsHot = new NewsHotFragment();
		/*backstageNews = new BackstageNewsFragment();*/
		
	}

	private final String[] TITLES = { "            Tin hót            ", "           Hậu trường           " };

	@Override
	public CharSequence getPageTitle(int position) {
		return TITLES[position];
	}

	@Override
	public int getCount() {
		return TITLES.length;
	}

	
	@Override
	public Fragment getItem(int position) {
		switch (position) {
		case 0:
			/*return new SportRSSFragment();*/
			return new NewsHotRSSFragment().getInstance();
			 // Tin hót
		case 1:
			return new BackstageNewsRSSFragment().getInstance(); // Tin hậu trường
		/*case 2:
			return new ProfessionalNewsFragment(); // Tin chuyên gia
*/		}
		return null;
	}
}
