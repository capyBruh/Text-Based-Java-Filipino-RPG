package TP_Finals;

public class Player {
    private String name;
    private int health;
    private int attackPower;
    private int coins;
    private int defense;
    private int potions;

    public Player(String name, int health, int attackPower, int defense, int potions) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
        this.coins = 0;
        this.defense = defense;
        this.potions = potions;
    }

    public int getHP() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Enemies enemy) {
        eep(2);
        System.out.println();
        System.out.print(name + " attacks the " + enemy.getName() + "! ");
        enemy.takeDamage(attackPower);
        eep(2);
    }

    public void heal() {
        if (potions > 0) {
            int healAmount = 20;
            health += healAmount;
            potions--;
            eep(2);
            System.out.println();
            System.out.println(name + " heals for " + healAmount + " HP! Potions left: " + potions);
            eep(2);
        } else {
            eep(2);
            System.out.println();
            System.out.println("No potions left to heal!");
            eep(2);
        }
    }

    public void takeDamage(int damage) {
        int actualDamage = Math.max(0, damage - defense);
        health -= actualDamage;
        eep(2);
        System.out.println(name + " takes " + actualDamage + " damage!");
        eep(2);
    }

    public void earn(int coins) {
        this.coins += coins;
        eep(2);
    }

    public int getCoins() {
        return coins;
    }

    public boolean purchaseHeal() {
        int potionCost = 25;
        if (coins >= potionCost) {
            coins -= potionCost;
            potions++;
            eep(2);
            System.out.println();
            System.out.println(name + " purchases a potion! Total potions: " + potions);
            eep(2);
            return true;
        } else {
            eep(2);
            System.out.println();
            System.out.println("Not enough coins to purchase a potion!");
            eep(2);
            return false;
        }
    }

    // delay by seconds
    public int eep(int eepy) {
        eepy *= 100;
        try {
            Thread.sleep(eepy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return eepy;
    }
}
