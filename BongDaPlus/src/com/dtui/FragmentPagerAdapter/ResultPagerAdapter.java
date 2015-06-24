package com.dtui.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtui.result.EnglandResultFragment;
import com.dtui.result.FranceResultFragment;
import com.dtui.result.GermanyResultFragment;
import com.dtui.result.SpainResultFragment;

public class ResultPagerAdapter extends FragmentStatePagerAdapter {

	public ResultPagerAdapter(FragmentManager fm) {
		super(fm);

	}

	private final String[] TITLES = { "Anh", "Tây Ban Nha", "Pháp", "Khác" };

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
			return new EnglandResultFragment().getInstance();
		case 1:
			return new SpainResultFragment().getInstance();
		case 2:
			return new FranceResultFragment().getInstance();
		case 3:
			return new GermanyResultFragment().getInstance();
		}
		return null;
	}
}
