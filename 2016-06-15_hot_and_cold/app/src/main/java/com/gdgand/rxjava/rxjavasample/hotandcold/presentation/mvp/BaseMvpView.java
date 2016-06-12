package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp;

import rx.Observable;

public interface BaseMvpView {

	<T> Observable.Transformer<T, T> bindToLifecycle();

	<T> Observable.Transformer<T, T> injectProgress();

	void showThrowable(Throwable throwable);
}
