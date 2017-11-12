package com.example.bo108220.mymusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bo108220.mymusic.R;
import com.example.bo108220.mymusic.bean.Mp3Info;
import com.example.bo108220.mymusic.utils.MusicUtil;

import java.util.List;

/**
 * 创建时间：2017/11/11 11:54
 * 作者：Li zhb
 * 功能描述：本地音乐适配器
 */

public class MyMusicAdapter extends BaseAdapter {

    private List<Mp3Info> mp3Infos;
    private Context context;

    public MyMusicAdapter(List<Mp3Info> mp3Infos, Context context) {

        this.mp3Infos = mp3Infos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mp3Infos.size();
    }

    @Override
    public Object getItem(int i) {
        return mp3Infos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = View.inflate(context, R.layout.mp3_list_item, null);
            holder.mp3Title = view.findViewById(R.id.music_item_title);
            holder.mp3Name = view.findViewById(R.id.music_item_name);
            holder.mp3Time = view.findViewById(R.id.music_item_time);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.mp3Title.setText(mp3Infos.get(i).getTitle());
        holder.mp3Name.setText(mp3Infos.get(i).getArtist());
        holder.mp3Time.setText(MusicUtil.formatTime(mp3Infos.get(i).getDuration()));
        return view;
    }

    private class ViewHolder {
        TextView mp3Title, mp3Name,mp3Time;
    }
}
