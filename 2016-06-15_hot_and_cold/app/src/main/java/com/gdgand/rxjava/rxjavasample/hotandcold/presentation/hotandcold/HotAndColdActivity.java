package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.hotandcold;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RadioGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.hotandcold.adapter.HotAndColdAdapter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.ReplaySubject;

import java.util.Random;

/**
 * ReplaySubject 에서 Hot And Cold Observable 동작
 */
public class HotAndColdActivity extends AppCompatActivity {

	@BindView(R.id.radioGroup)
	RadioGroup radioGroup;

	@BindView(R.id.recyclerView1)
	RecyclerView recyclerView1;

	@BindView(R.id.recyclerView2)
	RecyclerView recyclerView2;

	ReplaySubject<Integer> subject = ReplaySubject.create();
	HotAndColdAdapter adapter1;
	HotAndColdAdapter adapter2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hot_and_cold);
		ButterKnife.bind(this);


		adapter1 = new HotAndColdAdapter(this);
		recyclerView1.setAdapter(adapter1);
		recyclerView1.setLayoutManager(new LinearLayoutManager(this));

		adapter2 = new HotAndColdAdapter(this);
		recyclerView2.setAdapter(adapter2);
		recyclerView2.setLayoutManager(new LinearLayoutManager(this));

		subject.onNext(1);
		subject.onNext(2);
		subject.onNext(3);
		subject.onNext(4);
		subject.onNext(5);
		subject.onNext(6);
	}

	@OnClick(R.id.buttonSubmit)
	void clickSubmit() {
		adapter1.clearItems();
		adapter2.clearItems();

		Observable<String> observable = getObservable();
		observable
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(str -> {
				adapter1.addItem(str);
			});
		observable
			.observeOn(AndroidSchedulers.mainThread())
			.subscribe(str -> {
				adapter2.addItem(str);
			});
	}

	Observable<String> getObservable() {
		Observable<String> observable = subject
			.map(integer -> integer + ", " + new Random().nextInt(100));
		switch (radioGroup.getCheckedRadioButtonId()) {
			case R.id.radioButtonCold:
				return observable;
			case R.id.radioButtonHot:
				return observable
					.replay()
					.refCount();
			default:
				return Observable.empty();
		}
	}
}
