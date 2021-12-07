package com.example.p5.ui.main;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.p5.R;
import com.example.p5.fragment_current;
import com.example.p5.fragment_menu;

import java.util.ArrayList;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 * @author Manav Bali
 * @author Daniel Lopez
 */
public class SectionsPagerAdapter2 extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_3,R.string.tab_text_4};
    private final Context mContext;

    private ArrayList<String> fragments;

    /**
     * set context to fragment.
     */
    public SectionsPagerAdapter2(Context context, FragmentManager fm) {
        super(fm);
        fragments=new ArrayList<String>();
        mContext = context;
    }

    /**
     * Sets the fragment based on the item.
     */
    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment=null;
        int first=0;
        int second=1;
        if(position==first){
            fragment= new fragment_menu();
        }
        else if(position==second){
            fragment= new fragment_current();
        }
        return fragment;
    }

    /**
     * Get position.
     */
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    /**
     * Get count.
     */
    @Override
    public int getCount() {
        int num=2;
        return num;
    }

    /**
     * Get fragment.
     */
    public String getFragment(int tab){
        String tabName=fragments.get(tab);
        if(tabName!=null){
            return tabName;
        }
        return null;
    }

    /**
     * Get the item.
     */
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object o= super.instantiateItem(container, position);
        if(o instanceof Fragment){
            Fragment temp=(Fragment) o;
            String name=temp.getTag();
            fragments.add(position,name);
        }
        return o;
    }
}