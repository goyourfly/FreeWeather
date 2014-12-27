package com.goyourfly.weather_library;

import android.util.Log;

import com.goyourfly.weather_library.transformer.JsonTransformer;
import com.goyourfly.weather_library.net.Net;
import com.goyourfly.weather_library.net.obj.NetCurrentWeather;
import com.goyourfly.weather_library.transformer.ObjectTransformer;
import com.goyourfly.weather_library.ui.obj.CurrentWeather;
import com.goyourfly.weather_library.utils.Errors;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by gaoyf on 14/12/27.
 */
public class WeatherModule {
    public interface OnGetListener<T> {
        public void onGet(T t);

        public void onError(String err);

        public void onFinally();
    }

    public void getCurrentWeather(final OnGetListener<CurrentWeather> currentWeatherOnGetListener) {
        Net net = new Net();
        net.getData(Urls.getWeatherUrl("BeiJing")).observeOn(AndroidSchedulers.mainThread())
                .flatMap(new Func1<String, Observable<NetCurrentWeather>>() {
                    @Override
                    public Observable<NetCurrentWeather> call(String s) {
                        return JsonTransformer.getCurrentWeather(s);
                    }
                })
                .flatMap(new Func1<NetCurrentWeather, Observable<CurrentWeather>>() {
                    @Override
                    public Observable<CurrentWeather> call(NetCurrentWeather netCurrentWeather) {
                        return ObjectTransformer.getCurrentWeather(netCurrentWeather);
                    }
                })
                .subscribe(new Action1<CurrentWeather>() {
                    @Override
                    public void call(CurrentWeather s) {
                        logD(s.toString());
                        currentWeatherOnGetListener.onGet(s);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        logE(throwable.getMessage());
                        currentWeatherOnGetListener.onError(Errors.getError(throwable.getMessage()));
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        currentWeatherOnGetListener.onFinally();
                    }
                });
    }

    private void logD(String log) {
        Log.d("WeatherModule", log);
    }

    private void logE(String log) {
        Log.e("WeatherModule", log);
    }

}
