package com.example.clicker1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.clicker1.ViewCountContract.*;


//TODO Return Kontostand after leaving the Tab

public class AdActivity extends AppCompatActivity implements RewardedVideoAdListener {
    private ViewCountHelper dbHelper;
    private AdView topAdView;
    private RewardedVideoAd rewardedAd;

    private Button button, backToMenue;
    private TextView number;

    private int clicks;
    private int views;
    private String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        //Create Database Connection
        dbHelper = new ViewCountHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String checkUserName = "Select * from " + ViewCountContract.ViewCount.TABLE_NAME + " where email = '" + email + "'";
        Cursor res = db.rawQuery(checkUserName, null);

        int cClicks = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_CLICKS);
        int cViews = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_VIEWS);

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            clicks = res.getInt(cClicks);
            views = res.getInt(cViews);
        }


        number = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        backToMenue = findViewById(R.id.backToMenue);
        topAdView = findViewById(R.id.adView);

        String text = "Kontostand: " + clicks;
        number.setText(text);


        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        AdRequest adRequest = new AdRequest.Builder().build();
        topAdView.loadAd(adRequest);

        rewardedAd = MobileAds.getRewardedVideoAdInstance(this);
        rewardedAd.setRewardedVideoAdListener(this);

        rewardedAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(rewardedAd.isLoaded()) {
                    rewardedAd.show();
                }
            }
        });

        backToMenue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                String update = "Update " + ViewCount.TABLE_NAME + " set " + ViewCount.COLUMN_NAME_CLICKS + " = " + clicks +
                        " , " + ViewCount.COLUMN_NAME_VIEWS + " = " + views;
                db.execSQL(update);

                menueActivity.konto.setText(String.valueOf(clicks));
                finish();
            }
        });
    }

    private void updatedCounterText() {
        String text = "Kontostand: " + clicks;
        number.setText(text);
    }

    private void loadRewardedVideoAd() {
        rewardedAd.loadAd("ca-app-pub-3940256099942544/5224354917", new AdRequest.Builder().build());
    }


    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {
        this.clicks += 1;
        this.views += 1;

        //TODO Optionaler Printout
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
