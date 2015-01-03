package com.goyourfly.freeweather;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.goyourfly.weather_library.WeatherModule;
import com.goyourfly.weather_library.ui.obj.Forecast;
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
    @InjectView(R.id.linearLayout)
    LinearLayout mWeatherBottom;
    @InjectView(R.id.wind)
    TextView mWind;
    @InjectView(R.id.atmosphere)
    TextView mAtmosphere;
    @InjectView(R.id.astronomy)
    TextView mAstronomy;
    @InjectView(R.id.temp)
    TextView mTemp;
    @InjectView(R.id.place)
    TextView mPlace;
    @InjectView(R.id.weather_icon)
    ImageView mWeatherIcon;

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
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.inject(this, view);

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

        return view;
    }

    private void initWeather(Weather weather) {
        mPlace.setText(WeatherModule.getInstance().getPlace(weather.woeid).name);
        mWind.setText("wind:" + weather.windDirection + "/" + weather.windSpeed + "mph");
        mAtmosphere.setText("humidity:" + weather.humidity);
        mAstronomy.setText("astronomy:" + weather.sunrise + "/" + weather.sunset);
        mTemp.setText((int) weather.temp + "°");
        mWeatherIcon.setImageResource(WeatherIconFactory.getIcon(weather.code));
        mWeatherBottom.removeAllViews();
        for (Forecast forecast : weather.forecasts) {
            View convertView = getActivity().getLayoutInflater().inflate(R.layout.item_weather, null, false);

            TextView day = (TextView) convertView.findViewById(R.id.forecast_day);
            TextView temp = (TextView) convertView.findViewById(R.id.forecast_temp);
            ImageView weatherIcon = (ImageView)convertView.findViewById(R.id.forecast_weatherIcon);
            weatherIcon.setImageResource(WeatherIconFactory.getIcon(forecast.code));

            day.setText(forecast.day);
            temp.setText(forecast.low + "/" + forecast.high + "°");

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            params.gravity = Gravity.CENTER;
            mWeatherBottom.addView(convertView, params);
        }
    }
}
