package com.example.clicker1;

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

    private TextView agb_text;
    private CheckBox agb_check;
    private Button proceed;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agb);

        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        agb_text = findViewById(R.id.agb_textView);
        agb_text.setMovementMethod(new ScrollingMovementMethod());

        agb_check = findViewById(R.id.agb_checkbox);

        proceed = findViewById(R.id.porceed);

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(agb_check.isChecked()) {
                    HelperFunctions.createUser(email, AgbActivity.this);


                    Intent intent = new Intent(AgbActivity.this, AdActivity.class);
                    intent.putExtra(MainActivity.EMAIL, email);
                    startActivity(intent);
                }else {
                    Toast.makeText(AgbActivity.this, "You need to accept the Terms of service!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
