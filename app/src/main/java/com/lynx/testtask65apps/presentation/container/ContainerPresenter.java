package com.lynx.testtask65apps.presentation.container;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;
import com.lynx.testtask65apps.other.events.HideLoaderEvent;
import com.lynx.testtask65apps.other.events.ShowBaseToolbarEvent;
import com.lynx.testtask65apps.other.events.ShowLoaderEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

@InjectViewState
public class ContainerPresenter extends MvpPresenter<ContainerView> {


    public void onStart() {
        EventBus.getDefault().register(this);
    }

    public void onStop(){
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onHideLoader(HideLoaderEvent event){
        getViewState().hideLoader();
    }

    @Subscribe
    public void onShowLoader(ShowLoaderEvent event){
        getViewState().showLoader();
    }

    @Subscribe
    public void onHideBaseToolbar(HideBaseToolbarEvent event){
        getViewState().hideBaseToolbar();
    }

    @Subscribe
    public void onShowBaseToolbar(ShowBaseToolbarEvent event){
        getViewState().showBaseToolbar();
    }
}
