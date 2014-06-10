package com.bd.theworld.cup;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

	LinearLayout mSubMenu;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		mSubMenu = (LinearLayout) findViewById(R.id.sub_menu);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the Sub-Menu with the sections adapter.
		initSubMenu();

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

		mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageSelected(int position) {
				focusSubMenu(position);
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});
    }

	private void initSubMenu() {
		int count = mSectionsPagerAdapter.getCount();
		for (int i = 0; i < count; i++) {
			TextView subMenuTxt = new TextView(this);
			subMenuTxt.setText(mSectionsPagerAdapter.getPageTitle(i));
			subMenuTxt.setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
			layoutParams.weight = 1;
			final int position = i;
			subMenuTxt.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					mViewPager.setCurrentItem(position, false);
				}
			});
			mSubMenu.addView(subMenuTxt, layoutParams);
		}
		focusSubMenu(0);
	}

	private void focusSubMenu(int position) {
		int childCount = mSubMenu.getChildCount();
		for (int i = 0; i < childCount; i++) {
			TextView textView = (TextView) mSubMenu.getChildAt(i);
			if (textView != null) {
				if (i == position) {
					textView.setBackgroundColor(getResources().getColor(R.color.holo_green_dark));
				} else {
					textView.setBackgroundColor(getResources().getColor(R.color.holo_green_light));
				}
			}
		}
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return SubMenu.getFragment(position);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return SubMenu.getCount();
        }

        @Override
        public CharSequence getPageTitle(int position) {
        	return SubMenu.getTitleName(position);
        }
    }

}
