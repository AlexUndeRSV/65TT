package com.lynx.testtask65apps.presentation.container;

import com.arellomobile.mvp.MvpView;

public interface ContainerView extends MvpView {
    void hideLoader();

    void showLoader();

    void hideBaseToolbar();

    void showBaseToolbar();

    void setToolbarTitle(String title);

    void dataLoaded(boolean isFirstTime);

    void disableSTR();

    void enableSTR();

    void checkNetCon();
}
