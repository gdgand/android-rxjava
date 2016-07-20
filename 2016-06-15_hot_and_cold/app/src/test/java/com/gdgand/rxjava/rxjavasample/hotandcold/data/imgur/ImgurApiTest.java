package com.gdgand.rxjava.rxjavasample.hotandcold.data.imgur;

import com.annimon.stream.Stream;
import com.gdgand.rxjava.rxjavasample.hotandcold.data.DaggerTestDataComponent;
import com.gdgand.rxjava.rxjavasample.hotandcold.di.module.DataModule;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import rx.observers.TestSubscriber;

import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

@Ignore("Ignore e2e test by default")
public class ImgurApiTest {

	@Inject
	ImgurApi imgurApi;

	@Before
	public void setup() {
		DaggerTestDataComponent.builder()
			.dataModule(new DataModule())
			.build()
			.inject(this);
	}

	@Test
	public void testDefaultTopicsWithTestSubscriber() throws Exception {
		// given
		TestSubscriber<TopicResponse> subscriber = TestSubscriber.create();

		// when
		imgurApi.defaultTopics().subscribe(subscriber);

		// then
		subscriber.awaitTerminalEvent();
		List<TopicResponse> items = subscriber.getOnNextEvents();

		assertThat(items).hasSize(1);
		Stream.of(items).forEach(value -> assertThat(value.getData()).isNotEmpty());
	}

	@Test
	public void testDefaultTopicsWithBlocking() throws Exception {
		// given

		// when
		Iterator<TopicResponse> response = imgurApi.defaultTopics().toBlocking().getIterator();

		// then
		assertThat(response).hasSize(1);
		Stream.of(response).forEach(value -> assertThat(value.getData()).isNotEmpty());
	}
}