package com.gdgand.rxjava.rxjavasample.hotandcold.di.module;

import android.app.Activity;

import com.gdgand.rxjava.rxjavasample.hotandcold.di.PerActivity;
import com.gdgand.rxjava.rxjavasample.hotandcold.views.progress.ProgressIndicatorProvider;

import dagger.Module;
import dagger.Provides;

@Module
public class ActivityModule {

	private Activity activity;

	public ActivityModule(Activity activity) {
		this.activity = activity;
	}

	@Provides
	@PerActivity
	public Activity activity() {
		return activity;
	}

	@Provides
	@PerActivity
	public ProgressIndicatorProvider progressIndicatorProvider(Activity activity) {
		return new ProgressIndicatorProvider(activity);
	}
}
