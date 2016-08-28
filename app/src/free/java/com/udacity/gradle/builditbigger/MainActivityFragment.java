package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
import com.udacity.gradle.builditbigger.utils.AsyncJokesProvider;
import com.udacity.gradle.builditbigger.utils.JokeListener;

import prithvi.jokesdisplay.*;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements JokeListener{

    private Button buttonJoke;
    private ProgressBar progressBar;
    private AdView mAdView;
    public PublisherInterstitialAd mPublisherInterstitialAd;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_main, container, false);
        buttonJoke = (Button) view.findViewById(R.id.bJoke);
        progressBar = (ProgressBar) view.findViewById(R.id.progressBar);
        mAdView = (AdView) view.findViewById(R.id.adView);

        progressBar.setVisibility(View.GONE);

        mPublisherInterstitialAd = new PublisherInterstitialAd(getActivity());
        mPublisherInterstitialAd.setAdUnitId(getActivity().getResources().getString(R.string.interstitial_ad_unit_id));

        mPublisherInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed(){
                requestInterstitialAd();
                new AsyncJokesProvider(MainActivityFragment.this).getJoke();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        requestBannerAd();
        requestInterstitialAd();

        buttonJoke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mPublisherInterstitialAd.isLoaded()){
                    mPublisherInterstitialAd.show();
                }else{
                    new AsyncJokesProvider(MainActivityFragment.this).getJoke();
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }

    private void requestBannerAd(){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);
    }

    private void requestInterstitialAd(){
        PublisherAdRequest adRequest = new PublisherAdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mPublisherInterstitialAd.loadAd(adRequest);
    }

    @Override
    public void jokeDownloaded(String joke) {
        progressBar.setVisibility(View.GONE);
        Intent intent = new Intent(getActivity(), DisplayJokesActivity.class);
        intent.putExtra("joke", joke);
        startActivity(intent);
    }
}
