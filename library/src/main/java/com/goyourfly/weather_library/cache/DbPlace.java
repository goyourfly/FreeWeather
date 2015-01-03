package com.goyourfly.weather_library.cache;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.gson.Gson;
import com.goyourfly.weather_library.ui.obj.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaoyf on 15/1/3.
 */
public class DbPlace {
    private DbHelper mHelper;
    public DbPlace(Context context){
        mHelper = new DbHelper(context);
    }

    /**
     * Insert place
     * @param place
     * @return
     */
    public int insert(Place place) {
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("woeid", place.woeid);
        contentValues.put("name", place.name);
        contentValues.put("country", place.country);
        contentValues.put("timezone", place.timezone);
        contentValues.put("admin1", place.admin1);
        contentValues.put("admin2", place.admin2);
        contentValues.put("admin3", place.admin3);
        contentValues.put("locality1", place.locality1);
        contentValues.put("add_date", place.addDate);

        Log.d("DbPlace", "Insert place:" + new Gson().toJson(place));
        int result = (int) sqLiteDatabase.insert(DbHelper.TABLE_PLACE, null, contentValues);
        sqLiteDatabase.close();
        return result;
    }

    public boolean deleteByWoeid(int id) {
        SQLiteDatabase sqLiteDatabase = mHelper.getWritableDatabase();
        String sql = "delete from " + DbHelper.TABLE_PLACE +
                " where woeid = " + id;
        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.close();
        return true;
    }

    public List<Place> queryAll() {
        SQLiteDatabase sqLiteDatabase = mHelper.getReadableDatabase();
        List<Place> list = new ArrayList<Place>();
        String sql = "select * from " + DbHelper.TABLE_PLACE + " order by add_date";
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("woeid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String timezone = cursor.getString(cursor.getColumnIndex("timezone"));
            String admin1 = cursor.getString(cursor.getColumnIndex("admin1"));
            String admin2 = cursor.getString(cursor.getColumnIndex("admin2"));
            String admin3 = cursor.getString(cursor.getColumnIndex("admin3"));
            String locality1 = cursor.getString(cursor.getColumnIndex("locality1"));
            long date = cursor.getLong(cursor.getColumnIndex("add_date"));

           Place place = new Place();
            place.woeid = id;
            place.name = name;
            place.country = country;
            place.timezone = timezone;
            place.admin1 = admin1;
            place.admin2 = admin2;
            place.admin3 = admin3;
            place.locality1 = locality1;
            place.addDate = date;

            list.add(place);
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }


    public Place queryByWoeid(int id) {
        SQLiteDatabase sqLiteDatabase = mHelper.getReadableDatabase();
        String sql = "select * from " + DbHelper.TABLE_PLACE + " where woeid = " + id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if (cursor.moveToNext()) {
            int woeid = cursor.getInt(cursor.getColumnIndex("woeid"));
            String name = cursor.getString(cursor.getColumnIndex("name"));
            String country = cursor.getString(cursor.getColumnIndex("country"));
            String timezone = cursor.getString(cursor.getColumnIndex("timezone"));
            String admin1 = cursor.getString(cursor.getColumnIndex("admin1"));
            String admin2 = cursor.getString(cursor.getColumnIndex("admin2"));
            String admin3 = cursor.getString(cursor.getColumnIndex("admin3"));
            String locality1 = cursor.getString(cursor.getColumnIndex("locality1"));
            long date = cursor.getLong(cursor.getColumnIndex("add_date"));

            Place place = new Place();
            place.woeid = woeid;
            place.name = name;
            place.country = country;
            place.timezone = timezone;
            place.admin1 = admin1;
            place.admin2 = admin2;
            place.admin3 = admin3;
            place.locality1 = locality1;
            place.addDate = date;

            cursor.close();
            sqLiteDatabase.close();

            return place;
        }
        cursor.close();
        sqLiteDatabase.close();
        return null;
    }
}
