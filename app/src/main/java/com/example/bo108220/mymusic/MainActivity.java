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


import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


import com.example.bo108220.mymusic.adapter.MyPagerAdapter;
import com.example.bo108220.mymusic.bean.Mp3Info;
import com.example.bo108220.mymusic.utils.MusicUtil;
import com.example.bo108220.mymusic.utils.PagerSlidingTabStrip;

import java.util.List;


/**
 * 主界面的activity
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "MainActivity";
    private static final int NEXT_MUSIC = 0x101;
    private static final int PLAY_TIME_MUSIC = 0x102;
    private static final int NONE_MUSIC = 0x103;
    private PagerSlidingTabStrip tabs;
    private ViewPager pager;
    private MyPagerAdapter adapter;
    private ImageView musicNext, musicIcon;
    private TextView musicTime, musicTitle, musicName;
    private SeekBar musicBar;
    private int currentColor = 0xFF3F9FE0;
    private int i = 0;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @SuppressLint("ShowToast")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NEXT_MUSIC:
                    setCurrentMp3Info();
                    break;
                case PLAY_TIME_MUSIC:
                    if (playService != null) {
                        if (playService.isPlay()) {
                            i++;
                            musicTime.setText(getPlayTime(mp3Info.getDuration()));
                        }
                    }
                    sendEmptyMessageDelayed(PLAY_TIME_MUSIC, 1000);
                    break;
                case NONE_MUSIC: //手机中没有音乐
                    Toast.makeText(MainActivity.this, "您还没有歌曲"
                            , Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

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
        musicBar.setOnSeekBarChangeListener(new MySeekBarListener());
        tabs = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);
        adapter = new MyPagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        pager.setPageMargin(pageMargin);
        tabs.setViewPager(pager);
        changeColor(currentColor);
        setCurrentMp3Info();
        handler.sendEmptyMessage(PLAY_TIME_MUSIC);
        setSeekBar();
    }

    /**
     * 设置的进度条
     */
    private void setSeekBar(){
        if (playService!=null) {
            if (playService.mp != null) {
                musicBar.setMax(playService.mp.getDuration());
            }
        }
    }

    private void changeColor(int newColor) {
        tabs.setIndicatorColor(newColor);
        currentColor = newColor;
    }

    @Override
    public void onClick(View view) {
        //下一曲并更新底部UI
        super.playService.next();
        handler.sendEmptyMessage(NEXT_MUSIC);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindPlayService();
    }

    /**
     * 设置当前音乐信息,默认显示第一首音乐
     */
    public void setCurrentMp3Info() {
        List<Mp3Info> mp3Infos = MusicUtil.getMp3Infos(MainActivity.this);
        if (mp3Info == null) {
            if (mp3Infos != null && (mp3Infos.size() > 0)) {
                mp3Info = MusicUtil.getMp3Infos(MainActivity.this).get(0);
            } else {
                Log.e(TAG, "setCurrentMp3Info: 您还没有歌曲");

                handler.sendEmptyMessage(NONE_MUSIC);
                return;
            }
        }
        musicTitle.setTag(mp3Info.getTitle());
        musicName.setText(mp3Info.getArtist());
        musicTime.setText(MusicUtil.formatTime(mp3Info.getDuration()));

    }

    /**
     * 获取播放进度时间
     *
     * @param time 音乐时长
     * @return
     */
    private String getPlayTime(long time) {

        if (time >= 0) {
            time -= 1000 * i;
        }
        return MusicUtil.formatTime(time);
    }

    private class MySeekBarListener implements SeekBar.OnSeekBarChangeListener{

        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}