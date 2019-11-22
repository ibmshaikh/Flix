package com.ibm.contentupload;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PagerviewAdapter extends FragmentPagerAdapter {
    public PagerviewAdapter(FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

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
