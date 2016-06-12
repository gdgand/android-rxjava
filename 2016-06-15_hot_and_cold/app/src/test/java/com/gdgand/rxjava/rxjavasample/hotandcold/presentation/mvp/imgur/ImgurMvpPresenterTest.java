package com.gdgand.rxjava.rxjavasample.hotandcold.presentation.mvp.imgur;

import com.gdgand.rxjava.rxjavasample.hotandcold.TestSchedulerProxy;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.ImgurApi;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.Topic;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur.TopicResponse;
import com.google.common.collect.Lists;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.gdgand.rxjava.rxjavasample.hotandcold.TestTransformer.pass;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ImgurMvpPresenterTest {

	@Mock
	ImgurApi imgurApi;

	@Mock
	ImgurMvpView view;

	@InjectMocks
	ImgurMvpPresenter presenter;

	@Before
	public void setup() {
		presenter.attachView(view);
		given(view.bindToLifecycle()).willReturn(pass());
		given(view.injectProgress()).willReturn(pass());
	}

	@After
	public void finish() {
		presenter.detachView();
	}

	TestSchedulerProxy proxy = TestSchedulerProxy.get();

	@Test
	public void testRefreshTopics() throws Exception {
		// given
		TopicResponse response = new TopicResponse();
		List<Topic> topics = Lists.newArrayList(new Topic(), new Topic(), new Topic());
		response.setData(topics);
		given(imgurApi.defaultTopics()).willReturn(Observable.just(response));

		// when
		presenter.refreshTopics();

		// then
		proxy.advanceBy(1, TimeUnit.SECONDS);
		verify(view, times(1)).bindToLifecycle();
		verify(view, times(1)).injectProgress();
		verify(view, times(1)).showTopics(topics);
	}
}