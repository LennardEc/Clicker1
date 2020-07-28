package com.example.clicker1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SignOutActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signout_popup);

        Button yes = findViewById(R.id.yesButton);
        Button no = findViewById(R.id.noButton);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignOutActivity.this, MainActivity.class);
                intent.putExtra("Flag", true);
                startActivity(intent);
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignOutActivity.this, MainActivity.class);
                intent.putExtra("Flag", false);
                startActivity(intent);
            }
        });
    }
}
