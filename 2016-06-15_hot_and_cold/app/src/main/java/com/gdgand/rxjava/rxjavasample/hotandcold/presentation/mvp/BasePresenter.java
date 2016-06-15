package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp;

public abstract class BasePresenter<VIEW extends BaseMvpView> {

	private VIEW view;

	public void attachView(VIEW view) {
		this.view = view;
	}

	public void detachView() {
		view = null;
	}

	protected VIEW view() {
		return view;
	}
}
