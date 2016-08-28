package com.udacity.gradle.builditbigger.ui;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.google.android.gms.ads.AdRequest;
//import com.google.android.gms.ads.AdView;

import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.builditbigger.utils.AsyncJokesProvider;
import com.udacity.gradle.builditbigger.utils.JokeListener;

import prithvi.jokesdisplay.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeListener{

    private Button buttonJoke;
    private ProgressBar progressBar;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(com.udacity.gradle.builditbigger.R.layout.fragment_main, container, false);
        buttonJoke = (Button) view.findViewById(R.id.bJoke);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        buttonJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AsyncJokesProvider(MainActivityFragment.this).getJoke();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    @Override
    public void jokeDownloaded(String joke) {
        Intent intent = new Intent(getActivity(), DisplayJokesActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
