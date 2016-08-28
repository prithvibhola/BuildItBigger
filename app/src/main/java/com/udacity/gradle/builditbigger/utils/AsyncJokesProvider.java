package com.udacity.gradle.builditbigger.utils;

import android.os.AsyncTask;

import com.example.prithvi.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import static com.udacity.gradle.builditbigger.utils.EndPoints.*;

import java.io.IOException;

/**
 * Created by Prithvi on 8/5/2016.
 */
public class AsyncJokesProvider {

    private static MyApi myApiService = null;
    private JokeListener jokeListener;

    public AsyncJokesProvider(JokeListener jokeListener) {
        this.jokeListener = jokeListener;
    }

    public void getJoke(){
        new EndpointsAsyncTask().execute();
    }

    class EndpointsAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            if (myApiService == null) {
                MyApi.Builder builder = new MyApi.Builder(  AndroidHttp.newCompatibleTransport(),
                                                            new AndroidJsonFactory(),
                                                            null)
                        .setRootUrl(ROOT_URL);
                myApiService = builder.build();
            }

            try {
                return myApiService.getJoke().execute().getData();
            } catch (IOException e) {
                return e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String joke) {
            super.onPostExecute(joke);
            jokeListener.jokeDownloaded(joke);
        }
    }
}
