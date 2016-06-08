package com.gdgand.rxjava.rxjavasample.fcm.token;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FCMTokenService extends FirebaseInstanceIdService {
    public static final String KEY_FCM_TOKEN = "fcm_token";
    private static final String TAG = "FCMTokenService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String fcmTokenId = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "onTokenRefresh() : Token : " + fcmTokenId);
        saveToken(fcmTokenId);
    }

    private void saveToken(String fcmTokenId) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        preferences.edit().putString(KEY_FCM_TOKEN, fcmTokenId).apply();

    }
}
