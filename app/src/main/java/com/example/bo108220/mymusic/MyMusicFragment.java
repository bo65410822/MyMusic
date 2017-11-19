package com.example.bo108220.mymusic;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.bo108220.mymusic.adapter.MyMusicAdapter;
import com.example.bo108220.mymusic.bean.Mp3Info;
import com.example.bo108220.mymusic.utils.MusicUtil;

import java.util.List;

/**
 * 创建时间：2017/11/10 14:36
 * 作者：Li zhb
 * 功能描述：本地音乐的fragment
 */
public class MyMusicFragment extends Fragment implements AdapterView.OnItemClickListener{

    private static final String TAG = "MyMusicFragment";
    private View view;
    private List<Mp3Info> mp3Infos;
    private MainActivity mainActivity;
    private ListView myMusicView;
    private MyMusicAdapter myMusicAdapter;
    private Mp3Info currentMp3Info;


    public static MyMusicFragment newInstance() {

        Bundle args = new Bundle();

        MyMusicFragment fragment = new MyMusicFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_music_fragment, container, false);
        mp3Infos = MusicUtil.getMp3Infos(mainActivity);
        myMusicAdapter = new MyMusicAdapter(mp3Infos,mainActivity);
        findView();
        mainActivity.bindPlayService();
        return view;
    }

    private void findView() {
        myMusicView = view.findViewById(R.id.my_music_list);
        myMusicView.setAdapter(myMusicAdapter);
        myMusicView.setOnItemClickListener(this);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Log.e(TAG, "onItemClick: " + i );
        mainActivity.playService.start(i);
        currentMp3Info = mp3Infos.get(i);
        mainActivity.setCurrentMp3Info();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainActivity.unbindPlayService();
    }

}
