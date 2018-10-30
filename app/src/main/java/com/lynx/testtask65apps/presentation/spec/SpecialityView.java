package com.lynx.testtask65apps.presentation.spec;

import com.arellomobile.mvp.MvpView;
import com.lynx.testtask65apps.domain.dataclass.Speciality;

import java.util.List;

public interface SpecialityView extends MvpView {

    void setSpecList(List<Speciality> specList);
}
