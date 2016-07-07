package com.gdgand.rxjava.tips.example;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResult {
    @SerializedName("total_count") private int totalCount;
    private List<SearchItem> items;

    public int getTotalCount() {
        return totalCount;
    }

    public List<SearchItem> getItems() {
        return items;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setItems(List<SearchItem> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchResult result = (SearchResult) o;

        if (totalCount != result.totalCount) return false;
        return !(items != null ? !items.equals(result.items) : result.items != null);
    }

    @Override
    public int hashCode() {
        int result = totalCount;
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }
}