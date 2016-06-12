package com.gdgand.rxjava.rxjavasample.hotandcold.di.component;

import com.gdgand.rxjava.rxjavasample.hotandcold.di.PerActivity;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.ActivityModule;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur.ImgurActivity;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvvm.imgur.MvvmActivity;

import dagger.Component;


@PerActivity
@Component(dependencies = { ApplicationComponent.class }, modules = { ActivityModule.class })
public interface ActivityComponent {

	void inject(MvvmActivity activity);
	void inject(ImgurActivity activity);
}
