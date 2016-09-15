package com.example.akamenov.a01newsinformation;

/**
 * Created by AKamenov on 9/15/2016.
 */
public class NewsModel {
    private String title;
    private String description;

    public NewsModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
