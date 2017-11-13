package com.example.bo108220.mymusic;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.example.bo108220.mymusic.service.PlayService;

/**
 * 创建时间：2017/11/11 15:10
 * 作者：Li zhb
 * 功能描述：Activity的基类
 */

public abstract class BaseActivity extends FragmentActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public PlayService playService = null;
    private boolean isBound = false;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            isBound = true;
            PlayService.BindPlay bindPlay = (PlayService.BindPlay) iBinder;
            playService = bindPlay.getPlayService();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            playService = null;
            isBound = false;
        }
    };


    /**
     * 绑定服务
     */
    @SuppressLint("WrongConstant")
    public void bindPlayService() {
        if (!isBound){
            Intent intent = new Intent();
            intent.setClass(this,PlayService.class);
            bindService(intent, conn, Service.BIND_AUTO_CREATE);
            isBound  = true;
        }
    }

    /**
     * 解除绑定
     */
    public void unbindPlayService() {
        if (isBound) {
            unbindService(conn);
            isBound = false;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindPlayService();
    }
}
