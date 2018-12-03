package com.lynx.testtask65apps.presentation.spec;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lynx.testtask65apps.domain.dataclass.Speciality;

import java.util.List;

public interface SpecialityView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setSpecList(List<Speciality> specList);
}
