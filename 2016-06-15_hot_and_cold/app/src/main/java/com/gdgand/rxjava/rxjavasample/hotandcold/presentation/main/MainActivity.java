package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.gdgand.rxjava.rxjavasample.hotandcold.R;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.main.adapter.MainAdapter;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur.ImgurActivity;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvvm.imgur.MvvmActivity;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.hotandcold.HotAndColdActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

	private final static String[] TITLES = {
		"Hot And Cold",
		"Mvvm Imgur",
		"Mvp Imgur"
	};

	@BindView(R.id.rv_main)
	RecyclerView rvMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ButterKnife.bind(this);


		MainAdapter adapter = new MainAdapter(MainActivity.this);
		adapter.addAll(Arrays.asList(TITLES));
		rvMain.setAdapter(adapter);
		rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));


		adapter.setOnItemClickListener((adapter1, position) -> {
			switch (position) {
				case 0:
					startActivity(new Intent(MainActivity.this, HotAndColdActivity.class));
					break;
				case 1:
					startActivity(new Intent(MainActivity.this, MvvmActivity.class));
					break;
				case 2:
					startActivity(new Intent(MainActivity.this, ImgurActivity.class));
					break;
				default:
					break;
			}
		});
	}
}
