package kr.socar.rxjavastudy;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.junit.Test;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.subjects.AsyncSubject;
import rx.subjects.BehaviorSubject;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testAsyncSubjectBasic() {
        AsyncSubject<String> asyncSubject = AsyncSubject.create();
        asyncSubject.subscribe(new Observer<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                System.out.println("AsyncSubject => " + s);
            }
        });
        asyncSubject.onNext("one");
        asyncSubject.onNext("two");
        asyncSubject.onNext("three");
        asyncSubject.onCompleted();
    }

    @Test
    public void testAsyncSubjectRange() {
        AsyncSubject<Integer> aaa = AsyncSubject.create();
        aaa.subscribe(new Subscriber<Integer>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Integer aLong) {
                System.out.println("AsyncSubject => " + aLong);
            }
        });


        Observable.range(1, 100)
                //.observeOn(Schedulers.io())
                .subscribe(aaa);
    }
}
