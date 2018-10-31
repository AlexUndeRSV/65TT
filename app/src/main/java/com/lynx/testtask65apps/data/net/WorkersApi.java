package com.lynx.testtask65apps.data.net;

import com.lynx.testtask65apps.domain.dataclass.RequestResult;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface WorkersApi {
    @GET("/65gb/static/raw/master/testTask.json")
    Single<RequestResult> getWorkersInfo();
}
