package com.palinka.finno_ugric_lutemon;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button testButton, createButton, recyclerButton;
    private EditText nameInput;
    Storage storage = Storage.getInstance();
    Home home = new Home();
    String[] lutemonTypes = {"Black", "Green", "Pink", "White", "Orange"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        testButton = findViewById(R.id.eeliButton);
        nameInput = findViewById(R.id.testInput);
        createButton = findViewById(R.id.createLutemonButton);
        //Drop-down menu spinner methods
        Spinner typeSpinner = findViewById(R.id.lutemonTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lutemonTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        // end of drop-down menu

        //Bator: created a button that brings us to the next page, that will contain the recycler view
        recyclerButton = findViewById(R.id.recyclerButton);


        //set up the on click listener
        recyclerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
        //End the testing (Bator)


        // CREATING A LUTEMON BUTTON
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String selectedType = typeSpinner.getSelectedItem().toString();
                String name = nameInput.getText().toString();
                home.createLutemon(selectedType, name);
                Toast.makeText(MainActivity.this, "Lutemon Created", Toast.LENGTH_SHORT).show();
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleField battle = new BattleField();
                battle.fight();

            }
        });
    }

    //method to go to the training activity
    public void trainTest(View view){
        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }
}