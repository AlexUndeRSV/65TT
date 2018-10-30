package com.lynx.testtask65apps.data.net;

import com.lynx.testtask65apps.other.Constants;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {

    final private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.WorkersApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    final public WorkersApi workersApi = retrofit.create(WorkersApi.class);
}
