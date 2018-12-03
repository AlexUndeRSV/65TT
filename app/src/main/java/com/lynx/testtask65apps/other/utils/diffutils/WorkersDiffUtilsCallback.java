package com.lynx.testtask65apps.other.utils.diffutils;

import com.lynx.testtask65apps.domain.dataclass.Worker;

import java.util.List;

public class WorkersDiffUtilsCallback extends BaseDiffUtilsCallback {

    public WorkersDiffUtilsCallback(List<?> oldList, List<?> newList) {
        super(oldList, newList);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Worker workerOld = (Worker) oldList.get(oldItemPosition);
        Worker workerNew = (Worker) newList.get(newItemPosition);

        return isSame(workerOld, workerNew);
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return isSame((Worker) oldList.get(oldItemPosition), (Worker) newList.get(newItemPosition));
    }

    private boolean isSame(Worker workerOld, Worker workerNew) {
        return workerOld.getAvatarUrl().equals(workerNew.getAvatarUrl())
                && workerOld.getBirthday().equals(workerNew.getBirthday())
                && workerOld.getFName().equals(workerNew.getFName())
                && workerOld.getLName().equals(workerNew.getLName())
                && workerOld.getSpecialty().equals(workerNew.getSpecialty());
    }
}
