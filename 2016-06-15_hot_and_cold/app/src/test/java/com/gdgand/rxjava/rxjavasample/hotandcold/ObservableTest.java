package com.gdgand.rxjava.rxjavasample.hotandcold;

import org.junit.Test;

import rx.Observable;
import rx.observables.ConnectableObservable;

public class ObservableTest {

	@Test
	public void testColdObservable() {
		final Count count = new Count();
		Observable<String> observable = Observable
			.range(0, 10)
			.timestamp()
			.map(timestamped -> String.format("[%d] %d", timestamped.getValue(), timestamped.getTimestampMillis()))
			.doOnNext(value -> count.increase());

		observable.subscribe(value -> {
			System.out.println("subscriber1 : " + value);
		});

		observable.subscribe(value -> {
			System.out.println("subscriber2 : " + value);
		});

		System.out.println("연산횟수 : " + count.count());
	}

	@Test
	public void testHotObservable() {
		final Count count = new Count();

		ConnectableObservable<String> observable = Observable
			.range(0, 10)
			.timestamp()
			.map(timestamped -> String.format("[%d] %d", timestamped.getValue(), timestamped.getTimestampMillis()))
			.doOnNext(value -> count.increase())
			.publish();

		observable.subscribe(value -> {
			System.out.println("subscriber1 : " + value);
		});

		observable.subscribe(value -> {
			System.out.println("subscriber2 : " + value);
		});

		observable.connect();
		System.out.println("연산횟수 : " + count.count());
	}

	static class Count {
		private int count = 0;

		void increase() {
			count++;
		}

		int count() {
			return count;
		}
	}
}
