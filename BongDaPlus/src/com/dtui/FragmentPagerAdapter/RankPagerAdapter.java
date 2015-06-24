package com.dtui.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtui.rank.EnglandRankFragment;
import com.dtui.rank.FranceRankFragment;
import com.dtui.rank.GermanyRankFragment;
import com.dtui.rank.SpainRankFragment;

public class RankPagerAdapter extends FragmentStatePagerAdapter {

	public RankPagerAdapter(FragmentManager fm) {
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
			return new EnglandRankFragment().getInstance();
		case 1:
			return new SpainRankFragment().getInstance();
		case 2:
			return new FranceRankFragment().getInstance();
		case 3:
			return new GermanyRankFragment().getInstance();
		}
		return null;
	}
}
