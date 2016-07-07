package com.gdgand.rxjava.tips.example;

// import com.fernandocejas.frodo.annotation.RxLogObservable;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface GitHubService {
    // @RxLogObservable(RxLogObservable.Scope.EVERYTHING)
    @GET("/search/users?")
    Observable<SearchResult> searchUsers(@Query("q") String query);
}