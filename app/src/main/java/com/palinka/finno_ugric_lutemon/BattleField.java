package com.palinka.finno_ugric_lutemon;

import android.os.SystemClock;

public class BattleField {
    public interface BattleCallback {
        void onTurnUpdate(String log, int playerHP, int enemyHP);
        void onBattleEnd(String result);
    }
    private static final long TURN_DELAY = 1000; // 2 seconds between turns
    public void fight(Lutemon player, Enemy enemy, BattleCallback callback) {
        boolean playerStarts = Math.random() < 0.5;
        boolean isPlayerTurn = playerStarts;

        callback.onTurnUpdate((playerStarts ? "You won the coin flip! You start!" : "Enemy won the coin flip! Enemy starts!"), player.health, enemy.getHealth());

        while (player.health > 0 && enemy.getHealth() > 0) {
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

            callback.onTurnUpdate(log, player.health, enemy.getHealth());
            isPlayerTurn = !isPlayerTurn;

            // Add delay between turns to slow down the battle
            SystemClock.sleep(TURN_DELAY);
        }

        // Add a longer delay before showing the final result
        SystemClock.sleep(TURN_DELAY);

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