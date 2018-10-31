package com.lynx.testtask65apps.presentation.spec;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.domain.dataclass.Speciality;
import com.lynx.testtask65apps.other.events.HideBaseToolbarEvent;
import com.lynx.testtask65apps.other.itemdecorators.LinearItemDecorator;
import com.lynx.testtask65apps.presentation.spec.adapter.SpecialityAdapter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class SpecialityFragment extends MvpAppCompatFragment implements SpecialityView, SpecialityAdapter.OnSpecItemClickListener {
    public static final String TAG = "SpecialityFragment";
    @InjectPresenter
    SpecialityPresenter presenter;

    private RecyclerView recView;
    private SpecialityAdapter adapter;

    private List<Speciality> specList;

    public static SpecialityFragment newInstance(Bundle args) {
        SpecialityFragment fragment = new SpecialityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speciality, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.hideBaseToolbar();

        recView = view.findViewById(R.id.recViewSpec);
        adapter = new SpecialityAdapter(getActivity());
        adapter.setOnSpecItemClickListener(this);

        recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recView.addItemDecoration(new LinearItemDecorator(20));
        recView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position) {
        Speciality speciality = specList.get(position);
        presenter.showWorkers(speciality);
    }

    @Override
    public void setSpecList(List<Speciality> specList) {
        this.specList = specList;
        adapter.setSpecialityList(specList);
        adapter.notifyDataSetChanged();
    }
}
