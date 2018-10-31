package com.lynx.testtask65apps.presentation.workers;


import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.Screen;
import com.lynx.testtask65apps.other.events.EnableSTREvent;
import com.lynx.testtask65apps.other.events.SetToolbarTitleEvent;
import com.lynx.testtask65apps.other.events.ShowBaseToolbarEvent;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class WorkersPresenter extends MvpPresenter<WorkersView> {

    public void setToolbarTitle(String toolbarTitle) {
        EventBus.getDefault().post(new SetToolbarTitleEvent(toolbarTitle));
    }

    public void showToolbar() {
        EventBus.getDefault().post(new ShowBaseToolbarEvent());
    }

    public void onCreate(String specId) {
        getViewState().setWorkersList(App.getDBRepository().getWorkersList(specId));
    }

    public void showDetailInfo(Worker worker) {
        Bundle args = new Bundle();
        args.putParcelable(Constants.BundleKeys.WORKER_KEY, worker);
        args.putParcelableArrayList(Constants.BundleKeys.SPEC_KEY, worker.getSpecialty());
        App.getRouter().navigateTo(Screen.DETAIL.name(), args);
    }

    public void enableSTW() {
        EventBus.getDefault().post(new EnableSTREvent());
    }
}
