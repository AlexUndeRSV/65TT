package com.lynx.testtask65apps.presentation.workers;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lynx.testtask65apps.domain.dataclass.Worker;

import java.util.List;

public interface WorkersView extends MvpView {
    @StateStrategyType(AddToEndSingleStrategy.class)
    void setWorkersList(List<Worker> workersList);
}
