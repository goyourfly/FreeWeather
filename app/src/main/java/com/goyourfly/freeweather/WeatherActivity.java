package com.goyourfly.freeweather;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.goyourfly.weather_library.WeatherModule;
import com.goyourfly.weather_library.net.obj.NetCurrentWeather;
import com.goyourfly.weather_library.ui.obj.CurrentWeather;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class WeatherActivity extends ActionBarActivity {
    private WeatherModule mWeatherModule;

    @InjectView(R.id.weather)
    TextView weather;
    @InjectView(R.id.temp)
    TextView temp;
    @InjectView(R.id.wind)
    TextView wind;
    @InjectView(R.id.cloud)
    TextView cloud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.inject(this);

        mWeatherModule = new WeatherModule();
        mWeatherModule.getCurrentWeather(new WeatherModule.OnGetListener<CurrentWeather>() {
            @Override
            public void onGet(CurrentWeather currentWeather) {
                toast("GetWeathering");
                refreshWeather(currentWeather);
            }

            @Override
            public void onError(String err) {
                toast("GetWeatherError");
            }

            @Override
            public void onFinally() {
                toast("GetSuccess");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_weather, menu);
        return true;
    }

    private void refreshWeather(final CurrentWeather currentWeather) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                weather.setText("Weather:" + currentWeather.weather);
                temp.setText("Temp:" + currentWeather.temp);
                wind.setText("wind:" + currentWeather.wind);
                cloud.setText("cloud:" + currentWeather.cloud);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(WeatherActivity.this, msg, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
