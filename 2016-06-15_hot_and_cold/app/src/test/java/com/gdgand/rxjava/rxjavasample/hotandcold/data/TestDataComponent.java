package com.gdgand.rxjava.rxjavasample.hotandcold.data;

import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApiTest;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.DataModule;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = { DataModule.class })
public interface TestDataComponent {

	void inject(ImgurApiTest imgurApiTest);
}
