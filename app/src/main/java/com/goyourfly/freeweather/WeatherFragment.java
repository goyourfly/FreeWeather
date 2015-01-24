package com.goyourfly.freeweather;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.goyourfly.weather_library.WeatherModule;
import com.goyourfly.weather_library.ui.obj.Forecast;
import com.goyourfly.weather_library.ui.obj.ICiBa;
import com.goyourfly.weather_library.ui.obj.Weather;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by gaoyf on 15/1/3.
 */
public class WeatherFragment extends Fragment {
    public static final String BUNDLE_WOEID = "BUNDLE_WOEID";
    private int mWoeid;
    private Handler mHandler;
    private View rootView;
    @InjectView(R.id.linearLayout)
    ViewGroup mWeatherBottom;
    @InjectView(R.id.wind)
    TextView mWind;
    @InjectView(R.id.atmosphere)
    TextView mAtmosphere;
    @InjectView(R.id.sunrise)
    TextView msSunrise;
    @InjectView(R.id.sunset)
    TextView msSunset;
    @InjectView(R.id.temp)
    TextView mTemp;
    @InjectView(R.id.date)
    TextView mDate;
    @InjectView(R.id.weather)
    TextView mPlace;
    @InjectView(R.id.weather_icon)
    TextView mWeatherIcon;
    @InjectView(R.id.per_day_word)
    TextView mPerDayWord;

    public static WeatherFragment newInstance(int woeid) {
        WeatherFragment weatherFragment = new WeatherFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BUNDLE_WOEID, woeid);
        weatherFragment.setArguments(bundle);
        return weatherFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mWoeid = bundle.getInt(BUNDLE_WOEID);
        mHandler = new Handler();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, rootView);

        WeatherModule.getInstance().getWeather(new WeatherModule.OnGetListener<Weather>() {
            @Override
            public void onGet(final Weather weather) {
                if (weather != null)
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            initWeather(weather);
                        }
                    });
            }

            @Override
            public void onError(String err) {

            }

            @Override
            public void onFinally() {

            }
        }, mWoeid);

        return rootView;
    }

    private void initWeather(Weather weather) {
        if (rootView.getVisibility() != View.VISIBLE)
            rootView.setVisibility(View.VISIBLE);
        mPlace.setText(weather.weatherName + " / ");
        mWind.setText(" " +
                WeatherIconFactory.getWindDirection(getActivity(), weather.windDirection)
                + " " + WeatherIconFactory.getWindIcon(getActivity(), weather.windSpeed));


        mAtmosphere.setText(" " + getString(R.string.wi_sprinkles) + "  " + weather.humidity + "%");
        msSunrise.setText(getString(R.string.wi_sunrise) + " " + weather.sunrise);
        msSunset.setText(getString(R.string.wi_sunset) + " " + weather.sunset);
        mTemp.setText((int) weather.temp + "°");
        mDate.setText(weather.date);
        mWeatherIcon.setText(WeatherIconFactory.getIcon(getActivity(), weather.code));
        mWeatherBottom.removeAllViewsInLayout();
        for (Forecast forecast : weather.forecasts) {
            View convertView = getLayoutInflater(getArguments()).inflate(R.layout.item_weather, null,false);
            TextView day = (TextView) convertView.findViewById(R.id.forecast_day);
            TextView temp = (TextView) convertView.findViewById(R.id.forecast_temp);
            TextView weatherIcon = (TextView) convertView.findViewById(R.id.forecast_weatherIcon);
            weatherIcon.setText(WeatherIconFactory.getIcon(getActivity(), forecast.code));

            day.setText(forecast.day);
            temp.setText(forecast.low + "/" + forecast.high + "°");
            mWeatherBottom.addView(convertView);
        }

        WeatherModule.getInstance().getWordPerDay(new WeatherModule.OnGetListener<ICiBa>() {
            @Override
            public void onGet(final ICiBa iCiBa) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mPerDayWord.setText(iCiBa.content + "\n" + iCiBa.note);

                    }
                });
            }

            @Override
            public void onError(String err) {

            }

            @Override
            public void onFinally() {

            }
        });
    }
}
