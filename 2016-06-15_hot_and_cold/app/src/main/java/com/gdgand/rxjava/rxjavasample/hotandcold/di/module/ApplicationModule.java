package com.gdgand.rxjava.rxjavasample.hotandcold.di.module;

import android.app.Application;
import android.content.Context;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class ApplicationModule {
	private Application application;
	public ApplicationModule(Application application) {
		this.application = application;
	}

	@Provides
	@Singleton
	public Context context() {
		return application;
	}

}
