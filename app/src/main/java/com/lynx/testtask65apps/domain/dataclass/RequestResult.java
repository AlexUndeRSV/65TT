package com.lynx.testtask65apps.domain.dataclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RequestResult {
    @SerializedName("response")
    @Expose
    private List<Worker> worker = null;

    public List<Worker> getWorker() {
        return worker;
    }

    public void setWorker(List<Worker> worker) {
        this.worker = worker;
    }
}
