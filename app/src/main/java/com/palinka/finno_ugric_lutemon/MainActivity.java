package com.palinka.finno_ugric_lutemon;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private Button testButton;
    private Button createButton;
    private EditText nameInput;
    Storage storage = new Storage();

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

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lutemon lutemon = new Black();
                lutemon.setName(String.valueOf(nameInput.getText()));
                storage.addLutemon(lutemon);
                Toast.makeText(MainActivity.this, "Lutemon Created", Toast.LENGTH_SHORT).show();
            }
        });
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BattleField battle = new BattleField();

            }
        });
    }

    public void eeliTesting(View view) {
        try {
            Lutemon testGuy = new Black();
            testGuy.setName("Good guy");
            Lutemon testEnemy = new Black();
            testEnemy.setName("Bad guy");
            while (true) {
                testGuy.health = testGuy.health - testEnemy.attack;
                if(testGuy.health <= 0) {
                    System.out.println(testGuy.getName() + "died. Battle lost.");
                    break;
                }
                System.out.println(testGuy.getName() + "is hit, has " + testGuy.health + " HP left.");

                testEnemy.health = testEnemy.health - testGuy.attack;
                if(testEnemy.health <= 0) {
                    System.out.println(testEnemy.getName() + " died. Battle won.");
                    break;
                }
                System.out.println(testEnemy.getName() + "is hit, has " + testGuy.health + "HP left.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    } // end of function
}