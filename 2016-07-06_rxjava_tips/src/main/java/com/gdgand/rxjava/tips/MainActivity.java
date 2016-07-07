package com.gdgand.rxjava.tips;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gdgand.rxjava.tips.example.FirstExampleActivity;
import com.gdgand.rxjava.tips.example.SecondExampleActivity;
import com.gdgand.rxjava.tips.example.ThirdExampleActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button1)
    public void moveToExample1 () {
        startActivity(new Intent(this, FirstExampleActivity.class));
    }

    @OnClick(R.id.button2)
    public void moveToExample2 () {
        startActivity(new Intent(this, SecondExampleActivity.class));
    }

    @OnClick(R.id.button3)
    public void moveToExample3 () {
        startActivity(new Intent(this, ThirdExampleActivity.class));
    }
}
