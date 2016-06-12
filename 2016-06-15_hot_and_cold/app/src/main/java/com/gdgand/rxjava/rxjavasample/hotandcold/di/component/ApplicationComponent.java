package com.gdgand.rxjava.rxjavasample.hotandcold.di.component;

import android.content.Context;

import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApi;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.ApplicationModule;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.DataModule;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { ApplicationModule.class, DataModule.class })
public interface ApplicationComponent {
	Context context();
	ImgurApi imgurApi();
}
