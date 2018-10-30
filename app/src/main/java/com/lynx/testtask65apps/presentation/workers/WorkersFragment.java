package com.lynx.testtask65apps.presentation.workers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynx.testtask65apps.presentation.workers.WorkersView;
import com.lynx.testtask65apps.presentation.workers.WorkersPresenter;

import com.arellomobile.mvp.MvpAppCompatFragment;

import com.lynx.testtask65apps.R;

import com.arellomobile.mvp.presenter.InjectPresenter;

public class WorkersFragment extends MvpAppCompatFragment implements WorkersView {
    public static final String TAG = "WorkersFragment";
    @InjectPresenter
    WorkersPresenter presenter;


    public static WorkersFragment newInstance(Bundle args) {
        WorkersFragment fragment = new WorkersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workers, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}
