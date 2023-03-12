package com.example.pmdm_2223.EVA2.retrofit_listview.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VolumeImageLinks {
    @SerializedName("smallThumbnail")
    @Expose
    private String smallThumbnail;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    public String getSmallThumbnail() {
        return smallThumbnail;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}