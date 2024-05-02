package com.example.leftovertracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class leftoverAdapter extends RecyclerView.Adapter<leftoverAdapter.MyViewHolder>{
    Context context;
    ArrayList<LeftoverList> LeftoverLists;

    public leftoverAdapter(Context context, ArrayList<LeftoverList> LeftoverLists){
        this.context = context;
        this.LeftoverLists = LeftoverLists;
    }

    @NonNull
    @Override
    public leftoverAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_view_rows, parent, false);
        return new leftoverAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull leftoverAdapter.MyViewHolder holder, int position) {
        holder.tvName.setText(LeftoverLists.get(position).getLeftoverFoodName());
        holder.tvDays.setText(LeftoverLists.get(position).getLeftoverDaystoEat());
    }

    @Override
    public int getItemCount() {
        return LeftoverLists.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;
        TextView tvDays;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.rItemName);
            tvDays = itemView.findViewById(R.id.rDaysLeft);
        }
    }
}
