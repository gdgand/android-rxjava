package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur;

import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApi;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.TopicResponse;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.BasePresenter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import javax.inject.Inject;

public class ImgurMvpPresenter extends BasePresenter<ImgurMvpView> {

	private ImgurApi imgurApi;

	@Inject
	public ImgurMvpPresenter(ImgurApi imgurApi) {
		this.imgurApi = imgurApi;
	}

	public void refreshTopics() {
		addSubscription(
			imgurApi.defaultTopics()
				.map(TopicResponse::getData)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.compose(view().bindToLifecycle())
				.compose(view().injectProgress())
				.subscribe(topics -> {
					view().showTopics(topics);
				}, throwable -> {
					view().showThrowable(throwable);
				})
		);

	}
}
