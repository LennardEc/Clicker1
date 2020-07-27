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
                " , " + ViewCountContract.ViewCount.COLUMN_NAME_VIEWS + " = " + views + " where email = '" + email + "'";
        db.execSQL(update);
    }

    public static int[] loadUserValues(String email, Context context) {
        int[] result = new int[2];
        ViewCountHelper dbHelper = new ViewCountHelper(context);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String checkUserName = "Select * from " + ViewCountContract.ViewCount.TABLE_NAME + " where email = '" + email + "'";
        Cursor res = db.rawQuery(checkUserName, null);

        int cClicks = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_CLICKS);
        int cViews = res.getColumnIndex(ViewCountContract.ViewCount.COLUMN_NAME_VIEWS);

        for(res.moveToFirst(); !res.isAfterLast(); res.moveToNext()) {
            result[0] = res.getInt(cClicks);
            result[1] = res.getInt(cViews);
        }

        return result;
    }
}
