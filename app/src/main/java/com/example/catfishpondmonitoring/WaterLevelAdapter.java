package com.example.catfishpondmonitoring;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WaterLevelAdapter extends RecyclerView.Adapter<WaterLevelAdapter.WaterLevelViewHolder> {

    private List<WaterLevelData> waterLevelList;

    public WaterLevelAdapter(List<WaterLevelData> waterLevelList) {
        this.waterLevelList = waterLevelList;
    }

    @NonNull
    @Override
    public WaterLevelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_water_level, parent, false); // Create a layout for individual items
        return new WaterLevelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterLevelViewHolder holder, int position) {
        WaterLevelData waterLevel = waterLevelList.get(position);
        holder.dateTextView.setText(waterLevel.getDate());
        holder.timeTextView.setText(waterLevel.getTime());
        holder.valueTextView.setText(String.valueOf(waterLevel.getValue()));
    }

    @Override
    public int getItemCount() {
        return waterLevelList.size();
    }

    public static class WaterLevelViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView timeTextView;
        TextView valueTextView;

        public WaterLevelViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }
}
