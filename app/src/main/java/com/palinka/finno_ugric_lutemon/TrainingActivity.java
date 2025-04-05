package com.palinka.finno_ugric_lutemon;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Activity for training Lutemons.
 * @author Marton
 */
public class TrainingActivity extends AppCompatActivity {
    private Button testTrainButton;
    private Spinner trainSpinner;
    private HashMap<Integer, Lutemon> lutemonMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        testTrainButton = findViewById(R.id.testTrainButton);
        trainSpinner = findViewById(R.id.trainSpinner);

        // Initialize the Storage instance and get the lutemon map

        Storage storage = Storage.getInstance();
        lutemonMap = storage.getLutemonMap();
        // Convert HashMap values to a list
        List<Lutemon> lutemonList = new ArrayList<>(lutemonMap.values());

        // Create an ArrayAdapter with the list
        ArrayAdapter<Lutemon> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lutemonList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the adapter to the Spinner
        trainSpinner.setAdapter(adapter);
    }

    TrainingArea trainingArea = new TrainingArea();
    /**
     * Train the lutemon (still not fully implemented)
     * @param view
     */
    public void trainLutemon(View view){
        Lutemon selectedLutemon = (Lutemon) trainSpinner.getSelectedItem();
        if (selectedLutemon != null) {
            try {
                trainingArea.train(selectedLutemon);
                Toast.makeText(this, "Training finished, " + selectedLutemon.getName() + "gained 10 XP", Toast.LENGTH_SHORT).show();
            } catch (InterruptedException e) {
                System.out.println("Training interrupted.");
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }else {
            Toast.makeText(this, "Please select a Lutemon to train.", Toast.LENGTH_LONG).show();
        }
    }
}
