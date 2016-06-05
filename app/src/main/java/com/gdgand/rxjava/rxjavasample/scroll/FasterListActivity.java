package com.gdgand.rxjava.rxjavasample.scroll;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.gdgand.rxjava.rxjavasample.R;
import com.gdgand.rxjava.rxjavasample.scroll.adapter.FasterAdapter;
import com.gdgand.rxjava.rxjavasample.scroll.adapter.NormalAdapter;
import com.gdgand.rxjava.rxjavasample.scroll.realm.FasterListData;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;

public class FasterListActivity extends AppCompatActivity {

    @BindView(R.id.list_fasterlist)
    RecyclerView lvFasterList;

    private FasterAdapter fasterAdapter;
    private NormalAdapter normalAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fasterlist);

        setUpActionbar();

        ButterKnife.bind(this);

        fasterAdapter = new FasterAdapter(FasterListActivity.this);
        normalAdapter = new NormalAdapter(FasterListActivity.this);

        ProgressDialog dialog = ProgressDialog.show(FasterListActivity.this, null, "로딩 중...", true);

        Realm.setDefaultConfiguration(new RealmConfiguration.Builder(FasterListActivity.this)
                .build());
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.deleteAll();
        realm.commitTransaction();
        realm.executeTransactionAsync(realm1 -> {
            Observable.range(1, 13000)
                    .subscribe(index -> {
                        FasterListData object = realm1.createObject(FasterListData.class);
                        object.setPosition(index);

                        normalAdapter.add("Index : " + index);
                        fasterAdapter.add("Index : " + index);
                    }, Throwable::printStackTrace);
        }, () -> {
            lvFasterList.setLayoutManager(new LinearLayoutManager(FasterListActivity.this));
            lvFasterList.setAdapter(normalAdapter);
            dialog.dismiss();
        });
    }

    private void setUpActionbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.tb_fastlist);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        getMenuInflater().inflate(R.menu.fasterlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_fasterlist_non_observable:
                lvFasterList.setAdapter(normalAdapter);
                normalAdapter.notifyDataSetChanged();
                break;
            case R.id.menu_fasterlist_with_observable:
                lvFasterList.setAdapter(fasterAdapter);
                fasterAdapter.notifyDataSetChanged();
                break;
        }

        return true;
    }
}
