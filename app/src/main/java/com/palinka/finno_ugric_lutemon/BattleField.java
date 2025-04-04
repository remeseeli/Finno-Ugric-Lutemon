package com.palinka.finno_ugric_lutemon;

public class BattleField {
    boolean playerStarts = coinFlip();
    Storage storage = new Storage();
    /**
     * This is the method that handles the fighting.
     * I will be testing the fight here -eeli
     */
    public void fight() {
        Enemy enemy = storage.generateEnemy(storage.getLutemonMap());
        Lutemon player = storage.getLutemon(0);
        if (player == null) {
            System.out.println("Error: No Lutemon found for the player!");
            return;
        }
        if (enemy == null) {
            System.out.println("Error: Failed to generate an enemy!");
            return;
        }

        System.out.println("The battle starts. Flipping a coin...");
        boolean playerStarts = coinFlip(); // Determine who starts
        boolean isPlayerTurn = playerStarts; // Keep track of turns correctly

        // Announce the coin flip result
        System.out.println(playerStarts ? "You won the coin flip! You attack first."
                : "You lost the coin flip! Enemy attacks first.");

        // Battle loop
        while (player.health > 0 && enemy.health > 0) {
            System.out.println("\nYour HP: " + player.health + " | Enemy HP: " + enemy.health);

            if (isPlayerTurn) {
                int damage = player.attack(enemy);
                enemy.health -= damage;
                System.out.println("You attack and deal " + damage + " damage!");
            } else {
                int damage = enemy.attack(player);
                player.health -= damage;
                System.out.println("Enemy attacks and deals " + damage + " damage!");
            }

            isPlayerTurn = !isPlayerTurn; // 🔹 Correct: Toggle turns AFTER full action

            // Small delay for readability (optional)
            try {
                Thread.sleep(500); }
            catch (InterruptedException e) {
                e.printStackTrace(); }
        }

        // Announce winner
        System.out.println(player.health <= 0 ? "\n⚔️ You lost! The enemy defeated you!"
                : "\n🎉 Victory! You defeated the enemy !");
        }
    private boolean coinFlip() {
        return Math.random() < 0.5; // 50% chance (true = player, false = enemy)
    }
}
