package com.example.clicker1;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class PayOutActivity extends AppCompatActivity {
    String paymentMethod;
    String email;
    String payPalLink;
    String additionalInfo = "";

    HashMap<Integer, Button> hash;

    private int clicks, views;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);
        paymentMethod = intent.getStringExtra(menueActivity.PAYMENT);

        if (paymentMethod.equals(menueActivity.PAYPAL)) {
            payPalLink = intent.getStringExtra(menueActivity.PAYPALLINK);
            additionalInfo = payPalLink;
        }

        Button btBack = findViewById(R.id.backToMenuePayout);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });



        //TODO entfernen des Hardcode Values
        int[] result = HelperFunctions.loadUserValues(email, this);
        clicks = result[0];
        views = result[1];

        //SELECT Amount
        Button bt10, bt15, bt20, bt30, bt50;
        bt10 = findViewById(R.id.button10);
        bt15 = findViewById(R.id.button15);
        bt20 = findViewById(R.id.button20);
        bt30 = findViewById(R.id.button30);
        bt50 = findViewById(R.id.button50);

        hash = new HashMap<>();
        hash.put(10000, bt10);
        hash.put(15000, bt15);
        hash.put(20000, bt20);
        hash.put(30000, bt30);
        hash.put(50000, bt50);

        HashMap<Integer, Integer> hashGreen = new HashMap<>();
        hashGreen.put(10000, R.drawable.ic_eur_button_green_10);
        hashGreen.put(15000, R.drawable.ic_eur_button_green_15);
        hashGreen.put(20000, R.drawable.ic_eur_button_green_20);
        hashGreen.put(30000, R.drawable.ic_eur_button_green_30);
        hashGreen.put(50000, R.drawable.ic_eur_button_green_50);

        HashMap<Integer, Integer> hashRed = new HashMap<>();
        hashRed.put(10000, R.drawable.ic_eur_button_red_10);
        hashRed.put(15000, R.drawable.ic_eur_button_red_15);
        hashRed.put(20000, R.drawable.ic_eur_button_red_20);
        hashRed.put(30000, R.drawable.ic_eur_button_red_30);
        hashRed.put(50000, R.drawable.ic_eur_button_red_50);

        for (int key : hash.keySet()) {
            Button bt = hash.get(key);
            if (key <= clicks) {
                bt.setEnabled(true);
                bt.setBackgroundResource(hashGreen.get(key));
            } else {
                bt.setEnabled(false);
                bt.setBackgroundResource(hashRed.get(key));
            }
        }

        bt10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Security Call
                int[] result = HelperFunctions.loadUserValues(email, PayOutActivity.this);
                clicks = result[0];

                if(clicks >= 10000) {
                    JavaEmailAPI jea = new JavaEmailAPI(PayOutActivity.this, email, paymentMethod, 10000, additionalInfo, views);
                    jea.execute();

                    HelperFunctions.updateClicks(email, PayOutActivity.this,clicks - 10000);

                    Toast.makeText(PayOutActivity.this, "Request send!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PayOutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        bt15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Security Call
                int[] result = HelperFunctions.loadUserValues(email, PayOutActivity.this);
                clicks = result[0];

                if(clicks >= 15000) {
                    JavaEmailAPI jea = new JavaEmailAPI(PayOutActivity.this, email, paymentMethod, 15000, additionalInfo, views);
                    jea.execute();

                    HelperFunctions.updateClicks(email, PayOutActivity.this,clicks - 15000);

                    Toast.makeText(PayOutActivity.this, "Request send!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PayOutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        bt20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Security Call
                int[] result = HelperFunctions.loadUserValues(email, PayOutActivity.this);
                clicks = result[0];

                if(clicks >= 20000) {
                    JavaEmailAPI jea = new JavaEmailAPI(PayOutActivity.this, email, paymentMethod, 20000, additionalInfo, views);
                    jea.execute();

                    HelperFunctions.updateClicks(email, PayOutActivity.this,clicks - 20000);

                    Toast.makeText(PayOutActivity.this, "Request send!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PayOutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        bt30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Security Call
                int[] result = HelperFunctions.loadUserValues(email, PayOutActivity.this);
                clicks = result[0];

                if(clicks >= 30000) {
                    JavaEmailAPI jea = new JavaEmailAPI(PayOutActivity.this, email, paymentMethod, 30000, additionalInfo, views);
                    jea.execute();

                    HelperFunctions.updateClicks(email, PayOutActivity.this,clicks - 30000);

                    Toast.makeText(PayOutActivity.this, "Request send!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PayOutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        bt50.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Security Call
                int[] result = HelperFunctions.loadUserValues(email, PayOutActivity.this);
                clicks = result[0];

                if(clicks >= 50000) {
                    JavaEmailAPI jea = new JavaEmailAPI(PayOutActivity.this, email, paymentMethod, 50000, additionalInfo, views);
                    jea.execute();

                    HelperFunctions.updateClicks(email, PayOutActivity.this,clicks - 50000);

                    Toast.makeText(PayOutActivity.this, "Request send!", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PayOutActivity.this, "Error!", Toast.LENGTH_SHORT).show();
                }

                Intent intent = new Intent(PayOutActivity.this, menueActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });
    }

}