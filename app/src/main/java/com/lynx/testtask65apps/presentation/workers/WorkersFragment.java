package com.lynx.testtask65apps.presentation.workers;

import android.os.Bundle;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.itemdecorators.LinearItemDecorator;
import com.lynx.testtask65apps.other.utils.OnListItemClickListener;
import com.lynx.testtask65apps.other.utils.diffutils.BaseDiffUtilsCallback;
import com.lynx.testtask65apps.other.utils.diffutils.WorkersDiffUtilsCallback;
import com.lynx.testtask65apps.presentation.workers.adapter.WorkersAdapter;

import java.util.ArrayList;
import java.util.List;

public class WorkersFragment extends MvpAppCompatFragment implements WorkersView, OnListItemClickListener {
    public static final String TAG = "WorkersFragment";
    @InjectPresenter
    WorkersPresenter presenter;

    private String specId;
    private String specTitle;

    private RecyclerView recView;
    private WorkersAdapter adapter;

    private List<Worker> workersList;

    public static WorkersFragment newInstance(final Bundle args) {
        final WorkersFragment fragment = new WorkersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();

        if (args != null) {
            specId = args.getString(Constants.BundleKeys.ID_KEY);
            specTitle = args.getString(Constants.BundleKeys.TITLE_KEY);
        }

        presenter.showWorkers(specId);
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
        presenter.enableSTW();

        workersList = new ArrayList<>();

        recView = view.findViewById(R.id.recViewWorkers);
        adapter = new WorkersAdapter(getActivity());
        adapter.setOnListItemClickListener(this);

        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.addItemDecoration(new LinearItemDecorator(20));
        recView.setAdapter(adapter);
    }

    @Override
    public void setWorkersList(final List<Worker> workersList) {

        this.workersList = workersList;

        final BaseDiffUtilsCallback specialityDiffUtilsCallback = new WorkersDiffUtilsCallback(adapter.getWorkerList(), workersList);
        final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(specialityDiffUtilsCallback);

        adapter.setWorkersList(workersList);

        diffResult.dispatchUpdatesTo(adapter);
    }

    @Override
    public void onItemClick(final int position) {
        final Worker worker = workersList.get(position);
        presenter.showDetailInfo(worker);
    }
}
