package com.lynx.testtask65apps.presentation.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.other.Constants;
import com.lynx.testtask65apps.other.utils.CorrectUtils;
import com.squareup.picasso.Picasso;

public class DetailFragment extends MvpAppCompatFragment implements DetailView {
    public static final String TAG = "DetailFragment";
    @InjectPresenter
    DetailPresenter presenter;

    private Worker worker;

    private ImageView collapsingImage;
    private TextView txtFName, txtLName, txtBirthday, txtAge, txtSpec;

    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;

    public static DetailFragment newInstance(final Bundle args) {
        final DetailFragment fragment = new DetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        if (args != null) {
            worker = args.getParcelable(Constants.BundleKeys.WORKER_KEY);
            worker.setSpecialty(args.getParcelableArrayList(Constants.BundleKeys.SPEC_KEY));
        }
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.hideBaseToolbar();
        presenter.disableSTW();

        toolbar = view.findViewById(R.id.toolbar_collapsing);
        toolbar.setNavigationOnClickListener((v) -> presenter.onBackPressed());

        collapsingToolbar = view.findViewById(R.id.collapsingToolbar);

        final String title = worker.getLName() + " " + getActivity().getString(R.string.details);
        collapsingToolbar.setTitle(title);

        collapsingImage = view.findViewById(R.id.collapsingImage);
        txtFName = view.findViewById(R.id.txtFNameDetail);
        txtLName = view.findViewById(R.id.txtLNameDetail);
        txtBirthday = view.findViewById(R.id.txtBirthdayDetail);
        txtAge = view.findViewById(R.id.txtAgeDetail);
        txtSpec = view.findViewById(R.id.txtSpecDetail);

        txtAge.setText(CorrectUtils.getAge(worker.getBirthday()));
        txtFName.setText(worker.getFName());
        txtLName.setText(worker.getLName());
        txtBirthday.setText(worker.getBirthday());

        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < worker.getSpecialty().size(); i++) {
            sb.append(worker.getSpecialty().get(i).getName());
            if (i != worker.getSpecialty().size() - 1) sb.append(",\n");
        }

        txtSpec.setText(sb.toString());

        if (worker.getAvatarUrl() == null || worker.getAvatarUrl().isEmpty())
            collapsingImage.setImageResource(R.drawable.ic_placeholder);
        else
            Picasso.with(getActivity()).load(worker.getAvatarUrl()).placeholder(R.drawable.ic_placeholder).into(collapsingImage);
    }
}
