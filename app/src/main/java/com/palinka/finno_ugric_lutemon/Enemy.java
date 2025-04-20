package com.palinka.finno_ugric_lutemon;

public class Enemy {
    String name;
    int attack;
    int defense;
    int level;
    public int health;
    int maxHealth;
    //Constructor
    public Enemy(String name,  int attack, int defense, int level, int health, int maxHealth) {
        this.name = name;
        this.attack = attack;
        this.defense = defense;
        this.level =level;
        this.health = health;
        this.maxHealth = maxHealth;
    }
    public int attack(Lutemon player) {
        int baseDamage = (this.attack + this.level) - player.defense; // the base damage scales with the levels progressed in the game
        int randomFactor = (int)(Math.random() * 4); //This will give us a random extra damage between 0 and 3 (hopefully as well)
        int damage = baseDamage + randomFactor;


        if(Math.random()<0.05)  {
            // Critical hit
            damage *= 2;

        }

            // Normal hit
            return Math.max(1, damage);
    }

    public String getName() {
        return this.name;
    }
    public int getLevel() { return this.level; }
    public int getHealth() { return this.health; }
    public int getMaxHealth() { return this.maxHealth;}
    public int setHealth(int damage) { return this.health -= damage;  }
}
