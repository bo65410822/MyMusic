/*
 * Copyright (C) 2013 Andreas Stuetz <andreas.stuetz@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.bo108220.mymusic;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import com.example.bo108220.mymusic.adapter.MyPagerAdapter;
import com.example.bo108220.mymusic.utils.PagerSlidingTabStrip;


/**
 * 主界面的activity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ImageView musicNext, musicIcon;
    private TextView musicTime, musicTitle, musicName;
    private SeekBar musicBar;
    private int currentColor = 0xFF3F9FE0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicNext = findViewById(R.id.music_next);
        musicNext.setOnClickListener(this);
        musicTime = findViewById(R.id.music_time);
        musicTitle = findViewById(R.id.music_title);
        musicName = findViewById(R.id.music_name);
        musicIcon = findViewById(R.id.music_icon);
        musicBar = findViewById(R.id.music_seek_bar);
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
        changeColor(currentColor);
    }


    private void changeColor(int newColor) {

        tabs.setIndicatorColor(newColor);

        currentColor = newColor;

    }

    @Override
    public void onClick(View view) {
        super.playService.next();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindPlayService();
    }
}