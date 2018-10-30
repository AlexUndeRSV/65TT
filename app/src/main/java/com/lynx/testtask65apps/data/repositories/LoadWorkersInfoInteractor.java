package com.lynx.testtask65apps.data.repositories;

import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.domain.dataclass.RequestResult;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoadWorkersInfoInteractor {

    public void loadWorkersInfo(SingleObserver<RequestResult> sub){
        App.getNetworkService().workersApi
                .getWorkersInfo()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(sub);
    }
}
