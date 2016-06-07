package com.gdgand.rxjava.rxjavasample.queue;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gdgand.rxjava.rxjavasample.R;
import com.gdgand.rxjava.rxjavasample.queue.adapter.Queue2Adapter;
import com.gdgand.rxjava.rxjavasample.queue.command.BottomCommand;
import com.gdgand.rxjava.rxjavasample.queue.command.Command;
import com.gdgand.rxjava.rxjavasample.queue.command.TopCommand;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

public class Queue2Activity extends AppCompatActivity {

    @BindView(R.id.rv_queue2)
    RecyclerView rvQueue;

    private PublishSubject<Command> commandQueue;
    private Queue2Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue2);

        ButterKnife.bind(this);

        initObject();
        initViews();
    }

    private void initObject() {
        commandQueue = PublishSubject.create();
        commandQueue.onBackpressureBuffer()
                .observeOn(Schedulers.io())
                .subscribe(Command::call, Throwable::printStackTrace);
    }

    private void initViews() {
        rvQueue.setLayoutManager(new LinearLayoutManager(Queue2Activity.this));
        adapter = new Queue2Adapter(Queue2Activity.this);
        rvQueue.setAdapter(adapter);
    }

    @OnClick(R.id.btn_queue2_add_bottom)
    void onAddBottomClick() {
        commandQueue.onNext(new BottomCommand(adapter));
    }

    @OnClick(R.id.btn_queue2_add_top)
    void onAddTopClick() {
        commandQueue.onNext(new TopCommand(adapter));
    }
}
