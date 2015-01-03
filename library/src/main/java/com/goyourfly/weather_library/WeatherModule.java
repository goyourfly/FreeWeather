package com.goyourfly.weather_library;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.goyourfly.weather_library.cache.PlaceCache;
import com.goyourfly.weather_library.cache.WeatherCache;
import com.goyourfly.weather_library.net.yahoo_obj.NetGetWoeId;
import com.goyourfly.weather_library.net.yahoo_obj.NetWeather;
import com.goyourfly.weather_library.transformer.JsonTransformer;
import com.goyourfly.weather_library.net.Net;
import com.goyourfly.weather_library.transformer.ObjectTransformer;
import com.goyourfly.weather_library.ui.obj.Weather;
import com.goyourfly.weather_library.ui.obj.Place;
import com.goyourfly.weather_library.utils.Errors;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.ActionN;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class WeatherModule {
    private static WeatherModule mModule;
    private PlaceCache mPlaceCache;
    private WeatherCache mWeatherCache;
    private Context mContext;

    private WeatherModule() {

    }

    public void init(Context context) {
        mContext = context;
        mPlaceCache = new PlaceCache(context);
        mWeatherCache = new WeatherCache();
    }

    public interface OnGetListener<T> {
        public void onGet(T t);

        public void onError(String err);

        public void onFinally();
    }

    public static WeatherModule getInstance() {
        if (mModule == null)
            mModule = new WeatherModule();
        return mModule;
    }

    public void getWeather(final OnGetListener<Weather> listener, final int woeid) {
        Net net = new Net();
        Observable.concat(mWeatherCache.getWeather(woeid), net.getData(Urls.getWeatherUrl(woeid))
                        .flatMap(new Func1<String, Observable<NetWeather>>() {
                            @Override
                            public Observable<NetWeather> call(String s) {
                                logD("OnGetWeather:" + s);
                                return JsonTransformer.getNetWeather(s);
                            }
                        })
                        .flatMap(new Func1<NetWeather, Observable<Weather>>() {
                            @Override
                            public Observable<Weather> call(NetWeather netWeather) {
                                logD("NetWeather:" + new Gson().toJson(netWeather));
                                return ObjectTransformer.getWeather(woeid,netWeather);
                            }
                        })
                        .map(new Func1<Weather, Weather>() {
                            @Override
                            public Weather call(Weather weather) {
                                logD("Map:add to cache");
                                mWeatherCache.addWeather(weather);
                                return weather;
                            }
                        })
        )
                .subscribe(new Action1<Weather>() {
                    @Override
                    public void call(Weather s) {
//                        logD(s.toString());
                        listener.onGet(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                        logE(throwable.getMessage());
                        listener.onError(Errors.getError(throwable.getMessage()));
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        listener.onFinally();
                    }
                });
    }

    public void getWoeId(final OnGetListener<List<Place>> listener, String name) {
        Net net = new Net();
        net.getData(Urls.getWoeidUrl(name)).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<NetGetWoeId>>() {
                    @Override
                    public Observable<NetGetWoeId> call(String s) {
                        logD("GetWoeId:" + s);
                        return JsonTransformer.getWoeId(s);
                    }
                })
                .flatMap(new Func1<NetGetWoeId, Observable<List<Place>>>() {
                    @Override
                    public Observable<List<Place>> call(NetGetWoeId netGetWoeId) {
                        if (netGetWoeId == null || netGetWoeId.places == null || netGetWoeId.places.place.length < 1)
                            return null;
                        return ObjectTransformer.getPlaces(netGetWoeId.places.place);
                    }
                })
                .timeout(4000, TimeUnit.MILLISECONDS)
                .subscribe(new Action1<List<Place>>() {
                    @Override
                    public void call(List<Place> s) {
                        listener.onGet(s);
                        logD(s.toString());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        listener.onError(Errors.getError(throwable.getMessage()));
                        logE(throwable.getMessage());
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        logD("finally");
                        listener.onFinally();
                    }
                });

    }

    public void addPlace(Place place) {
        place.addDate = System.currentTimeMillis();
        mPlaceCache.addPlace(place);
    }

    public Place getPlace(int woeid) {
        return mPlaceCache.getPlace(woeid);
    }

    public List<Place> getAllPlace() {
        return mPlaceCache.getAll();
    }


    private void logD(String log) {
        Log.d("WeatherModule", log);
    }

    private void logE(String log) {
        Log.e("WeatherModule", log == null ? "unknown" : log);
    }

}
