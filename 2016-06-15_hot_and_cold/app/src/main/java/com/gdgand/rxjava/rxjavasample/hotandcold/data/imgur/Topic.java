package com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur;

public class Topic {
    private long id;

    private String name;

    private String description;

    private TopPost topPost;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TopPost getTopPost() {
        return topPost;
    }

    public void setTopPost(TopPost topPost) {
        this.topPost = topPost;
    }
}
