package TP_Finals;

import java.util.Random;

public class Enemies {
    private String name;
    private int health;
    private int attackPower;

    public Enemies(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    public String getName() {
        return name;
    }

    public int getHP() {
        return health;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public void attack(Player player) {
        eep(0.2);
        System.out.println();
        System.out.print(name + " attacks the player! ");
        player.takeDamage(attackPower);
        eep(0.2);
    }

    public void takeDamage(int damage) {
        health -= damage;
        eep(0.2);
        System.out.println(name + " takes " + damage + " damage!");
        eep(0.2);
    }

    public static Enemies RandomEnemies() {
        Random random = new Random();
        int type = random.nextInt(3);
        switch (type) {
            case 0:
                return new Enemies("Goblin", 80, 15);
            case 1:
                return new Enemies("Orc", 120, 20);
            case 2:
                return new Enemies("Troll", 150, 25);
            default:
                return new Enemies("Goblin", 80, 15);
        }
    }

    public double eep(double eepy) {
        try {
            Thread.sleep((long) eepy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return eepy;
    }
}