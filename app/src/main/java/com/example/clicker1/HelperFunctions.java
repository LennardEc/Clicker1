package com.example.clicker1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HelperFunctions {
    public static boolean userExists(String email, Context context) {
        ViewCountHelper dbHelper = new ViewCountHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String checkUserName = "Select * from " + ViewCountContract.ViewCount.TABLE_NAME + " where email = '" + email + "'";
        Cursor res = db.rawQuery(checkUserName, null);

        if(res.getCount() == 1) {
            return true;
        }

        return false;
    }

    public static void createUser(String email, Context context) {
        ViewCountHelper dbHelper = new ViewCountHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String newUser = "Insert into " + ViewCountContract.ViewCount.TABLE_NAME + " values('" + email + "', 0, 0)";
        db.execSQL(newUser);
    }

    public static void updateUser(String email, Context context, int clicks, int views) {
        ViewCountHelper dbHelper = new ViewCountHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String update = "Update " + ViewCountContract.ViewCount.TABLE_NAME + " set " + ViewCountContract.ViewCount.COLUMN_NAME_CLICKS + " = " + clicks +
                " , " + ViewCountContract.ViewCount.COLUMN_NAME_VIEWS + " = " + views;
        db.execSQL(update);
    }
}
