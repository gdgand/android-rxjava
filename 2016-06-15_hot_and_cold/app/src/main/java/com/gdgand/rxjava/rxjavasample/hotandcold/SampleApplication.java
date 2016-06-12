package com.gdgand.rxjava.rxjavasample.hotandcold;

import android.app.Application;

import com.gdgand.rxjava.rxjavasample.hotandcold.di.component.ApplicationComponent;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.component.DaggerApplicationComponent;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.ApplicationModule;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.DataModule;

public class SampleApplication extends Application {
	private ApplicationComponent applicationComponent;

	@Override
	public void onCreate() {
		super.onCreate();
		applicationComponent = createComponent();
	}

	private ApplicationComponent createComponent() {
		return DaggerApplicationComponent.builder()
			.applicationModule(new ApplicationModule(this))
			.dataModule(new DataModule())
			.build();
	}

	public ApplicationComponent getApplicationComponent() {
		return applicationComponent;
	}

}
