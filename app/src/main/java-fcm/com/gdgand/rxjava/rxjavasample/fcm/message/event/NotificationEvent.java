package com.gdgand.rxjava.rxjavasample.fcm.message.event;

public class NotificationEvent {
    private String index;
    private String message;

    public NotificationEvent(String index, String message) {

        this.index = index;
        this.message = message;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
