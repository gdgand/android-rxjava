package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;
import com.gdgand.rxjava.rxjavasample.hotandcold.SampleApplication;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.Topic;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.component.DaggerActivityComponent;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.ActivityModule;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.BaseMvpActivity;

import java.util.List;

import javax.inject.Inject;

public class ImgurActivity extends BaseMvpActivity implements ImgurMvpView {

	@Inject
	ImgurMvpPresenter presenter;

	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	private ImgurAdapter adapter;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mvp_imgur);
		ButterKnife.bind(this);

		adapter = new ImgurAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));

		onInject();

		presenter.attachView(this);
		presenter.refreshTopics();
	}

	@Override
	protected void onDestroy() {
		presenter.detachView();
		super.onDestroy();
	}

	@OnClick(R.id.buttonSubmit)
	void clickSubmit() {
		presenter.refreshTopics();
	}

	@Override
	public void showTopics(List<Topic> topics) {
		adapter.setDatas(topics);
	}

	void onInject() {
		DaggerActivityComponent
			.builder()
			.applicationComponent(((SampleApplication)getApplication()).getApplicationComponent())
			.activityModule(new ActivityModule(this))
			.build()
			.inject(this);
	}

}
