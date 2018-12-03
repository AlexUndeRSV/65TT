package com.lynx.testtask65apps.other.utils.diffutils;

import com.lynx.testtask65apps.domain.dataclass.Speciality;

import java.util.List;

public class SpecialityDiffUtilsCallback extends BaseDiffUtilsCallback {

    public SpecialityDiffUtilsCallback(List<?> oldList, List<?> newList) {
        super(oldList, newList);
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        Speciality specialityOld = (Speciality) oldList.get(oldItemPosition);
        Speciality specialityNew = (Speciality) newList.get(newItemPosition);

        return specialityOld.getId().equals(specialityNew.getId());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        Speciality specialityOld = (Speciality) oldList.get(oldItemPosition);
        Speciality specialityNew = (Speciality) newList.get(newItemPosition);

        return specialityOld.getName().equals(specialityNew.getName());
    }
}
