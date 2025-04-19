package com.palinka.finno_ugric_lutemon;

import android.os.SystemClock;

public class BattleField {
    public interface BattleCallback {
        void onTurnUpdate(String log, int playerHP, int enemyHP);
        void onBattleEnd(String result);
    }
    private static final long TURN_DELAY = 1000; // 1 second between turns
    /*
     * This method simulates a battle between a player's lutemon and an enemy
     */
    public void fight(Lutemon player, Enemy enemy, BattleCallback callback) {
        // Turn start is decided by coin flip (50/50 chance)
        boolean playerStarts = Math.random() < 0.5;
        boolean isPlayerTurn = playerStarts;

        callback.onTurnUpdate((playerStarts ? "You won the coin flip! You start!" : "Enemy won the coin flip! Enemy starts!"), player.health, enemy.getHealth());
        // Battle loop
        // The battle continues until either the player or the enemy runs out of health
        while (player.health > 0 && enemy.getHealth() > 0) {
            // String log is used for logging the battle events
            String log;
            if (isPlayerTurn) {
                int damage = player.attack(enemy);
                enemy.health = Math.max(0, enemy.getHealth() - damage);
                log = player.getName() + " attacks for " + damage + " damage!";
            } else {
                int damage = enemy.attack(player);
                player.health = Math.max(0, player.health - damage);
                log = "Enemy attacks for " + damage + " damage!";
            }
            // Update the UI with the current health of both player and enemy
            callback.onTurnUpdate(log, player.health, enemy.getHealth());
            isPlayerTurn = !isPlayerTurn;

            // Add delay between turns to slow down the battle
            SystemClock.sleep(TURN_DELAY);
        }

        // Add a longer delay before showing the final result
        SystemClock.sleep(TURN_DELAY);
        // Check if the player or enemy has lost all health
        String result = (player.health <= 0)
                ? "💀 You lost the battle!\n" + player.getName() + " is healing back to full health, try again later!"
                : "🎉 You won the battle!\n" + player.getName() + " gained " + enemy.getLevel() * 10 + " XP!";
        callback.onBattleEnd(result);
        player.setHealth(player.maxHealth); // Heal player after battle
        player.gainXP(enemy.getLevel() * 10); // Gain XP based on enemy level

        //Update statistics
        player.incrementBattles();
        if (player.health <= 0) {
            player.incrementLosses();
        } else {
            player.incrementWins();
        }
    }
}