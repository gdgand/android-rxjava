package com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur;

import java.util.Date;

public class TopPost {

    private String id;

    private String title;

    private String description;

    private boolean is_album;

    private String link;


    // for album
    private String cover;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean is_album() {
        return is_album;
    }

    public void setIs_album(boolean is_album) {
        this.is_album = is_album;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
