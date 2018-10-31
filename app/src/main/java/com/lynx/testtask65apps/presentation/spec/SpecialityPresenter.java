package com.lynx.testtask65apps.presentation.spec;


import android.os.Bundle;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.domain.dataclass.Speciality;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.Screen;
import com.lynx.testtask65apps.other.events.EnableSTREvent;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class SpecialityPresenter extends MvpPresenter<SpecialityView> {

    public void hideBaseToolbar() {
        EventBus.getDefault().post(new HideBaseToolbarEvent());
    }

    public void showWorkers(Speciality speciality) {
        Bundle args = new Bundle();
        args.putString(Constants.BundleKeys.ID_KEY, speciality.getId());
        args.putString(Constants.BundleKeys.TITLE_KEY, speciality.getName());
        App.getRouter().navigateTo(Screen.WORKERS.name(), args);
    }


    public void getSpecList() {
        getViewState().setSpecList(App.getDBRepository().getSpecList());
    }

    public void enableSTW() {
        EventBus.getDefault().post(new EnableSTREvent());
    }
}
