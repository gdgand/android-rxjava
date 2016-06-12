package com.gdgand.rxjava.rxjavasample.hotandcold.di.module;

import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApi;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Singleton;

@Module
public class DataModule {

	@Provides
	@Singleton
	public Converter.Factory converterFactory() {
		return GsonConverterFactory.create();
	}


	@Provides
	@Singleton
	public CallAdapter.Factory callAdapterFactory() {
		return RxJavaCallAdapterFactory.create();
	}

	@Provides
	@Singleton
	public OkHttpClient okHttpClient() {
		HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
		interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

		return new OkHttpClient.Builder()
			.addInterceptor(interceptor)
			.build();
	}

	@Provides
	@Singleton
	public Retrofit retrofit(OkHttpClient client, CallAdapter.Factory callAdapterFactory, Converter.Factory converterFactory) {
		return new Retrofit.Builder()
			.baseUrl("https://api.imgur.com")
			.client(client)
			.addCallAdapterFactory(callAdapterFactory)
			.addConverterFactory(converterFactory)
			.build();
	}

	@Provides
	@Singleton
	public ImgurApi imgurApi(Retrofit retrofit) {
		return retrofit.create(ImgurApi.class);
	}
}
