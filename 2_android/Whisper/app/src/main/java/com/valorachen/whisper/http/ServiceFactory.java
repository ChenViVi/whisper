package com.valorachen.whisper.http;

import com.valorachen.whisper.http.service.RequestService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.valorachen.whisper.MyApplication.BASE_URL;

/**
 * Created by vivi on 2016/9/17.
 */
public class ServiceFactory {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static RequestService getRequestService() {
        return retrofit.create(RequestService.class);
    }
}
