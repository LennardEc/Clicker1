package com.example.clicker1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class menueActivity extends AppCompatActivity {
    private int clicks;
    private int views;

    public static final String PAYMENT = "PAYMENT";
    public static final String PAYPALLINK = "PAYPALLINK";
    public static final String PAYPAL = "PAYPAL";
    public static final String AMAZON = "AMAZON";
    public static final String GOOGLE = "GOOGLE";
    public static final String FORTNITE = "FORTNTIE";

    private Button button;
    private Button signOut;
    public static TextView konto;

    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        //Return an array with the size 2 including clicks at index 0 and views at index 1
        int[] result = HelperFunctions.loadUserValues(email, this);
        clicks = result[0];
        views = result[1];


        button = findViewById(R.id.redirectToAd);
        signOut = findViewById(R.id.signOutButton);
        konto = findViewById(R.id.kontostand);

        konto.setText(String.valueOf(clicks));


        /* Return back to AdActivity */
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, AdActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        /* Return to main Menu and trigger the onResume function to conduced the SignOut() process */
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, SignOutActivity.class);
                startActivity(intent);
            }
        });


        /* Payment Methods */
        Button amazon = findViewById(R.id.amazon);
        Button googlePlay = findViewById(R.id.googlePlay);
        Button paypal = findViewById(R.id.paypal);

        //TODO get Paypal link and Fortnite code

        amazon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, PayOutActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                intent.putExtra(PAYMENT, AMAZON);
                startActivity(intent);
            }
        });

        googlePlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, PayOutActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                intent.putExtra(PAYMENT, GOOGLE);
                startActivity(intent);
            }
        });

        paypal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, PayPalLinkActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                intent.putExtra(PAYMENT, PAYPAL);
                startActivity(intent);
            }
        });


    }
}
