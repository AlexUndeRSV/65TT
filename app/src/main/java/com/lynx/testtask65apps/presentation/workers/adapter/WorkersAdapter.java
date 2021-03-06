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
import com.lynx.testtask65apps.other.utils.OnListItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class WorkersAdapter extends RecyclerView.Adapter<WorkersAdapter.WorkersViewHolder> {

    private OnListItemClickListener onListItemClickListener = null;

    public void setOnListItemClickListener(final OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    private final Context ctx;
    private final List<Worker> workersList;

    public WorkersAdapter(final Context ctx) {
        this.ctx = ctx;
        workersList = new ArrayList<>();
    }

    public void setWorkersList(final List<Worker> workersList) {
        this.workersList.clear();
        this.workersList.addAll(workersList);
    }

    @NonNull
    @Override
    public WorkersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(ctx).inflate(R.layout.worker_item, parent, false);
        return new WorkersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkersViewHolder holder, int position) {
        final Worker worker = workersList.get(position);

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

    public List<Worker> getWorkerList() {
        return workersList;
    }

    public class WorkersViewHolder extends RecyclerView.ViewHolder {

        private TextView txtFirstName, txtLastName, txtAge;
        private ImageView imgWorkerAvatar;

        public WorkersViewHolder(final View itemView) {
            super(itemView);

            txtAge = itemView.findViewById(R.id.txtWorkerAge);
            txtFirstName = itemView.findViewById(R.id.txtFirstName);
            txtLastName = itemView.findViewById(R.id.txtLastName);
            imgWorkerAvatar = itemView.findViewById(R.id.imgWorkerAva);

            itemView.setOnClickListener((v) -> {
                if (onListItemClickListener != null) {
                    final int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onListItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
