package com.gdgand.rxjava.tips.example;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class SearchItem {
    private String login;
    @SerializedName("avatar_url") private String avatarUrl;

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchItem item = (SearchItem) o;

        if (login != null ? !login.equals(item.login) : item.login != null) return false;
        return !(avatarUrl != null ? !avatarUrl.equals(item.avatarUrl) : item.avatarUrl != null);
    }

    @Override
    public int hashCode() {
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (avatarUrl != null ? avatarUrl.hashCode() : 0);
        return result;
    }
}