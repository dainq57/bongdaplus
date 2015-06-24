package com.dtui.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtui.footballfunny.ImageFunnyFragment;
import com.dtui.footballfunny.VideoFunnyFragment;

public class FootballFunnyPagerAdapter extends FragmentStatePagerAdapter {

	public FootballFunnyPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	private final String[] TITLES = { "            Hình ảnh            ", "                 Video                "};

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
			return new ImageFunnyFragment().getInstance();
		case 1:
			return new VideoFunnyFragment().getInstance();
		}
		return null;
	}
}
