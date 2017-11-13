package com.example.bo108220.mymusic.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.bo108220.mymusic.bean.Mp3Info;
import com.example.bo108220.mymusic.constants.Constants;
import com.example.bo108220.mymusic.utils.MusicUtil;

import java.io.IOException;
import java.util.List;

public class PlayService extends Service {

    private List<Mp3Info> mp3Infos;
    private MediaPlayer mp;
    private BindPlay bindPlay = new BindPlay();
    private int currentPosition;

    public PlayService() {

    }

    public class BindPlay extends Binder {

        public PlayService getPlayService() {
            return PlayService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return bindPlay;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        mp = new MediaPlayer();
        mp3Infos = MusicUtil.getMp3Infos(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 开始播放
     *
     * @param position
     */
    public void start(int position) {

        try {
            mp.reset(); //重置多媒体
            String url = mp3Infos.get(position).getUrl();
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPosition = position;
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mp.isPlaying()) {
            mp.pause();
        }
    }

    /**
     * 下一曲
     */
    public void next() {
        if (currentPosition + 1 >= mp3Infos.size()) {
            currentPosition = 0;
        } else {
            currentPosition++;
        }
        start(currentPosition);
    }

    /**
     * 上一曲
     */
    public void prev() {
        if (currentPosition - 1 <= 0) {
            currentPosition = mp3Infos.size() - 1;
        } else {
            currentPosition--;
        }
        start(currentPosition);
    }
}
