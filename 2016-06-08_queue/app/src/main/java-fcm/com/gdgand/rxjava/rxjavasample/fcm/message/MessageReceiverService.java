package com.gdgand.rxjava.rxjavasample.fcm.message;

import android.util.Log;

import com.gdgand.rxjava.rxjavasample.fcm.message.event.NotificationEvent;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.greenrobot.eventbus.EventBus;

import java.util.Map;

public class MessageReceiverService extends FirebaseMessagingService {

    private static final String TAG = "MessageReceiverService";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Map<String, String> data = remoteMessage.getData();

        Log.d(TAG, "onMessageReceived: " + data.toString());

        String index = data.get("index");
        String message = data.get("message");

        NotificationEvent event = new NotificationEvent(index, message);
        EventBus.getDefault().post(event);
    }
}
