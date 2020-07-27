package com.example.clicker1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class menueActivity extends AppCompatActivity {
    ViewCountHelper dbHelper;

    private int clicks;
    private int views;


    private Button button;
    private Button signOut;
    public static TextView konto;

    public static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menue);

        //Get the Email from the Login Process
        //The Email is also the Primary Key
        Intent intent = getIntent();
        email = intent.getStringExtra(MainActivity.EMAIL);

        //Create a DBHelper to create a connection to the local Database
        dbHelper = new ViewCountHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //If the Query is null or has no entrys than
        String checkUserName = "Select * from " + ViewCountContract.ViewCount.TABLE_NAME + " where email = '" + email + "'";
        Cursor res = db.rawQuery(checkUserName, null);

        int cEmail = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_EMAIL);
        int cClicks = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_CLICKS);
        int cViews = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_VIEWS);

        //The getCount() is either 0 or 1, because the email is unique
        if(res.getCount() == 1) {
            // User already Exists -> Fetch Data
            for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
                clicks = res.getInt(cClicks);
                views = res.getInt(cViews);
            }
            Toast.makeText(this, "Clicks: " + clicks + "   " + "Views: " + views , Toast.LENGTH_SHORT).show();
        }else {
            //If the cursor size is Zero, no user exist
            String newUser = "Insert into " + ViewCountContract.ViewCount.TABLE_NAME + "values('" + email + "', 0, 0)";
            db.execSQL(newUser);
            clicks = 0;
            views = 0;
        }


        button = findViewById(R.id.redirectToAd);
        signOut = findViewById(R.id.signOutButton);
        konto = findViewById(R.id.kontostand);

        konto.setText(String.valueOf(clicks));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, AdActivity.class);
                intent.putExtra(MainActivity.EMAIL, email);
                startActivity(intent);
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menueActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
