package com.gdgand.rxjava.tips.example;

import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.gdgand.rxjava.tips.MainActivity;
import com.gdgand.rxjava.tips.R;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import java.util.Collections;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SecondExampleActivity extends RxAppCompatActivity {
    @BindView(R.id.search_bar) EditText searchBar;
    @BindView(R.id.results) RecyclerView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        ButterKnife.bind(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        results.setLayoutManager(layoutManager);
        results.setHasFixedSize(true);

        final SearchRecyclerAdapter adapter = new SearchRecyclerAdapter(Collections.emptyList());
        results.setAdapter(adapter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LruCache<String, SearchResult> cache = new LruCache<>(5 * 1024 * 1024); // 5MiB

        final GitHubInteractor interactor = new GitHubInteractor(retrofit, cache);

        RxUserBus.sub().compose(bindToLifecycle()).subscribe((String s) -> {
            Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
        });

        RxTextView.textChanges(searchBar)
                .compose(bindToLifecycle())
                .observeOn(Schedulers.io())
                .debounce(1, TimeUnit.SECONDS)
                .filter(charSequence -> charSequence.length() > 0)
                .switchMap((CharSequence seq) -> interactor.searchUsers(seq.toString()))
                .flatMap((SearchResult searchResult) ->
                        Observable.from(searchResult.getItems()).limit(20).toList())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::refreshResults, throwable -> {
                    Log.e(MainActivity.class.getName(), throwable.getMessage());
                });
    }
}