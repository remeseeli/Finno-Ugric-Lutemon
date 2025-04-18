package com.palinka.finno_ugric_lutemon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.palinka.finno_ugric_lutemon.Lutemon;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LutemonSelectorActivity extends AppCompatActivity {
    private Storage storage;
    private ListView lutemonListView;
    private Button selectButton;
    private int selectedLutemonId = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lutemon_selector);

        // Initialize views
        lutemonListView = findViewById(R.id.lutemonListView);
        selectButton = findViewById(R.id.selectButton);

        // Get storage singleton
        storage = Storage.getInstance();

        // Populate list with Lutemons
        populateLutemonList();

        // Set click listener for list items
        lutemonListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected Lutemon ID
                selectedLutemonId = (int) parent.getItemAtPosition(position);
                // Highlight the selection visually
                lutemonListView.setItemChecked(position, true);
            }
        });

        // Set click listener for select button
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedLutemonId != -1) {
                    // Start battle activity with selected Lutemon
                    Intent intent = new Intent(LutemonSelectorActivity.this, BattleActivity.class);
                    intent.putExtra("LUTEMON_ID", selectedLutemonId);
                    startActivity(intent);
                } else {
                    Toast.makeText(LutemonSelectorActivity.this,
                            "Please select a Lutemon first", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void populateLutemonList() {
        HashMap<Integer, Lutemon> lutemons = storage.getLutemonMap();

        if (lutemons.isEmpty()) {
            Toast.makeText(this, "No Lutemons available. Create some first!",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        // Create list of Lutemon IDs
        List<Integer> lutemonIds = new ArrayList<>(lutemons.keySet());

        // Create adapter that displays Lutemon name and level
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
                this,
                android.R.layout.simple_list_item_activated_1,
                lutemonIds) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                int lutemonId = getItem(position);
                Lutemon lutemon = storage.getLutemon(lutemonId);
                ((android.widget.TextView) view).setText(
                        lutemon.getName() + " (Level: " + lutemon.getLevel() +
                                ", HP: " + lutemon.getHealth() + "/" + lutemon.getMaxHealth() + ")");
                return view;
            }
        };

        lutemonListView.setAdapter(adapter);
    }
}