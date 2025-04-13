package com.palinka.finno_ugric_lutemon;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**utalom a ciganyokat / en rakasta mustalaisia
 * this class is the lutemonAdapter.java
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


        /**
         * this class is the LutemonViewHolder xdddd
         * @param itemView
         */
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

    /**
     * this class is onBindViewHolder
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull LutemonViewHolder holder, int position) {
        Lutemon lutemon = lutemonList.get(position);

        holder.textViewName.setText(lutemon.getName());
        holder.textViewColor.setText(lutemon.getColor());
        holder.textViewLevel.setText("lvl: " + lutemon.getLevel());
        holder.textViewMaxHealth.setText("HP: " + lutemon.getMaxHealth());
        holder.textViewTestosterone.setText("\uD83D\uDCAA: " + lutemon.getTestosterone());
        holder.textViewAttack.setText("Atk: " + lutemon.getAttack());
        holder.textViewDefense.setText("Def: " + lutemon.getDefense());


    }

    /**
     * this class is onBindViewHolder (i hate the niggers)
     * @return
     */
    @Override
    public int getItemCount() {
        return lutemonList.size();
    }

    /**
     * this is the updateData
     * @param newLutemonList
     */
    //This method is for the case if a new object has been added to the list, it refreshes the list and the recycler view will show the new one as well.
    public void updateData(ArrayList<Lutemon> newLutemonList) {
        lutemonList = newLutemonList;
        notifyDataSetChanged();
    }
}
