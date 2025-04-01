package com.palinka.finno_ugric_lutemon;

public class BattleField {
    public void fight() {
        /*
        This is the method that handles the fighting.
        I will be testing the fight here -eeli
        */
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
    }
}
