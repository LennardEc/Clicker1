package com.clickandearn.clicker1;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgbActivity extends AppCompatActivity {
    private String email;
    public static int agb_version;

    private CheckBox agb_check;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agb);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        agb_version = MainActivity.AGB_VERSION;

        TextView agb_text = findViewById(R.id.agb_textView);
        agb_text.setMovementMethod(new ScrollingMovementMethod());
        agb_text.setText(MainActivity.agb);

        agb_check = findViewById(R.id.agb_checkbox);

        Button decline = findViewById(R.id.decline);
        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button proceed = findViewById(R.id.porceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUser = HelperFunctions.userExists(email, AgbActivity.this);

                if(agb_check.isChecked() && !isUser) {
                    HelperFunctions.createUser(email, AgbActivity.this, agb_version);

                    Intent intent = new Intent(AgbActivity.this, AdActivity.class);
                    intent.putExtra(MainActivity.EMAIL, email);
                    startActivity(intent);
                }else if(agb_check.isChecked() && isUser) {
                    HelperFunctions.setAGBStatus(email, AgbActivity.this, agb_version);

                    Intent intent = new Intent(AgbActivity.this, AdActivity.class);
                    intent.putExtra(MainActivity.EMAIL, email);
                    startActivity(intent);
                } else {
                    Toast.makeText(AgbActivity.this, "You need to accept the Terms of service!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
