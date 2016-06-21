package com.gdgand.rxjava.onestreamvsmulticast.command;

import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class OneStreamCommand implements Command {
    private final TextView tvMain;

    public OneStreamCommand(TextView tvMain) {

        this.tvMain = tvMain;
    }

    @Override
    public void call() {
        Observable.just("a1", "a2", "b3", "b4", "a5", "b6")
                .concatMap(it -> {

                    if (it.contains("a")) {
                        return Observable.just(it)
                                .compose(this::observableA);
                    } else if (it.contains("b")) {
                        return Observable.just(it)
                                .compose(this::observableB);
                    } else {
                        return Observable.just(it);
                    }

                })
                .subscribe();

    }

    private Observable<String> observableB(Observable<String> stringObservable) {
        return stringObservable.observeOn(Schedulers.io())
                .doOnNext(s -> {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s1 -> tvMain.setText(tvMain.getText() + "\n" + s1));
    }

    private Observable<String> observableA(Observable<String> stringObservable) {
        return stringObservable.observeOn(Schedulers.io())
                .doOnNext(s -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s1 -> tvMain.setText(tvMain.getText() + "\n" + s1));
    }
}
