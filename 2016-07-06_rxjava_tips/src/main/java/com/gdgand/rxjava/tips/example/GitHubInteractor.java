package com.gdgand.rxjava.tips.example;

import android.support.v4.util.LruCache;
import retrofit2.Retrofit;
import rx.Observable;

public class GitHubInteractor {
    private LruCache<String, SearchResult> cache;
    private GitHubService service;

    public GitHubInteractor(Retrofit retrofit, LruCache<String, SearchResult> cache) {
        this.cache = cache;
        service = retrofit.create(GitHubService.class);
    }

    public Observable<SearchResult> searchUsers(final String query) {
        return Observable.concat(cachedResults(query), networkResults(query)).first();
    }

    private Observable<SearchResult> cachedResults(String query) {
        return Observable.just(cache.get(query)).filter(result -> result != null);
    }

    private Observable<SearchResult> networkResults(final String query) {
        return service.searchUsers(query)
                .doOnNext(result -> cache.put(query, result));
    }
}