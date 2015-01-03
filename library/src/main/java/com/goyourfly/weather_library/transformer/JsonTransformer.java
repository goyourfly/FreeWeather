package com.goyourfly.weather_library.transformer;

import android.util.Log;

import com.google.gson.Gson;
import com.goyourfly.weather_library.net.yahoo_obj.NetGetWoeId;
import com.goyourfly.weather_library.net.yahoo_obj.NetWeather;

import java.io.IOException;

import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class JsonTransformer {
    public static Observable<NetWeather> getNetWeather(final String s) {
        return Async.start(new Func0<NetWeather>() {
            @Override
            public NetWeather call() {
                return new Gson().fromJson(s, NetWeather.class);
            }
        }, Schedulers.computation());
    }

    public static Observable<NetGetWoeId> getWoeId(final String s) {
        return Async.start(new Func0<NetGetWoeId>() {
            @Override
            public NetGetWoeId call() {
                return new Gson().fromJson(s, NetGetWoeId.class);
            }
        }, Schedulers.computation());
    }
}
