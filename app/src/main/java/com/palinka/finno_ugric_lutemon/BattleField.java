package com.palinka.finno_ugric_lutemon;

public class BattleField {
    /**
     * This is the method that handles the fighting.
     * I will be testing the fight here -eeli
     */
    public void fight() {
        Storage storage = new Storage();
        storage.generateEnemy(storage.getLutemonMap());
        storage.getLutemon(1);

        try {
            while (true) {
                System.out.println("The battle starts. ");
                Thread.sleep(100);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
