package com.clickandearn.clicker1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.clickandearn.clicker1.ViewCountContract.*;

public class ViewCountHelper extends SQLiteOpenHelper {
    public ViewCountHelper(@Nullable Context context) {
        super(context, ViewCount.DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + ViewCount.TABLE_NAME + " (" + ViewCount.COLUMN_NAME_EMAIL
                + " TEXT PRIMARY KEY, " + ViewCount.COLUMN_NAME_CLICKS + " INTEGER, "
                + ViewCount.COLUMN_NAME_VIEWS + " INTEGER, "
                + ViewCount.COLUMN_NAME_AGB_VERSION + " INTEGER)";

        db.execSQL(create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + ViewCount.TABLE_NAME);
        onCreate(db);
    }
}
