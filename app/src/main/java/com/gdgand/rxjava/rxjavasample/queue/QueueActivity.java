package com.gdgand.rxjava.rxjavasample.queue;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gdgand.rxjava.rxjavasample.R;
import com.gdgand.rxjava.rxjavasample.fcm.message.event.NotificationEvent;
import com.google.firebase.iid.FirebaseInstanceId;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.subjects.PublishSubject;

public class QueueActivity extends AppCompatActivity {

    @BindView(R.id.tv_queue_status)
    TextView tvQueueStatus;

    private boolean isQueue = false;
    private NotificationManager notificationManager;

    private PublishSubject<Pair<String, String>> subject;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queue);

        ButterKnife.bind(this);

        initObject();

        String token = FirebaseInstanceId.getInstance().getToken();
        System.out.println("token : " + token);

        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    private void initObject() {
        notificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));

        subject = PublishSubject.create();
        subject.onBackpressureBuffer()
                .throttleLast(700, TimeUnit.MILLISECONDS)
                .subscribe(pair -> {
                    notifyMessage(pair.first, pair.second);
                });
    }

    @Subscribe
    public void onNotificationEvent(NotificationEvent event) {
        String index = event.getIndex();
        String message = event.getMessage();

        if (!isQueue) {

            notifyMessage(index, message);
        } else {
            subject.onNext(Pair.create(index, message));
        }
    }

    private void notifyMessage(String index, String message) {
        int number = Integer.parseInt(index);
        Notification notification = new Notification.Builder(getApplicationContext())
                .setContentTitle("Queue")
                .setTicker(message)
                .setContentText(message)
                .setNumber(number)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setDefaults(Notification.DEFAULT_ALL)
                .build();
        notificationManager.notify(number, notification);
    }

    @OnClick(R.id.tv_queue_non_subject)
    void onNonSubjectClick() {
        isQueue = false;
        updateNotificationStatus();
    }

    @OnClick(R.id.tv_queue_subject)
    void onWithSubjectClick() {
        isQueue = true;
        updateNotificationStatus();
    }

    private void updateNotificationStatus() {
        tvQueueStatus.setText("Queue Status : " + isQueue);
    }
}
