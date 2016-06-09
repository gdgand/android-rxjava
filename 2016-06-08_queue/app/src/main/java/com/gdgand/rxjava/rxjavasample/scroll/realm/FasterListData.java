package com.gdgand.rxjava.rxjavasample.scroll.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class FasterListData extends RealmObject {
    @PrimaryKey
    private long position;

    public long getPosition() {
        return position;
    }

    public void setPosition(long position) {
        this.position = position;
    }
}
