package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ProvinceFragmentPagerAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private static final String getJSONProvincesByIslandNameEndpoint = "http://35.194.234.226:6014/getJSONProvincesByIslandName/";
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "jawa");
            case 1:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "sumatera");
            case 2:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "bali_nt");
            case 3:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "kalimantan");
            case 4:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "sulawesi");
            case 5:
                return ProvinceListFragment.newInstance(getJSONProvincesByIslandNameEndpoint + "papua_maluku");
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }

    public CharSequence getPageTitle(int position) {
        switch(position) {
            case 0:
                return "Jawa";
            case 1:
                return "Sumatera";
            case 2:
                return "Bali dan Nusa Tenggara";
            case 3:
                return "Kalimantan";
            case 4:
                return "Sulawesi";
            case 5:
                return "Papua dan Maluku";
        }
        return "";
    }

    public ProvinceFragmentPagerAdapter(Context c, FragmentManager fm) {
        super(fm);
        mContext = c;
    }
}
