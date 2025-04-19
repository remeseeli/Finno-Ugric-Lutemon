package com.palinka.finno_ugric_lutemon.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.palinka.finno_ugric_lutemon.Lutemon;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.Storage;
import com.palinka.finno_ugric_lutemon.TrainingArea;

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
        testTrainButton = findViewById(R.id.trainButton);
        trainSpinner = findViewById(R.id.selectionSpinner);

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
    long lastTrainingTime = 0;
    /**
     * Train the lutemon (still not fully implemented)
     * @param view
     */
    public void trainLutemon(View view){

        Lutemon selectedLutemon = (Lutemon) trainSpinner.getSelectedItem();
        if (selectedLutemon != null) {
            //chech if the 1 hour has passed
            long currentTime = System.currentTimeMillis();
            if (currentTime - lastTrainingTime < 3600000) { // 1 hour in milliseconds
                Toast.makeText(this, "You can only train a Lutemon once every hour.", Toast.LENGTH_LONG).show();
                return;
            }
            new CountDownTimer(10000, 1000) { // 10 seconds, tick every 1 second
                @Override
                public void onTick(long millisUntilFinished) {
                    // Display the remaining time
                    Toast.makeText(TrainingActivity.this, "Remaining time: " + millisUntilFinished / 1000 + " seconds", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onFinish() {
                    // Training is complete
                    trainingArea.train(selectedLutemon, TrainingActivity.this);
                    //The training is finished, so we have to show a message somehow
                }
            }.start();
            lastTrainingTime = System.currentTimeMillis(); // Update the last training time
        }else {
            Toast.makeText(this, "Please select a Lutemon to train.", Toast.LENGTH_LONG).show();
        }
    }

    //Go to the main menu
    public void goToMainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class); // Replace MainMenuActivity with the actual class name of your main menu Activity
        startActivity(intent);
        finish(); // Optional: Call finish() if you want to close the current Activity
    }
}
