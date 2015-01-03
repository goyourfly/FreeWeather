package com.goyourfly.weather_library.cache;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gaoyf on 14-10-1.
 */
public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "weather";
    public static final String TABLE_PLACE = "place";

    public DbHelper(Context context) {
        this(context, DB_NAME, null, 1);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table " + TABLE_PLACE + " (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "woeid int unique," +
                "name String," +
                "country String," +
                "timezone String," +
                "admin1 String," +
                "admin2 String," +
                "admin3 String," +
                "locality1 String," +
                "add_date long)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
