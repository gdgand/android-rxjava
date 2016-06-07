package com.gdgand.rxjava.rxjavasample.queue.command;

import com.gdgand.rxjava.rxjavasample.queue.adapter.Queue2Adapter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class TopCommand implements Command {
    private final Queue2Adapter adapter;

    public TopCommand(Queue2Adapter adapter) {

        this.adapter = adapter;
    }

    @Override
    public void call() {
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Observable.just(adapter.getItemCount())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(integer -> {
                    adapter.add("Top : " + String.valueOf(integer));
                    adapter.notifyDataSetChanged();
                });

    }
}
