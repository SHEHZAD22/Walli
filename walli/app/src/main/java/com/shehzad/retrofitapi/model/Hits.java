package com.shehzad.retrofitapi.model;

import com.google.gson.annotations.SerializedName;

public class Hits {
    private int views;
    private int likes;
    private String tags;
    @SerializedName("previewURL")
    private String firstImage;
    @SerializedName("largeImageURL")
    private String secondImage;

    public Hits(int views, int likes, String firstImage, String secondImage, String tags) {
        this.views = views;
        this.likes = likes;
        this.tags = tags;
        this.firstImage = firstImage;
        this.secondImage = secondImage;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getSecondImage() {
        return secondImage;
    }

    public void setSecondImage(String secondImage) {
        this.secondImage = secondImage;
    }
}
