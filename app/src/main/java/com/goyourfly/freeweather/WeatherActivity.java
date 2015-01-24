package com.goyourfly.freeweather;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.goyourfly.weather_library.WeatherModule;
import com.goyourfly.weather_library.ui.obj.Place;
import com.goyourfly.weather_library.ui.obj.Weather;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class WeatherActivity extends ActionBarActivity implements ViewPager.OnPageChangeListener {
    private Toolbar mToolbar;
    @InjectView(R.id.viewPager)
    ViewPager mPager;
    private MyPagerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.inject(this);

        mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        mToolbar.setTitle("Weather");
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(mAdapter);
        mPager.setOnPageChangeListener(this);
        mAdapter.setData(WeatherModule.getInstance().getAllPlace());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_weather,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_place) {
            Intent intent = new Intent(this, AddPlaceActivity.class);
            startActivityForResult(intent, AddPlaceActivity.REQUEST_CODE);
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

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == AddPlaceActivity.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                int woeid = data.getIntExtra(AddPlaceActivity.INTENT_ADD_PLACE, -1);
                WeatherModule.getInstance().getWeather(new WeatherModule.OnGetListener<Weather>() {
                    @Override
                    public void onGet(Weather weather) {
                        toast("GetWeathering");
                    }

                    @Override
                    public void onError(String err) {
                        toast("GetWeatherError");
                    }

                    @Override
                    public void onFinally() {
                        toast("GetSuccess");
                    }
                }, woeid);
            }
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        mToolbar.setTitle("Weather-" + mAdapter.getPlace(position).name);
    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        private List<Place> mPlace;

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addData(Place place) {
            mPlace.add(place);
            notifyDataSetChanged();
        }

        public void addData(List<Place> places) {
            mPlace.addAll(places);
            notifyDataSetChanged();
        }

        public void setData(List<Place> places) {
            mPlace = places;
            notifyDataSetChanged();
        }

        public Place getPlace(int position) {
            return mPlace.get(position);
        }

        @Override
        public Fragment getItem(int position) {
            return WeatherFragment.newInstance(mPlace.get(position).woeid);
        }

        @Override
        public int getCount() {
            return mPlace == null ? 0 : mPlace.size();
        }
    }
}
