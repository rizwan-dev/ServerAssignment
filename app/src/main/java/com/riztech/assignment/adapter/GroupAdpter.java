package com.riztech.assignment.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.riztech.assignment.R;
import com.riztech.assignment.model.Group;

import java.util.List;

public class GroupAdpter extends RecyclerView.Adapter<GroupAdpter.GroupViewHolder> {
    List<Group> groups;

    public GroupAdpter(List<Group> groups) {
        this.groups = groups;
    }

    @NonNull
    @Override
    public GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, null);

        return new GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupViewHolder holder, int i) {
        Group group = groups.get(i);

        holder.txtId.setText(group.getId());

        holder.txtName.setText(group.getName());

        holder.txtDescription.setText(Html.fromHtml(group.getDescription()));

    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtId, txtDescription;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.id);
            txtName = itemView.findViewById(R.id.name);
            txtDescription = itemView.findViewById(R.id.description);
        }
    }
}
