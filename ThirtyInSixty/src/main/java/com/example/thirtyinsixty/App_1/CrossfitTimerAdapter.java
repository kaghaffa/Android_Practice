package com.example.thirtyinsixty.App_1;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Kayvon on 7/28/13.
 */
public class CrossfitTimerAdapter extends FragmentPagerAdapter {

    Context context = null;

    public CrossfitTimerAdapter(Context context, FragmentManager manager) {
        super(manager);
        this.context = context;
    }

    @Override
    public int getCount() {
        return(3);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return CrossfitTimerFragment.newInstance(position);
            case 1:
                return EmomFragment.newInstance(position);
            case 2:
                return TabataFragment.newInstance(position);
        }
        return null;
    }

    @Override
    public String getPageTitle(int position) {
        return(CrossfitTimerFragment.getTitle(context, position));
    }
}
