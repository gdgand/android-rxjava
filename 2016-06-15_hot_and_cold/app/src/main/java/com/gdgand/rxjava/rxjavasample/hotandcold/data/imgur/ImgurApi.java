package com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur;

import retrofit2.http.GET;
import rx.Observable;

public interface ImgurApi {

	@GET("/3/topics/defaults")
	Observable<TopicResponse> defaultTopics();
}
