package com.goyourfly.weather_library.cache;

import android.content.Context;

import com.goyourfly.weather_library.ui.obj.Place;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by gaoyf on 14/12/30.
 */
public class PlaceCache {
    private DbPlace mDbPlace;

    public PlaceCache(Context context){
        mDbPlace = new DbPlace(context);
    }

    public void addPlace(Place place){
        mDbPlace.insert(place);
    }

    public void removePlace(int woeid){
        mDbPlace.deleteByWoeid(woeid);
    }

    public Place getPlace(int woeid){
        return mDbPlace.queryByWoeid(woeid);
    }


    public List<Place> getAll(){
        return mDbPlace.queryAll();
    }

}
