package com.lynx.testtask65apps.other.utils.diffutils;

import android.support.v7.util.DiffUtil;

import java.util.List;

public abstract class BaseDiffUtilsCallback extends DiffUtil.Callback {

    protected final List<?> oldList;
    protected final List<?> newList;

    public BaseDiffUtilsCallback(List<?> oldList, List<?> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public abstract boolean areItemsTheSame(int oldItemPosition, int newItemPosition);

    @Override
    public abstract boolean areContentsTheSame(int oldItemPosition, int newItemPosition);
}
