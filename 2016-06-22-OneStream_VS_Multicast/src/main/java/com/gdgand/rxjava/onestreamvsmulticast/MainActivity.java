package com.gdgand.rxjava.onestreamvsmulticast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gdgand.rxjava.onestreamvsmulticast.command.MulticastCommand;
import com.gdgand.rxjava.onestreamvsmulticast.command.OneStreamCommand;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_main)
    TextView tvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_multi)
    void onMulticastClick() {
        tvMain.setText("");
        new MulticastCommand(tvMain).call();
    }

    @OnClick(R.id.btn_one)
    void onOnestreamClick() {
        tvMain.setText("");
        new OneStreamCommand(tvMain).call();
    }
}
