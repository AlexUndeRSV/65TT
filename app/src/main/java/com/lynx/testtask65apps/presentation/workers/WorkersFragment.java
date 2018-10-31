package com.lynx.testtask65apps.presentation.workers;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynx.testtask65apps.domain.dataclass.Response;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.itemdecorators.LinearItemDecorator;

import com.arellomobile.mvp.MvpAppCompatFragment;

import com.lynx.testtask65apps.R;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.presentation.workers.adapter.WorkersAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkersFragment extends MvpAppCompatFragment implements WorkersView, WorkersAdapter.OnWorkerItemClickListener {
    public static final String TAG = "WorkersFragment";
    @InjectPresenter
    WorkersPresenter presenter;

    private String specId;
    private String specTitle;

    private RecyclerView recView;
    private WorkersAdapter adapter;

    private List<Response> workersList;

    public static WorkersFragment newInstance(Bundle args) {
        WorkersFragment fragment = new WorkersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();

        if(args != null) {
            specId = args.getString(Constants.BundleKeys.ID_KEY);
            specTitle = args.getString(Constants.BundleKeys.TITLE_KEY);
        }

        presenter.onCreate(specId);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workers, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.showToolbar();
        presenter.setToolbarTitle(specTitle);

        workersList = new ArrayList<>();

        recView = view.findViewById(R.id.recViewWorkers);
        adapter = new WorkersAdapter(getActivity());
        adapter.setOnWorkerItemClickListener(this);

        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.addItemDecoration(new LinearItemDecorator(20));
        recView.setAdapter(adapter);
    }

    @Override
    public void setWorkersList(List<Response> workersList) {
        this.workersList = workersList;
        adapter.setWorkersList(workersList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Response response = workersList.get(position);
        presenter.showDetailInfo(response);
    }
}
