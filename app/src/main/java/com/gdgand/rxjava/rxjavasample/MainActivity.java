package com.gdgand.rxjava.rxjavasample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.gdgand.rxjava.rxjavasample.adapter.MainAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_main)
    RecyclerView rvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        MainAdapter adapter = new MainAdapter(getApplicationContext());
        rvMain.setAdapter(adapter);

        adapter.setOnItemClickListener((adapter1, position) -> {

        });
    }
}
