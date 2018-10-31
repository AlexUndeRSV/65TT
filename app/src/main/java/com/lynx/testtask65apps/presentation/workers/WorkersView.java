package com.lynx.testtask65apps.presentation.workers;

import com.arellomobile.mvp.MvpView;
import com.lynx.testtask65apps.domain.dataclass.Response;

import java.util.List;

public interface WorkersView extends MvpView {

    void setWorkersList(List<Response> workersList);
}
