package com.lynx.testtask65apps.presentation.container;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.App;
import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.other.CustomNavigator;
import com.lynx.testtask65apps.other.Screen;

public class ContainerActivity extends MvpAppCompatActivity implements ContainerView {

    @InjectPresenter
    ContainerPresenter presenter;

    private View loader;

    private Toolbar toolbar;

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();

        App.getRouter().newRootScreen(Screen.SPEC.name());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_container);

        toolbar = findViewById(R.id.base_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(v -> onBackPressed());

        loader = findViewById(R.id.pre_loader);

        App.getNavigatorHolder().setNavigator(navigator);
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
}
