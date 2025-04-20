package com.palinka.finno_ugric_lutemon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ImageView;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;

import com.palinka.finno_ugric_lutemon.Lutemon;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.Storage;
import com.palinka.finno_ugric_lutemon.TrainingArea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import android.util.Log;

/**
 * Activity for training Lutemons.
 */
public class TrainingActivity extends AppCompatActivity {
    private Button trainButton;
    private Spinner trainSpinner;
    private HashMap<Integer, Lutemon> lutemonMap;
    private ImageView imageSpecificLutemon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);
        trainSpinner = findViewById(R.id.selectionSpinner);
        trainButton = findViewById(R.id.trainButton);
        imageSpecificLutemon = findViewById(R.id.imageSpecificLutemon);

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
        trainSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Lutemon selectedLutemon = (Lutemon) parent.getItemAtPosition(position);

                int imageRes = R.drawable.ic_launcher_foreground;
                switch (selectedLutemon.getColor()) {
                    case "Black":
                        imageRes = R.drawable.black_train;
                        break;
                    case "White":
                        imageRes = R.drawable.white_train;
                        break;
                    case "Green":
                        imageRes = R.drawable.green_train;
                        break;
                    case "Pink":
                        imageRes = R.drawable.pink_train;
                        break;
                    case "Orange":
                        imageRes = R.drawable.orange_train;
                        break;
                }
                imageSpecificLutemon.setImageResource(imageRes);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Optional: Set a default image or clear the ImageView
                imageSpecificLutemon.setImageResource(R.drawable.ic_launcher_foreground);
            }
        });

    }

    TrainingArea trainingArea = new TrainingArea();
    /**
     * Train the selected Lutemon with countdown timer
     * @param view
     */
    public void trainLutemon(View view){

        Lutemon selectedLutemon = (Lutemon) trainSpinner.getSelectedItem();
        if (selectedLutemon != null) {
            //check if the 5 sec has passed
            long currentTime = System.currentTimeMillis();
            long lastTrainingTime = selectedLutemon.getLastTrainingTime();
            if (lastTrainingTime == 0 || currentTime - lastTrainingTime >= 5000) {
                // Training is allowed
                trainButton.setEnabled(false);
                new CountDownTimer(10000, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        trainButton.setText((millisUntilFinished / 1000) + " seconds remaining");
                    }

                    @Override
                    public void onFinish() {
                        trainingArea.train(selectedLutemon, TrainingActivity.this);
                        trainButton.setEnabled(true);
                        trainButton.setText("Train");
                        Toast.makeText(TrainingActivity.this, selectedLutemon.getName() + " gained 10 experience points.", Toast.LENGTH_LONG).show();
                    }
                }.start();
                selectedLutemon.setLastTrainingTime(currentTime);
                lutemonMap.put(selectedLutemon.getId(), selectedLutemon);
            } else {
                // Cooldown is still active
                long secondsLeft = (5000 - (currentTime - lastTrainingTime)) / 1000;
                Toast.makeText(this, "You can train " + selectedLutemon.getName() + " again in " + secondsLeft + " seconds.", Toast.LENGTH_LONG).show();
            }
        } else  {
            // No Lutemon selected
            Toast.makeText(this, "Please select a Lutemon to train.", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Go to the Lutemon RecyclerActivity
     * @param view
     */
    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
