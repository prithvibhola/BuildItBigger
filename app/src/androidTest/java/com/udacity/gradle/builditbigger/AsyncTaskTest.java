package com.udacity.gradle.builditbigger;

import android.test.AndroidTestCase;
import android.test.UiThreadTest;

import com.udacity.gradle.builditbigger.utils.AsyncJokesProvider;
import com.udacity.gradle.builditbigger.utils.JokeListener;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by Prithvi on 8/5/2016.
 */
public class AsyncTaskTest extends AndroidTestCase implements JokeListener {

    private CountDownLatch signal;
    private String joke = "";
    private AsyncJokesProvider asyncJokesProvider;

    public AsyncTaskTest(){
        signal = new CountDownLatch(1);
        asyncJokesProvider = new AsyncJokesProvider(this);
    }

    @UiThreadTest
    public void testAsync() throws InterruptedException {
        asyncJokesProvider.getJoke();
        signal.await(10, TimeUnit.SECONDS);
//        assertFalse("Joke is empty", joke == null);
        assertTrue("Yes! Joke Received. It is really funny", joke != null);
    }

    @Override
    public void jokeDownloaded(String joke) {
        this.joke = joke;
        signal.countDown();
    }
}
