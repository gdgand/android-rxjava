package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur;

import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.Topic;
import com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.BaseMvpView;

import java.util.List;

public interface ImgurMvpView extends BaseMvpView {

	void showTopics(List<Topic> topics);
}
