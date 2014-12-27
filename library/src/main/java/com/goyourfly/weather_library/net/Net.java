package com.goyourfly.weather_library.net;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import rx.Observable;
import rx.functions.Func0;
import rx.util.async.Async;

/**
 * Created by gaoyf on 14/12/27.
 */
public class Net {
    /**
     * Use OkHttpClient to get data
     * @param urlString the url
     * @return request body
     * @throws IOException
     */
    private String getContent(String urlString) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(urlString)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }


    /**
     * @param url
     * @return an observable String
     */
    public Observable<String> getData(final String url) {
        return Async.start(new Func0<String>() {
            @Override
            public String call() {
                try {
                    return getContent(url);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }
}
