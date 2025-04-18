package com.palinka.finno_ugric_lutemon;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StatisticAdapter extends RecyclerView.Adapter<StatisticAdapter.StatisticViewHolder> {

    private ArrayList<Lutemon> lutemonList;

    public StatisticAdapter(ArrayList<Lutemon> lutemonList) {
        this.lutemonList = lutemonList;
    }

    public static class StatisticViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName, textViewBattles, textViewWins, textViewLosses, textViewTrainings;

        public StatisticViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewStat);
            textViewBattles = itemView.findViewById(R.id.textViewStat2);
            textViewWins = itemView.findViewById(R.id.textViewStat3);
            textViewLosses = itemView.findViewById(R.id.textViewStat4);
            textViewTrainings = itemView.findViewById(R.id.textViewStat5);
            if (textViewName == null) {
                Log.e("StatisticViewHolder", "textViewName is null. Check item_statlayout.xml.");
            }
        }
    }

    @NonNull
    @Override
    public StatisticViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_statlayout, parent, false);
        return new StatisticViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatisticViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);

        holder.textViewName.setText(lutemon.getName());
        holder.textViewBattles.setText(String.valueOf("Battles: " + lutemon.getNumberOfBattles()));
        holder.textViewWins.setText(String.valueOf("Wins: " + lutemon.getNumberOfWins()));
        holder.textViewLosses.setText(String.valueOf("Losses: " + lutemon.getNumberOfLosses()));
        holder.textViewTrainings.setText(String.valueOf("Trainings: " + lutemon.getNumberOfTrainings()));
    }

    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    public void updateData(ArrayList<Lutemon> newLutemonList) {
        lutemonList = newLutemonList;
        notifyDataSetChanged();
    }
}

