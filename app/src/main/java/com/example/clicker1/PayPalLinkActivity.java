package com.example.clicker1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayPalLinkActivity extends AppCompatActivity {

    private String email;
    private String payment;
    private String paypalLinkValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paypal_popop);

        final TextView paypalLink = findViewById(R.id.paypallink);
        Button continue_ = findViewById(R.id.continueButton);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);
        payment = intent.getStringExtra(menueActivity.PAYMENT);

        continue_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paypalLinkValue = paypalLink.getText().toString();

                if (!paypalLinkValue.equals("")) {
                    Intent intent = new Intent(PayPalLinkActivity.this, PayOutActivity.class);
                    intent.putExtra(MainActivity.EMAIL, email);
                    intent.putExtra(menueActivity.PAYMENT, payment);
                    intent.putExtra(menueActivity.PAYPALLINK, paypalLinkValue);
                    startActivity(intent);
                } else {
                    Toast.makeText(PayPalLinkActivity.this, "Enter Valid Paypal-Link", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
