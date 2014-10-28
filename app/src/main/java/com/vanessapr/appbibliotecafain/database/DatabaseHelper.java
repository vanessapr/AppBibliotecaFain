package com.vanessapr.appbibliotecafain.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by Milagros on 27/10/2014.
 */
public class DatabaseHelper extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "biblioteca.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Cursor executeQuery(String sql){
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery(sql, null);
        c.moveToFirst();
        return c;
    }
}
