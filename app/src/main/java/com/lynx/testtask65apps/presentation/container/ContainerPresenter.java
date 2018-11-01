package com.lynx.testtask65apps.presentation.container;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.domain.dataclass.RequestResult;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.domain.interactor.LoadWorkersInfoInteractor;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.events.DisableSTREvent;
import com.lynx.testtask65apps.other.events.EnableSTREvent;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;
import com.lynx.testtask65apps.other.events.HideLoaderEvent;
import com.lynx.testtask65apps.other.events.SetToolbarTitleEvent;
import com.lynx.testtask65apps.other.events.ShowBaseToolbarEvent;
import com.lynx.testtask65apps.other.events.ShowLoaderEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@InjectViewState
public class ContainerPresenter extends MvpPresenter<ContainerView> {

    private LoadWorkersInfoInteractor loadWorkersInfoInteractor;

    private Disposable disposable;

    public void onCreate() {
        loadWorkersInfoInteractor = new LoadWorkersInfoInteractor();

        checkDatabase(true);
    }

    public void checkDatabase(boolean isFirstTime) {
        if (App.getDBRepository().isEmpty()) {
            getViewState().checkNetCon();
        } else {
            getViewState().dataLoaded(isFirstTime);
        }
    }

    public void loadJsonData(boolean isFirstTime) {
        if (isFirstTime) EventBus.getDefault().post(new ShowLoaderEvent());
        EventBus.getDefault().post(new DisableSTREvent());
        loadWorkersInfoInteractor.loadWorkersInfo(new SingleObserver<RequestResult>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onSuccess(RequestResult requestResult) {
                saveData(requestResult.getWorker(), isFirstTime);
                EventBus.getDefault().post(new HideLoaderEvent());
                EventBus.getDefault().post(new EnableSTREvent());
            }

            @Override
            public void onError(Throwable e) {
                Log.e(Constants.LogTags.ERROR, e.getMessage());
                EventBus.getDefault().post(new HideLoaderEvent());
                EventBus.getDefault().post(new EnableSTREvent());
            }
        });
    }

    private void saveData(List<Worker> workerList, boolean isFirstTime) {
        App.getDBRepository().deleteTable(Constants.Database.WorkersTable.TABLE_NAME);
        App.getDBRepository().deleteTable(Constants.Database.SpecialityTable.TABLE_NAME);
        for (Worker worker : workerList) {
            App.getDBRepository().saveWorker(worker);
            App.getDBRepository().saveSpecialities(worker.getSpecialty());
        }

        getViewState().dataLoaded(isFirstTime);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
    }

    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop() {
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onHideLoader(HideLoaderEvent event) {
        getViewState().hideLoader();
    }

    @Subscribe
    public void onShowLoader(ShowLoaderEvent event) {
        getViewState().showLoader();
    }

    @Subscribe
    public void onHideBaseToolbar(HideBaseToolbarEvent event) {
        getViewState().hideBaseToolbar();
    }

    @Subscribe
    public void onShowBaseToolbar(ShowBaseToolbarEvent event) {
        getViewState().showBaseToolbar();
    }

    @Subscribe
    public void onSetToolbarTitle(SetToolbarTitleEvent event) {
        getViewState().setToolbarTitle(event.getTitle());
    }

    @Subscribe
    public void onDisableSTR(DisableSTREvent event) { // STR - swipe to refresh
        getViewState().disableSTR();
    }

    @Subscribe
    public void onEnableSTR(EnableSTREvent event) { // STR - swipe to refresh
        getViewState().enableSTR();
    }
}
