package com.example.clicker1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class PayOutActivity extends AppCompatActivity {
    String paymentMethod;
    String email;
    String payPalLink;

    private int clicks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payout);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);
        paymentMethod = intent.getStringExtra(menueActivity.PAYMENT);

        if(paymentMethod.equals(menueActivity.PAYPAL)) {
            payPalLink = intent.getStringExtra(menueActivity.PAYPALLINK);
        }

        int[] result = HelperFunctions.loadUserValues(email, this);
        clicks = result[0];

        //SELECT Amount
        Button bt10, bt15, bt20, bt30, bt50;
        bt10 = findViewById(R.id.button10);
        bt15 = findViewById(R.id.button15);
        bt20 = findViewById(R.id.button20);
        bt30 = findViewById(R.id.button30);
        bt50 = findViewById(R.id.button50);

    }
}
