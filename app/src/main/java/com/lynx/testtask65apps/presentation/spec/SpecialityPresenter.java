package com.lynx.testtask65apps.presentation.spec;


import android.os.Bundle;

import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.domain.dataclass.Speciality;
import com.lynx.testtask65apps.domain.interactor.LoadWorkersInfoInteractor;
import com.lynx.testtask65apps.domain.dataclass.RequestResult;
import com.lynx.testtask65apps.domain.dataclass.Response;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.Screen;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lynx.testtask65apps.other.events.HideLoaderEvent;
import com.lynx.testtask65apps.other.events.ShowLoaderEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class SpecialityPresenter extends MvpPresenter<SpecialityView> {

    private LoadWorkersInfoInteractor loadWorkersInfoInteractor;

    private Disposable disposable;

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        loadWorkersInfoInteractor = new LoadWorkersInfoInteractor();

        getJsonData();
    }

    private void getJsonData() {
        EventBus.getDefault().post(new ShowLoaderEvent());
        loadWorkersInfoInteractor.loadWorkersInfo(new SingleObserver<RequestResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(RequestResult requestResult) {
                saveData(requestResult.getResponse());
                EventBus.getDefault().post(new HideLoaderEvent());
            }

            @Override
            public void onError(Throwable e) {
//                e.getMessage();
                EventBus.getDefault().post(new HideLoaderEvent());
            }
        });

    }

    private void saveData(List<Response> responseList) {
        App.getDBRepository().deleteTable(Constants.Database.WorkersTable.TABLE_NAME);
        for (Response response : responseList) {
            App.getDBRepository().saveWorker(response);
            App.getDBRepository().saveSpecialities(response.getSpecialty());
        }

        getViewState().setSpecList(App.getDBRepository().getSpecList());
    }

    public void hideBaseToolbar() {
        EventBus.getDefault().post(new HideBaseToolbarEvent());
    }

    public void showWorkers(Speciality speciality) {
        Bundle args = new Bundle();
        args.putString(Constants.BundleKeys.ID_KEY, speciality.getId());
        args.putString(Constants.BundleKeys.TITLE_KEY, speciality.getName());
        App.getRouter().navigateTo(Screen.WORKERS.name(), args);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if(disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
