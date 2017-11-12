package com.example.bo108220.mymusic.bean;

import java.io.Serializable;

/**
 * 创建时间：2017/11/10 17:53
 * 作者：Li zhb
 * 功能描述：Mp3Info实体类
 */
public class Mp3Info implements Serializable {

    private long id;
    private String title;
    private String artist;
    private long duration;
    private long size;
    private String url;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
