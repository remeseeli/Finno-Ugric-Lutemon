package com.palinka.finno_ugric_lutemon.activities;

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

import com.palinka.finno_ugric_lutemon.Home;
import com.palinka.finno_ugric_lutemon.LutemonFileHandler;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.Storage;

public class MainActivity extends AppCompatActivity {
    private Button createButton, recyclerButton, battleMenuButton, saveButton, loadButton, clearButton;
    private EditText nameInput;
    Storage storage = Storage.getInstance();
    Home home = new Home();
    LutemonFileHandler filehandler = new LutemonFileHandler();
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
        nameInput = findViewById(R.id.testInput);
        createButton = findViewById(R.id.createLutemonButton);
        battleMenuButton = findViewById(R.id.battleMenuButton);
        //Drop-down menu spinner methods
        Spinner typeSpinner = findViewById(R.id.lutemonTypeSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, lutemonTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(adapter);
        // end of drop-down menu

        //Bator: created a button that brings us to the next page, that will contain the recycler view
        recyclerButton = findViewById(R.id.recyclerButton);
        saveButton = findViewById(R.id.saveButton);
        loadButton = findViewById(R.id.loadButton);
        clearButton = findViewById(R.id.clearButton);


        //set up the on click listener
        recyclerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecyclerActivity.class);
                startActivity(intent);
            }
        });
        //End the testing (Bator)


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filehandler.saveToFile(MainActivity.this);
                Toast.makeText(MainActivity.this, "Your collection has been saved.", Toast.LENGTH_SHORT).show();
            }
        });

        loadButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               filehandler.loadFromFile(MainActivity.this);
               Toast.makeText(MainActivity.this, "Your collection has been loaded.", Toast.LENGTH_SHORT).show();
           }
        });


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

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filehandler.clearFile(MainActivity.this);
                Toast.makeText(MainActivity.this, "Your collection has been cleared.", Toast.LENGTH_SHORT).show();
            }
        });

        //method to go to the battle activity
        battleMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LutemonSelectorActivity.class);
                startActivity(intent);
            }
        });
    }

    //method to go to the training activity
    public void trainingGround(View view) {
        Intent intent = new Intent(MainActivity.this, TrainingActivity.class);
        startActivity(intent);
    }


}






