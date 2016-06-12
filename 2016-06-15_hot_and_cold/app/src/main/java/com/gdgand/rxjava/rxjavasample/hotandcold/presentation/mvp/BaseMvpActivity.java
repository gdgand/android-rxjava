package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp;

import android.app.ProgressDialog;
import android.widget.Toast;

import com.gdgand.rxjava.rxjavasample.hotandcold.views.progress.ProgressIndicatorProvider;
import com.trello.rxlifecycle.components.RxActivity;

import rx.Observable;
import rx.functions.Action0;

import javax.inject.Inject;

public abstract class BaseMvpActivity extends RxActivity implements BaseMvpView {

	@Inject
	ProgressIndicatorProvider progressIndicatorProvider;

	@Override
	public <T> Observable.Transformer<T, T> injectProgress() {
		ProgressDialog progress = progressIndicatorProvider.provide();
		return observable -> observable
			.doOnSubscribe(progress::show)
			.doOnUnsubscribe((Action0) progress::dismiss);
	}

	@Override
	public void showThrowable(Throwable throwable) {
		Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
	}
}
