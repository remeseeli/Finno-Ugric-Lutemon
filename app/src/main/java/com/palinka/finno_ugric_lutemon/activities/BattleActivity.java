package com.palinka.finno_ugric_lutemon.activities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.palinka.finno_ugric_lutemon.BattleField;
import com.palinka.finno_ugric_lutemon.Enemy;
import com.palinka.finno_ugric_lutemon.Lutemon;
import com.palinka.finno_ugric_lutemon.R;
import com.palinka.finno_ugric_lutemon.Storage;

public class BattleActivity extends AppCompatActivity implements BattleField.BattleCallback {
    private Storage storage;
    private BattleField battleField;
    private Lutemon playerLutemon;
    private Enemy enemy;

    // UI Elements
    private TextView playerNameTextView;
    private TextView enemyNameTextView;
    private ProgressBar playerHealthBar;
    private ProgressBar enemyHealthBar;
    private TextView playerHealthTextView;
    private TextView enemyHealthTextView;
    private TextView battleLogTextView;
    private Button attackButton;
    private Button returnButton;

    private Handler handler = new Handler();
    private boolean battleInProgress = false;

    // Delay between battle turns in milliseconds - increase this to slow down the battle pace
    private final int TURN_DELAY = 2000; // 2 seconds between turns

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        // Initialize storage and battle field
        storage = Storage.getInstance();
        battleField = new BattleField();

        // Initialize UI elements
        playerNameTextView = findViewById(R.id.playerNameTextView);
        enemyNameTextView = findViewById(R.id.enemyNameTextView);
        playerHealthBar = findViewById(R.id.playerHealthBar);
        enemyHealthBar = findViewById(R.id.enemyHealthBar);
        playerHealthTextView = findViewById(R.id.playerHealthTextView);
        enemyHealthTextView = findViewById(R.id.enemyHealthTextView);
        battleLogTextView = findViewById(R.id.battleLogTextView);
        attackButton = findViewById(R.id.attackButton);
        returnButton = findViewById(R.id.returnButton);

        // Get selected Lutemon from intent
        int lutemonId = getIntent().getIntExtra("LUTEMON_ID", -1);
        if (lutemonId == -1) {
            finishWithError("No Lutemon selected");
            return;
        }

        playerLutemon = storage.getLutemon(lutemonId);
        if (playerLutemon == null) {
            finishWithError("Selected Lutemon not found");
            return;
        }

        // Generate enemy based on player's level
        enemy = storage.generateEnemy(storage.getLutemonMap());

        // Set up UI with initial data
        setupInitialUI();

        // Set click listeners
        attackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!battleInProgress) {
                    startBattle();
                }
            }
        });

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupInitialUI() {
        // Set names
        playerNameTextView.setText(playerLutemon.getName() + " (Level " + playerLutemon.getLevel() + ")");
        enemyNameTextView.setText(enemy.getName() + " (Level " + enemy.getLevel() + ")");

        // Set health bars max values and current values
        playerHealthBar.setMax(playerLutemon.getMaxHealth());
        playerHealthBar.setProgress(playerLutemon.getHealth());
        enemyHealthBar.setMax(enemy.getMaxHealth());
        enemyHealthBar.setProgress(enemy.getHealth());

        // Set health text
        updateHealthText();

        // Initial battle log
        battleLogTextView.setText("Battle is about to begin!\n" +
                playerLutemon.getName() + " VS " + enemy.getName());

        // Show attack button, hide return button initially
        attackButton.setVisibility(View.VISIBLE);
        returnButton.setVisibility(View.GONE);
    }

    private void updateHealthText() {
        playerHealthTextView.setText(playerLutemon.getHealth() + "/" + playerLutemon.getMaxHealth());
        enemyHealthTextView.setText(enemy.getHealth() + "/" + enemy.getMaxHealth());
    }

    private void startBattle() {
        battleInProgress = true;
        attackButton.setEnabled(false);
        battleLogTextView.setText("Battle started!\n");

        // Start the battle in a separate thread to not block the UI
        new Thread(new Runnable() {
            @Override
            public void run() {
                battleField.fight(playerLutemon, enemy, BattleActivity.this);
            }
        }).start();
    }

    @Override
    public void onTurnUpdate(final String log, final int playerHP, final int enemyHP) {
        // Update UI on the main thread with a delay for better visualization
        handler.post(new Runnable() {
            @Override
            public void run() {
                appendBattleLog(log);

                // Update health values
                playerLutemon.setHealth(playerHP);
                enemy.health = enemyHP;

                // Update health bars
                playerHealthBar.setProgress(playerHP);
                enemyHealthBar.setProgress(enemyHP);

                // Update health text
                updateHealthText();
            }
        });
    }

    @Override
    public void onBattleEnd(final String result) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                appendBattleLog("\n" + result);

                // Always heal player's Lutemon back to full health regardless of battle outcome
                playerLutemon.setHealth(playerLutemon.getMaxHealth());

                // Update the UI to reflect healing
                playerHealthBar.setProgress(playerLutemon.getHealth());
                updateHealthText();

                // Add a message about healing
                appendBattleLog(playerLutemon.getName() + " has been healed back to full health!");

                battleInProgress = false;
                attackButton.setVisibility(View.GONE);
                returnButton.setVisibility(View.VISIBLE);
            }
        });
    }

    private void appendBattleLog(String newLog) {
        String currentLog = battleLogTextView.getText().toString();
        // Keep the log from getting too long by limiting it
        if (currentLog.split("\n").length > 10) {
            // Remove the first line
            currentLog = currentLog.substring(currentLog.indexOf('\n') + 1);
        }
        battleLogTextView.setText(currentLog + "\n" + newLog);
    }

    private void finishWithError(String message) {
        // Display an error and finish the activity
        battleLogTextView.setText("Error: " + message);
        attackButton.setEnabled(false);
        returnButton.setVisibility(View.VISIBLE);
    }
}