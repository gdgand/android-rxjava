package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvvm.imgur;

import android.util.Log;
import android.util.Pair;

import com.annimon.stream.Collectors;
import com.annimon.stream.Stream;
import com.annimon.stream.function.Function;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApi;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.Topic;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.TopicResponse;
import com.jakewharton.rx.transformer.ReplayingShare;

import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static rx.Observable.combineLatest;
import static rx.schedulers.Schedulers.computation;

public class MvvmViewModel {

	enum VisibleType {
		ALL, ODD, EVEN,
	}

	private ImgurApi imgurApi;

	private BehaviorSubject<List<Topic>> postsSubject = BehaviorSubject.create(Collections.<Topic>emptyList());
	private BehaviorSubject<VisibleType> visibleTypeSubject = BehaviorSubject.create(VisibleType.ALL);
	private PublishSubject<Throwable> throwableSubject = PublishSubject.create();

	private Observable<List<Topic>> visibleTopicsObservable;

	@Inject
	public MvvmViewModel(ImgurApi imgurApi) {
		this.imgurApi = imgurApi;
		this.visibleTopicsObservable = combineLatest(
			postsSubject.observeOn(computation()),
			visibleTypeSubject.observeOn(computation()),
			(topics, visibleType) -> {
				Log.i("MVVM", "필터링");
				if (visibleType == VisibleType.ALL) {
					return topics;
				}
				List<Topic> filtered = new ArrayList<>();
				for (int i = 0; i < topics.size(); i++) {
					if ((visibleType == VisibleType.EVEN && i % 2 == 0) || (visibleType == VisibleType.ODD && i % 2 != 0)) {
						filtered.add(topics.get(i));
					}
				}
				return filtered;
			}
		).compose(ReplayingShare.instance());
	}

	public Observable<List<String>> getPosts() {
		return visibleTopicsObservable.onBackpressureBuffer()
			.map(topics ->
				Stream.of(topics).map(Topic::getName).collect(Collectors.toList())
			);
	}

	public Observable<Pair<Integer, Integer>> getCounts() {
		return visibleTopicsObservable.onBackpressureBuffer()
			.map(topics -> {
				int album = 0;
				int noAlbum = 0;
				for (Topic topic : topics) {
					if (topic.getTopPost().is_album()) {
						album++;
					} else {
						noAlbum++;
					}
				}
				return Pair.create(album, noAlbum);
			});
	}

	public Observable<Throwable> getThrowable() {
		return throwableSubject.onBackpressureBuffer();
	}

	public void refresh() {
		imgurApi.defaultTopics()
			.map(TopicResponse::getData)
			.subscribeOn(Schedulers.io())
			.subscribe(posts -> {
				postsSubject.onNext(posts);
			}, throwable -> {
				throwableSubject.onNext(throwable);
			});
	}

	public void modifyVisibleType(VisibleType visibleType) {
		visibleTypeSubject.onNext(visibleType);
	}

}
