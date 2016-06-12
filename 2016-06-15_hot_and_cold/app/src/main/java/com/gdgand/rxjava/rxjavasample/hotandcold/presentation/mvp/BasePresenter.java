package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter<VIEW extends BaseMvpView> {

	private CompositeSubscription subscriptions = new CompositeSubscription();

	private VIEW view;

	public void attachView(VIEW view) {
		this.view = view;
	}

	public void detachView() {
		subscriptions.unsubscribe();
		view = null;
	}

	protected VIEW view() {
		return view;
	}

	protected void addSubscription(Subscription subscription) {
		subscriptions.add(subscription);
	}
}
