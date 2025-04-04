package com.palinka.finno_ugric_lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


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
            textViewName = itemView.findViewById(R.id.textView);
            textViewColor = itemView.findViewById(R.id.textView2);
            textViewLevel = itemView.findViewById(R.id.textView3);
            textViewMaxHealth = itemView.findViewById(R.id.textView4);
            textViewTestosterone = itemView.findViewById(R.id.textView5);
            textViewAttack = itemView.findViewById(R.id.textView6);
            textViewDefense = itemView.findViewById(R.id.textView7);
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
        holder.textViewLevel.setText("lvl: " + lutemon.getLevel());
        holder.textViewMaxHealth.setText("HP: " + lutemon.getMaxHealth());
        holder.textViewTestosterone.setText("T: " + lutemon.getTestosterone());
        holder.textViewAttack.setText("Atk: " + lutemon.getAttack());
        holder.textViewDefense.setText("Def: " + lutemon.getDefense());


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
