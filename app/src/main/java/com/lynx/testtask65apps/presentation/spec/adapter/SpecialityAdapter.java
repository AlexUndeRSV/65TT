package com.lynx.testtask65apps.presentation.spec.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.domain.dataclass.Speciality;

import java.util.ArrayList;
import java.util.List;

public class SpecialityAdapter extends RecyclerView.Adapter<SpecialityAdapter.SpecialityViewHolder> {

    public interface OnSpecItemClickListener {
        void onItemClick(int position);
    }

    private OnSpecItemClickListener onSpecItemClickListener = null;

    public void setOnSpecItemClickListener(OnSpecItemClickListener onSpecItemClickListener) {
        this.onSpecItemClickListener = onSpecItemClickListener;
    }

    private Context ctx;
    private List<Speciality> specialityList;

    public SpecialityAdapter(Context ctx) {
        this.ctx = ctx;
        specialityList = new ArrayList<>();
    }

    public void setSpecialityList(List<Speciality> specialityList) {
        this.specialityList = specialityList;
    }

    @NonNull
    @Override
    public SpecialityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.spec_item_layout, parent, false);
        return new SpecialityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SpecialityViewHolder holder, int position) {
        Speciality speciality = specialityList.get(position);

        holder.txtTitle.setText(speciality.getName());
    }

    @Override
    public int getItemCount() {
        return specialityList.size();
    }

    public class SpecialityViewHolder extends RecyclerView.ViewHolder {

        private TextView txtTitle;

        public SpecialityViewHolder(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtSpecTitle);
            itemView.setOnClickListener((v) -> {
                if (onSpecItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onSpecItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
