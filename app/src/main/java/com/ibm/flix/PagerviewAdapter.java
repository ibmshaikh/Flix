package com.ibm.flix;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class PagerviewAdapter extends FragmentPagerAdapter {
    public PagerviewAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){

            case 0:
                Movies_Fregment movies_fregment = new Movies_Fregment();
                return movies_fregment;
            case 1:
                Series_Fregment series_fregment = new Series_Fregment();
                return series_fregment;

            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
