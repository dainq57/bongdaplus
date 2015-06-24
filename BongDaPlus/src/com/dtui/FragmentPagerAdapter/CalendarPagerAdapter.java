package com.dtui.FragmentPagerAdapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtui.calendar.EnglandCalendarFragment;
import com.dtui.calendar.FranceCalendarFragment;
import com.dtui.calendar.GermanyCalendarFragment;
import com.dtui.calendar.SpainCalendarFragment;

public class CalendarPagerAdapter extends FragmentStatePagerAdapter {

	public CalendarPagerAdapter(FragmentManager fm) {
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
			return new EnglandCalendarFragment().getInstance();
		case 1:
			return new SpainCalendarFragment().getInstance();
		case 2:
			return new FranceCalendarFragment().getInstance();
		case 3:
			return new GermanyCalendarFragment().getInstance();
		}
		return null;
	}
}
