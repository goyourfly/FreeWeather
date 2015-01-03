package com.goyourfly.weather_library.transformer;

import android.util.Log;

import com.goyourfly.weather_library.net.yahoo_obj.NetForecast;
import com.goyourfly.weather_library.net.yahoo_obj.NetPlace;
import com.goyourfly.weather_library.net.yahoo_obj.NetWeather;
import com.goyourfly.weather_library.ui.obj.Forecast;
import com.goyourfly.weather_library.ui.obj.Weather;
import com.goyourfly.weather_library.ui.obj.Place;
import com.goyourfly.weather_library.utils.WeatherUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import rx.Observable;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class ObjectTransformer {
    public static Observable<Weather> getWeather(final int woeid, final NetWeather netWeather) {

        return Async.start(new Func0<Weather>() {
            @Override
            public Weather call() {
                Weather weather = new Weather();
                weather.woeid = woeid;
                weather.date = netWeather.query.results.channel.item.pubDate;
                weather.code = Integer.parseInt(netWeather.query.results.channel.item.condition.code);
                weather.temp = WeatherUtils.getCTemp(Integer.parseInt(netWeather.query.results.channel.item.condition.temp));
                weather.weatherName = netWeather.query.results.channel.item.condition.text;
                weather.humidity = Integer.parseInt(netWeather.query.results.channel.atmosphere.humidity);
                weather.sunrise = netWeather.query.results.channel.astronomy.sunrise;
                weather.sunset = netWeather.query.results.channel.astronomy.sunset;
                weather.windSpeed = Integer.parseInt(netWeather.query.results.channel.wind.speed);
                weather.windDirection = Integer.parseInt(netWeather.query.results.channel.wind.direction);

                NetForecast[] netForecasts = netWeather.query.results.channel.item.forecast;
                if (netForecasts != null) {
                    List<Forecast> forecasts = new ArrayList();
                    for (NetForecast netForecast : netForecasts) {
                        Forecast forecast = new Forecast();
                        forecast.code = Integer.parseInt(netForecast.code);
                        forecast.date = netForecast.date;
                        forecast.day = netForecast.day;
                        forecast.high = WeatherUtils.getCTemp(Integer.parseInt(netForecast.high));
                        forecast.low = WeatherUtils.getCTemp(Integer.parseInt(netForecast.low));
                        forecast.text = netForecast.text;
                        forecasts.add(forecast);
                    }
                    weather.forecasts = forecasts;
                }


//                SimpleDateFormat format = new SimpleDateFormat("E, d MMM yyyy h:mm a 'CST'",Locale.US);

                return weather;
            }
        });
    }

    public static Observable<List<Place>> getPlaces(final NetPlace[] netPlaces) {
        return Async.start(new Func0<List<Place>>() {
            @Override
            public List<Place> call() {

                List<Place> places = new ArrayList<Place>();
                for (NetPlace netPlace : netPlaces) {
                    Place place = new Place();
                    place.woeid = netPlace.woeid;
                    place.name = netPlace.name;
                    place.country = netPlace.country;
                    place.timezone = netPlace.timezone;
                    place.admin1 = netPlace.admin1;
                    place.admin2 = netPlace.admin2;
                    place.admin3 = netPlace.admin3;
                    place.locality1 = netPlace.locality1;

                    places.add(place);
                }
                return places;
            }
        });
    }
}
