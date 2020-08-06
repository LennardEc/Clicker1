package com.example.clicker1;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PayOutHelper extends AppCompatActivity {

    private final static String email = "clickclickmoney57@gmail.com";
    private final static String payOutRequest = "Payout Request";

    public void sendEmail(Context context, String payMethod, String request, int amount, String additionalInformation) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");

        intent.putExtra(Intent.EXTRA_EMAIL, email);
        intent.putExtra(Intent.EXTRA_SUBJECT, payOutRequest);

        String body = "Payout Request from " + request + "\n"
                       + "via: " + payMethod + "\n" +
                        "amount: " + ""+amount + "\n" +
                        "Additional Information: " + additionalInformation;

        intent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(intent);
            Toast.makeText(context, "Started Activity", Toast.LENGTH_SHORT).show();
        }catch (Exception e) {
            Toast.makeText(context, "Exception: ", Toast.LENGTH_SHORT).show();

        }
    }
}
