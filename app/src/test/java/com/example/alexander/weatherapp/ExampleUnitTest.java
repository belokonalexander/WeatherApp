package com.example.alexander.weatherapp;

import org.junit.Test;

import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */




public class ExampleUnitTest {



    @Test
    public void rxflag_isCorrect() throws Exception {
        final boolean[] flag = {false};

        TestObserver<Integer> observer = TestObserver.create();

        Observable.fromCallable(() -> {
            flag[0] = true;
            return 100;
        }).subscribe(observer);

        observer.assertValue(100);
        assertTrue(flag[0]);
    }

    /**
     * как проверять, выполняется ли сейчас Observer?
     */
    @Test
    public void observerShouldBeDisposed()  {
        TestObserver<String> observer = new TestObserver<>();
        Observable.just("").subscribe(observer);
        observer.awaitTerminalEvent();

        observer.assertNoErrors();
        observer.assertComplete();
        assertTrue(observer.isDisposed());
    }





}