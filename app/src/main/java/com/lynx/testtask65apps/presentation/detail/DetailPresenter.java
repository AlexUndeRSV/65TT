package com.lynx.testtask65apps.presentation.detail;


import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;
import com.lynx.testtask65apps.presentation.detail.DetailView;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import org.greenrobot.eventbus.EventBus;

@InjectViewState
public class DetailPresenter extends MvpPresenter<DetailView> {

    public void hideBaseToolbar() {
        EventBus.getDefault().post(new HideBaseToolbarEvent());
    }

    public void onBackPressed() {
        App.getRouter().exit();
    }
}
