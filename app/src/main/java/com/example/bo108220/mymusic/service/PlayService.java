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
    public MediaPlayer mp;
    private BindPlay bindPlay = new BindPlay();
    private int currentPosition;
    private Mp3Info mp3Info;//正在播放的音乐信息
    private CallbackMusic callBackMusic;
    private boolean isPlay;
    public void setCallBackMusic(CallbackMusic callBackMusic) {
        this.callBackMusic = callBackMusic;
    }

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
        mp.setOnCompletionListener(new CompletionListener());
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
        mp3Info = mp3Infos.get(position);
        try {
            mp.reset(); //重置多媒体
            String url = mp3Info.getUrl();
            mp.setDataSource(url);
            mp.prepare();
            mp.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentPosition = position;
        callBackMusic.onCurrentMusic(mp3Info);
        isPlay = true;
    }

    /**
     * 暂停播放
     */
    public void pause() {
        if (mp.isPlaying()) {
            mp.pause();
        }
        isPlay = false;
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

    /**
     * 返回是否播放
     * @return
     */
    public boolean isPlay(){
        return isPlay;
    }

    /**
     * 回调音乐接口
     */
    public interface CallbackMusic{
        /**
         * 当前Music
         * @param mp3Info
         */
        public void onCurrentMusic(Mp3Info mp3Info);
    }

    /**
     * 自动播放下一曲
     */
    private final class CompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
           next();
        }

    }
}
