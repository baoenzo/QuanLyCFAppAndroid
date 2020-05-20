package com.example.quanlyquancf.AdapterR;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class PageAdapter extends FragmentStatePagerAdapter {

    int mNoOfTabs;

    private List<Fragment> fragmentList = new ArrayList();
    List<String> titlePage = new ArrayList<>();

    public PageAdapter(FragmentManager fm)
    {
        super(fm);
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        return  fragmentList.get(position);
    }

    @Override
    public int getCount() {
       return fragmentList.size();
    }
    public  void addFragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        titlePage.add(title);
    }

    public CharSequence getPageTitle(int i)
    {
        return titlePage.get(i);
    }
}
