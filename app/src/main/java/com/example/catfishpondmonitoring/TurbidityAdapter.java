package com.example.catfishpondmonitoring;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TurbidityAdapter extends RecyclerView.Adapter<TurbidityAdapter.ViewHolder> {

    private List<TurbidityData> turbidityDataList;

    public TurbidityAdapter(List<TurbidityData> turbidityDataList) {
        this.turbidityDataList = turbidityDataList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_turbidity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TurbidityData data = turbidityDataList.get(position);
        holder.dateTextView.setText(data.getDate());
        holder.timeTextView.setText(data.getTime());
        holder.valueTextView.setText(String.valueOf(data.getValue()));
    }

    @Override
    public int getItemCount() {
        return turbidityDataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView timeTextView;
        TextView valueTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            timeTextView = itemView.findViewById(R.id.timeTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
        }
    }
}
