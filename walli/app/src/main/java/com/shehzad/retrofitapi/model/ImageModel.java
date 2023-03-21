package com.shehzad.retrofitapi.model;

import java.util.List;

public class ImageModel {
    private int total;
    private int totalHits;
    private List<Hits> hits;

    public ImageModel(int totalHits, int total, List<Hits> hits) {
        this.totalHits = totalHits;
        this.total = total;
        this.hits = hits;
    }

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Hits> getHits() {
        return hits;
    }

    public void setHits(List<Hits> hits) {
        this.hits = hits;
    }
}
