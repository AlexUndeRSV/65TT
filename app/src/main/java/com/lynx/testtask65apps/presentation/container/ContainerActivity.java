package com.lynx.testtask65apps.presentation.container;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.other.CustomNavigator;
import com.lynx.testtask65apps.other.Screen;
import com.lynx.testtask65apps.other.utils.NetworkUtils;

public class ContainerActivity extends MvpAppCompatActivity implements ContainerView {

    @InjectPresenter
    ContainerPresenter presenter;

    private View loader;

    private Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    private View fragmentContainer;
    private View noNetworkLayout;

    private TextView txtRefresh;

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        fragmentContainer = findViewById(R.id.fragment_container);

        noNetworkLayout = findViewById(R.id.no_net_layout);

        txtRefresh = findViewById(R.id.txtRefresh);
        txtRefresh.setOnClickListener((v) -> {
            if (NetworkUtils.hasConnection(this)) {
                presenter.loadJsonData(true);
                noNetworkLayout.setVisibility(View.GONE);
            }
        });

        toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.loadJsonData(false);
            fragmentContainer.setClickable(false);
        });

        loader = findViewById(R.id.pre_loader);

        App.getNavigatorHolder().setNavigator(navigator);

        presenter.onCreate();
        hideBaseToolbar();
    }

    private CustomNavigator navigator = new CustomNavigator(ContainerActivity.this, getSupportFragmentManager(), R.id.fragment_container);

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getNavigatorHolder().removeNavigator();
    }

    @Override
    public void hideLoader() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBaseToolbar() {
        toolbar.setVisibility(View.GONE);
    }

    @Override
    public void showBaseToolbar() {
        toolbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void setToolbarTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void dataLoaded(boolean isFirstTime) {
        if (isFirstTime) App.getRouter().newRootScreen(Screen.SPEC.name());
        swipeRefreshLayout.setRefreshing(false);
        fragmentContainer.setClickable(true);
    }

    @Override
    public void disableSTR() {
        swipeRefreshLayout.setEnabled(false);
    }

    @Override
    public void enableSTR() {
        swipeRefreshLayout.setEnabled(true);
    }

    @Override
    public void checkNetCon() {
        if (NetworkUtils.hasConnection(ContainerActivity.this)) {
            presenter.loadJsonData(true);
        } else {
            showNoNet();
        }
    }

    public void showNoNet() {
        noNetworkLayout.setVisibility(View.VISIBLE);
    }
}
