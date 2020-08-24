package com.clickandearn.clicker1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;


public class AdActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private RewardedVideoAd rewardedAd;

    private TextView clickDisplayTextView;

    private int clicks;
    private int views;
    private String email;

    private String productionAd = "ca-app-pub-9673805035685655/4400516143";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        //Return an array with the size 2 including clicks at index 0 and views at index 1
        int[] result = HelperFunctions.loadUserValues(email, this);
        clicks = result[0];
        views = result[1];

        //Initialize all XML elements
        clickDisplayTextView = findViewById(R.id.clicksCounterDisplay);
        Button videoAdButton = findViewById(R.id.videoAdButton);
        Button backToMenue = findViewById(R.id.backToMenue);
        AdView topAdView = findViewById(R.id.adView);

        //Update number Textfield
        updatedCounterText();

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        topAdView.loadAd(adRequest);

        rewardedAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedAd.setRewardedVideoAdListener(this);

        rewardedAd.loadAd(productionAd, new AdRequest.Builder().build());


        /* Load a rewarded Ad, if the video isn't buffered yet the button will not respond*/
        videoAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rewardedAd.isLoaded()) {
                    rewardedAd.show();
                }
            }
        });


        /*First Save the new amount of clicks and views
          then the activity and change to menue*/
        backToMenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HelperFunctions.updateUser(email, AdActivity.this, clicks, views);

                Intent intent = new Intent(AdActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });
    }

    //Show the current Amounts of clicks
    private void updatedCounterText() {
        clickDisplayTextView.setText(String.valueOf(clicks));
    }

    //Load a rewarded Video Ad
    private void loadRewardedVideoAd() {
        rewardedAd.loadAd(productionAd, new AdRequest.Builder().build());
    }

    //Start Loading a new Ad after closing the previous, to reduce the time until the next Call
    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    /* After finishing the ad, clicks and views will be incremented,
    *  and clicks will be displayed to the User, as well as an update call for the clicks TextView*/
    @Override
    public void onRewarded(RewardItem rewardItem) {
        this.clicks += 1;
        this.views += 1;

        Toast.makeText(this, "" + this.clicks, Toast.LENGTH_SHORT).show();
        updatedCounterText();
    }

    @Override
    public void onRewardedVideoAdLoaded() {
        //fired when ad loaded
    }

    @Override
    public void onRewardedVideoAdOpened() {
        //fired when ad opened
    }

    @Override
    public void onRewardedVideoStarted() {
        //fired when ad started
    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }

    @Override
    public void onRewardedVideoCompleted() {

    }
}
