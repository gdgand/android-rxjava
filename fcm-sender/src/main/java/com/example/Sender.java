package com.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class Sender {

    private static final String SERVER_KEY = "AIzaSyDuiS6N5E3lksUgwwIvd8_9Vj3PjJIcET8";
    private static final String FCM_URL = "https://fcm.googleapis.com";
    private static final String REGISTRATION_ID = "cnntzxQKsE0:APA91bGaDWjvZT82ftU5iZR214t74M08Kz48eO3Y_M3oH5apNNi7dvB3A1cYHA9tXWzrqr-0IB7JDHVARIylWnfbX8FvXzTW6d_f0uEnjll9EjqKZeK5pz_gYVPO9UgVOgtGSPQp4RoU";

    public static void main(String[] args) throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FCM_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        for (int i = 0; i < 20; i++) {
            Message message = new Message();
            message.setTo(REGISTRATION_ID);
            HashMap<String, Object> data = new HashMap<>();
            data.put("index", String.valueOf(i));
            data.put("message", "Hello World : " + i);
            message.setData(data);
            Response<Map<String, Object>> response = retrofit.create(FirebaseCM.class)
                    .send(message).execute();

            System.out.println(response.body());
        }
    }


    public interface FirebaseCM {
        @POST("/fcm/send")
        @Headers(value = "Authorization:Key=" + SERVER_KEY)
        Call<Map<String, Object>> send(@Body Message message);
    }

    public static class Message {
        private Map<String, Object> data;
        private String to;

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }
    }

}