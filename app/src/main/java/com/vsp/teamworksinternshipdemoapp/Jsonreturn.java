package com.vsp.teamworksinternshipdemoapp;

import com.google.gson.annotations.SerializedName;

public class Jsonreturn {



    int albumId;
    @SerializedName("id")
    int id;

    String title;

    String url;

    String thumbnailUrl;

    public Jsonreturn(int albumId, int id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getAlbumId() {
        return albumId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
