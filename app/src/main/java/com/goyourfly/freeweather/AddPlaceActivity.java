package com.goyourfly.freeweather;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.goyourfly.weather_library.WeatherModule;
import com.goyourfly.weather_library.ui.obj.Place;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class AddPlaceActivity extends ActionBarActivity {
    public static final int REQUEST_CODE = -1;
    public static final String INTENT_ADD_PLACE = "INTENT_ADD_PLACE";
    @InjectView(R.id.editText)
    EditText mEditText;
    @InjectView(R.id.listView)
    ListView mListView;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_place);
        ButterKnife.inject(this);

        mAdapter = new MyAdapter();
        mListView.setAdapter(mAdapter);

        mEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                doSearch(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = (Place) parent.getAdapter().getItem(position);
                WeatherModule.getInstance().addPlace(place);
                Intent intent = new Intent();
                intent.putExtra(INTENT_ADD_PLACE, place.woeid);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void doSearch(String text) {
        if (text == null || text.isEmpty()) {
            mAdapter.setData(null);
            mAdapter.notifyDataSetChanged();
            return;
        }
        WeatherModule.getInstance().getWoeId(new WeatherModule.OnGetListener<List<Place>>() {
            @Override
            public void onGet(final List<Place> places) {
                Log.d("", "OnGet:" + new Gson().toJson(places));
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.setData(places);
                    }
                });
            }

            @Override
            public void onError(String err) {
                mAdapter.setData(null);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onFinally() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });
            }
        }, text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add_place) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_CANCELED);
        super.onBackPressed();
    }

    private class MyAdapter extends BaseAdapter {

        private List<Place> mPlaces;

        @Override
        public int getCount() {
            return mPlaces == null ? 0 : mPlaces.size();
        }

        public void setData(List<Place> places) {
            mPlaces = places;
        }

        @Override
        public Place getItem(int position) {
            return mPlaces.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null)
                convertView = getLayoutInflater().inflate(android.R.layout.simple_list_item_1, parent, false);

            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);

            Place place = getItem(position);
            textView.setText(place.name + ", " + place.country + ", " + place.admin1 + ", " + place.admin2);

            return convertView;
        }
    }
}
