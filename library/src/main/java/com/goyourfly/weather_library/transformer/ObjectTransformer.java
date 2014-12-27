package com.goyourfly.weather_library.transformer;

import com.goyourfly.weather_library.net.obj.NetCurrentWeather;
import com.goyourfly.weather_library.ui.obj.CurrentWeather;

import rx.Observable;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class ObjectTransformer {
    public static Observable<CurrentWeather> getCurrentWeather(final NetCurrentWeather netCurrentWeather) {

        return Async.start(new Func0<CurrentWeather>() {
            @Override
            public CurrentWeather call() {
                CurrentWeather currentWeather = new CurrentWeather();
                if (netCurrentWeather.weather.length > 0) {
                    currentWeather.weather = LanguageTransformer.weatherTransformer(netCurrentWeather.weather[0].main);
                }
                currentWeather.temp = netCurrentWeather.main.temp;
                currentWeather.wind = netCurrentWeather.wind.deg;
                currentWeather.cloud = netCurrentWeather.clouds.all;

                return currentWeather;
            }
        });
    }
}
