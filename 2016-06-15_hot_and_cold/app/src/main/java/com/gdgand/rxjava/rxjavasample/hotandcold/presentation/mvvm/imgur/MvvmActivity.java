package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvvm.imgur;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;
import com.gdgand.rxjava.rxjavasample.hotandcold.SampleApplication;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.component.DaggerActivityComponent;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.ActivityModule;

import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

import javax.inject.Inject;

public class MvvmActivity extends AppCompatActivity {

	@Inject
	MvvmViewModel viewModel;

	@BindView(R.id.textViewResult)
	TextView textViewResult;

	@BindView(R.id.recyclerView)
	RecyclerView recyclerView;

	@BindView(R.id.radioGroup)
	RadioGroup radioGroup;

	CompositeSubscription subscriptions;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mvvm_imgur);
		ButterKnife.bind(this);

		MvvmAdapter adapter = new MvvmAdapter(this);
		recyclerView.setAdapter(adapter);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
			switch (checkedId) {
				case R.id.radioButtonAll:
					viewModel.modifyVisibleType(MvvmViewModel.VisibleType.ALL);
					break;
				case R.id.radioButtonOdd:
					viewModel.modifyVisibleType(MvvmViewModel.VisibleType.ODD);
					break;
				case R.id.radioButtonEven:
					viewModel.modifyVisibleType(MvvmViewModel.VisibleType.EVEN);
					break;
				default:
					break;
			}
		});

		onInject();

		subscriptions = new CompositeSubscription();

		subscriptions.add(viewModel.getPosts()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(adapter::setDatas, this::showError));

		subscriptions.add(viewModel.getCounts()
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(pair -> {
				textViewResult.setText(String.format("앨범 %d개, 앨범아닌건 %d개", pair.first, pair.second));
			}, this::showError));

		viewModel.refresh();
	}

	@Override
	protected void onDestroy() {
		subscriptions.unsubscribe();
		super.onDestroy();
	}

	void onInject() {
		DaggerActivityComponent
			.builder()
			.applicationComponent(((SampleApplication)getApplication()).getApplicationComponent())
			.activityModule(new ActivityModule(this))
			.build()
			.inject(this);
	}

	void showError(Throwable throwable) {
		Toast.makeText(this, throwable.getMessage(), Toast.LENGTH_LONG).show();
	}

}
