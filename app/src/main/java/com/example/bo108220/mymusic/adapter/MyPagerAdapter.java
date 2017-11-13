package com.example.bo108220.mymusic.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.bo108220.mymusic.MyMusicFragment;
import com.example.bo108220.mymusic.NetMusicFragment;

/**
 * 创建时间：2017/11/13 10:05
 * 作者：Li zhb
 * 功能描述：我的音乐主界面适配器
 */

public class MyPagerAdapter extends FragmentPagerAdapter {

    private final String[] TITLES = {"本地音乐", "在线音乐"};

    public MyPagerAdapter(FragmentManager fm) {
        super(fm);
    }

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
        if (position == 0) {
            return MyMusicFragment.newInstance();
        } else if (position == 1) {
            return NetMusicFragment.newInstance();
        }
        return null;
    }
}
