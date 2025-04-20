package com.palinka.finno_ugric_lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Adapter class for displaying Lutemon objects in a RecyclerView.
 */
public class LutemonAdapter extends RecyclerView.Adapter<LutemonAdapter.LutemonViewHolder> {

    private ArrayList<Lutemon> lutemonList;

    public LutemonAdapter(ArrayList<Lutemon> lutemonList) {
        this.lutemonList = lutemonList;
    }


    public static class LutemonViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textViewName, textViewColor, textViewLevel, textViewMaxHealth,
                textViewTestosterone, textViewAttack, textViewDefense;



        public LutemonViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textViewName = itemView.findViewById(R.id.textViewStat);
            textViewColor = itemView.findViewById(R.id.textViewStat2);
            textViewLevel = itemView.findViewById(R.id.textView3);
            textViewMaxHealth = itemView.findViewById(R.id.textView4);
            textViewTestosterone = itemView.findViewById(R.id.textView5);
            textViewAttack = itemView.findViewById(R.id.textViewStat3);
            textViewDefense = itemView.findViewById(R.id.textViewStat4);
        }
    }

    @NonNull
    @Override
    public LutemonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new LutemonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);

        holder.textViewName.setText(lutemon.getName());
        holder.textViewColor.setText(lutemon.getColor());
        holder.textViewLevel.setText("Lvl: " + lutemon.getLevel());
        holder.textViewMaxHealth.setText("HP: " + lutemon.getMaxHealth());
        holder.textViewTestosterone.setText("\uD83D\uDCAA: " + lutemon.getTestosterone());
        holder.textViewAttack.setText("Atk: " + lutemon.getAttack());
        holder.textViewDefense.setText("Def: " + lutemon.getDefense());
        switch (lutemon.getColor().toLowerCase()) {
            case "black":
                holder.imageView.setImageResource(R.drawable.lutemon_black);
                break;
            case "green":
                holder.imageView.setImageResource(R.drawable.lutemon_green);
                break;
            case "white":
                holder.imageView.setImageResource(R.drawable.lutemon_white);
                break;
            case "orange":
                holder.imageView.setImageResource(R.drawable.lutemon_orange);
                break;
            case "pink":
                holder.imageView.setImageResource(R.drawable.lutemon_pink);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_launcher_foreground); // fallback image
                break;
        }


    }

    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    //This method is for the case if a new object has been added to the list, it refreshes the list and the recycler view will show the new one as well.
    public void updateData(ArrayList<Lutemon> newLutemonList) {
        lutemonList = newLutemonList;
        notifyDataSetChanged();
    }
}
