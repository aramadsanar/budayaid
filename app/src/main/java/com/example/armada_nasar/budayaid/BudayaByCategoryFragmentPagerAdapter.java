package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class BudayaByCategoryFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private int mProvinceId;
    private static final String getJSONProvincesByIslandNameEndpoint = "http://35.194.234.226:6014/getJSONProvincesByIslandName/";
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return BudayaCategoryFragment.newInstance(mProvinceId, 1);
            case 1:
                return BudayaCategoryFragment.newInstance(mProvinceId, 2);
            case 2:
                return BudayaCategoryFragment.newInstance(mProvinceId, 3);
            case 3:
                return BudayaCategoryFragment.newInstance(mProvinceId, 4);
            case 4:
                return BudayaCategoryFragment.newInstance(mProvinceId, 5);
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }

    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Makanan";
            case 1:
                return "Pakaian";
            case 2:
                return "Kesenian";
            case 3:
                return "Bangunan";
            case 4:
                return "Lainnya";
        }
        return "";
    }

    public BudayaByCategoryFragmentPagerAdapter(Context c, FragmentManager fm, int provinceId) {
        super(fm);
        mContext = c;
        mProvinceId = provinceId;
    }
}

