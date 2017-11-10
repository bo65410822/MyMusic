package com.example.bo108220.mymusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 创建时间：2017/11/10 14:36
 * 作者：Li zhb
 * 功能描述：本地音乐的fragment
 */
public class MyMusicFragment extends Fragment {

    private View view;

    public static MyMusicFragment newInstance() {

        Bundle args = new Bundle();

        MyMusicFragment fragment = new MyMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_music_fragment, container, false);
        return view;
    }
}
