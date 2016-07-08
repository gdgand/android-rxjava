package kr.socar.rxjavastudy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import rx.Observable;
import rx.Observer;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subjects.ReplaySubject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAsyncSubject          = (Button) findViewById(R.id.btn_async_subject);
        Button btnBehaviorSubject       = (Button) findViewById(R.id.btn_behavior_subject);
        Button btnPublishSubject        = (Button) findViewById(R.id.btn_publish_subject);
        Button btnReplaySubject         = (Button) findViewById(R.id.btn_replay_subject);
        Button btnObservableScriber     = (Button) findViewById(R.id.btn_observable_scriber);


        btnAsyncSubject.setOnClickListener(this);
        btnBehaviorSubject.setOnClickListener(this);
        btnPublishSubject.setOnClickListener(this);
        btnReplaySubject.setOnClickListener(this);
        btnObservableScriber.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_async_subject:
                testAsyncSubject();
                break;
            case R.id.btn_publish_subject:
                testPublishSubject();
                break;
            case R.id.btn_behavior_subject:
                testBehavoirSubject();
                break;
            case R.id.btn_replay_subject:
                testReplaySubject();
                break;
            case R.id.btn_observable_scriber:
                testObservableSubscriber();
                break;
            default:
                break;
        }

    }

    private void testPublishSubject() {
        Button publishSubject = (Button)findViewById(R.id.btn_publish_subject);
        publishSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PublishSubject<String> publishSubject = PublishSubject.create();
                publishSubject.subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("PublishSubject", "1 => " + s);
                    }
                });
                publishSubject.onNext("one");
                publishSubject.subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("PushishSubject", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        Log.e("PublishSubject", "2 => " + s);
                    }
                });
                publishSubject.onNext("two");

                publishSubject.onNext("three");
            }
        });
    }

    private void testAsyncSubject() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.e("AsyncSubject", "onCompleted");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("AsyncSubject", " => " + s);
            }
        });
        asyncSubject.onNext("one");
        asyncSubject.onNext("two");
        asyncSubject.onNext("three");
        asyncSubject.onCompleted();
    }

    private void testBehavoirSubject() {
        BehaviorSubject<String> behaviorSubject = BehaviorSubject.create("default");
        behaviorSubject.onNext("one");
        behaviorSubject.onNext("two");
        behaviorSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("BehaviorSubject", " => " + s);
            }
        });

        behaviorSubject.onNext("three");
    }

    private void testReplaySubject() {
        ReplaySubject<String> replaySubject = ReplaySubject.create();
        replaySubject.onNext("one");
        replaySubject.onNext("two");
        replaySubject.onNext("three");
        replaySubject.onNext("four");

        replaySubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("ReplaySubject", " => " + s);
            }
        });
    }

    private void testObservableSubscriber(){


        Observable<String> observable = Observable.just("One");


        PublishSubject<String> publishSubject = PublishSubject.create();

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("ObservableSubscriber", "onNext: subscribe1" + s);
            }
        });

        publishSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("ObservableSubscriber", "onNext: subscribe2" + s);
            }
        });

        observable.subscribe(publishSubject);

    }
}
