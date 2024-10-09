package com.example.catfishpondmonitoring;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class AcidAdapter extends RecyclerView.Adapter<AcidAdapter.AcidViewHolder> {

    private List<AcidData> acidDataList;

    public AcidAdapter(List<AcidData> acidDataList) {
        this.acidDataList = acidDataList;
    }

    @NonNull
    @Override
    public AcidViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_acid, parent, false);
        return new AcidViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AcidViewHolder holder, int position) {
        AcidData acidData = acidDataList.get(position);
        holder.dateTextView.setText(acidData.getDate());
        holder.timeTextView.setText(acidData.getTime());
        holder.acidLevelTextView.setText(String.valueOf(acidData.getAcidLevel()));
    }

    @Override
    public int getItemCount() {
        return acidDataList.size();
    }

    static class AcidViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView;
        TextView timeTextView;
        TextView acidLevelTextView;

        AcidViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.acidDateTextView);
            timeTextView = itemView.findViewById(R.id.acidTimeTextView);
            acidLevelTextView = itemView.findViewById(R.id.acidLevelTextView);
        }
    }
}
