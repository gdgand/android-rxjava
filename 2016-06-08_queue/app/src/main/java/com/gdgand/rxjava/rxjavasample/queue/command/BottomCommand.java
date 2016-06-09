package com.gdgand.rxjava.rxjavasample.queue.command;

import com.gdgand.rxjava.rxjavasample.queue.adapter.Queue2Adapter;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class BottomCommand implements Command {
    private final Queue2Adapter adapter;

    public BottomCommand(Queue2Adapter adapter) {

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
                    adapter.add(0, "Bottom : " + String.valueOf(integer));
                    adapter.notifyDataSetChanged();
                });
    }
}
