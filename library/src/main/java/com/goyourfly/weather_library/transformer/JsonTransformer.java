package com.goyourfly.weather_library.transformer;

import com.google.gson.Gson;
import com.goyourfly.weather_library.net.obj.NetCurrentWeather;

import rx.Observable;
import rx.functions.Func0;
import rx.schedulers.Schedulers;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class JsonTransformer {
    public static Observable<NetCurrentWeather> getCurrentWeather(final String s) {
        return Async.start(new Func0<NetCurrentWeather>() {
            @Override
            public NetCurrentWeather call() {
                return new Gson().fromJson(s, NetCurrentWeather.class);
            }
        }, Schedulers.computation());
    }
}
