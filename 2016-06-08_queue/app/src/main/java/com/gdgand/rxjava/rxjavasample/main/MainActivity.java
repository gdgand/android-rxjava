package com.gdgand.rxjava.rxjavasample.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.gdgand.rxjava.rxjavasample.R;
import com.gdgand.rxjava.rxjavasample.main.adapter.MainAdapter;
import com.gdgand.rxjava.rxjavasample.queue.Queue1Activity;
import com.gdgand.rxjava.rxjavasample.queue.Queue2Activity;
import com.gdgand.rxjava.rxjavasample.scroll.FasterListActivity;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final static String[] TITLES = {
            "Queue - Notification",
            "Queue - Command",
            "Scrolling In RecyclerView"
    };

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        MainAdapter adapter = new MainAdapter(MainActivity.this);
        adapter.addAll(Arrays.asList(TITLES));
        rvMain.setAdapter(adapter);
        rvMain.setLayoutManager(new LinearLayoutManager(MainActivity.this));


        adapter.setOnItemClickListener((adapter1, position) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, Queue1Activity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, Queue2Activity.class));
                    break;
                case 2:
                    startActivity(new Intent(MainActivity.this, FasterListActivity.class));
                    break;
            }
        });
    }
}
