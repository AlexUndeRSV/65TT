package com.lynx.testtask65apps.presentation.workers.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lynx.testtask65apps.R;
import com.lynx.testtask65apps.domain.dataclass.Worker;
import com.lynx.testtask65apps.other.utils.CorrectUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkersViewHolder> {

    public interface OnWorkerItemClickListener {
        void onItemClick(int position);
    }

    private OnWorkerItemClickListener onWorkerItemClickListener = null;

    public void setOnWorkerItemClickListener(OnWorkerItemClickListener onWorkerItemClickListener) {
        this.onWorkerItemClickListener = onWorkerItemClickListener;
    }

    private Context ctx;
    private List<Worker> workersList;

    public WorkersAdapter(Context ctx) {
        this.ctx = ctx;
        workersList = new ArrayList<>();
    }

    public void setWorkersList(List<Worker> workersList) {
        this.workersList = workersList;
    }

    @NonNull
    @Override
    public WorkersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(ctx).inflate(R.layout.worker_item, parent, false);
        return new WorkersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkersViewHolder holder, int position) {
        Worker worker = workersList.get(position);

        holder.txtFirstName.setText(worker.getFName());
        holder.txtLastName.setText(worker.getLName());
        holder.txtAge.setText(CorrectUtils.getAge(worker.getBirthday()));

        if (worker.getAvatarUrl() == null || worker.getAvatarUrl().isEmpty())
            holder.imgWorkerAvatar.setImageResource(R.drawable.ic_placeholder);
        else
            Picasso.with(ctx).load(worker.getAvatarUrl()).placeholder(R.drawable.ic_placeholder).into(holder.imgWorkerAvatar);

    }

    @Override
    public int getItemCount() {
        return workersList.size();
    }

    public class WorkersViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFirstName, txtLastName, txtAge;
        private ImageView imgWorkerAvatar;

        public WorkersViewHolder(View itemView) {
            super(itemView);

            txtAge = itemView.findViewById(R.id.txtWorkerAge);
            txtFirstName = itemView.findViewById(R.id.txtFirstName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            imgWorkerAvatar = itemView.findViewById(R.id.imgWorkerAva);

            itemView.setOnClickListener((v) -> {
                if (onWorkerItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onWorkerItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
