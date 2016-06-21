package com.gdgand.rxjava.onestreamvsmulticast.command;

import android.widget.TextView;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MulticastCommand implements Command {
    private final TextView tvMain;

    public MulticastCommand(TextView tvMain) {

        this.tvMain = tvMain;
    }

    @Override
    public void call() {
        Observable<String> stringObservable = Observable.just("a1", "a2", "b3", "b4", "a5", "b6")
                .publish().refCount();

        stringObservable.observeOn(Schedulers.io())
                .filter(it -> it.contains("a"))
                .doOnNext(it -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    tvMain.setText(tvMain.getText() + "\n" + s);
                });

        stringObservable.observeOn(Schedulers.io())
                .filter(it -> it.contains("b"))
                .doOnNext(it -> {
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    tvMain.setText(tvMain.getText() + "\n" + s);
                });

    }
}
