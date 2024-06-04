package TP_Finals;

import java.util.Random;
import java.util.Scanner;

public class Game extends Thread {
    private static Player player;
    private Enemies enemy;
    private Scanner scan;
    private Random random;

    public Game() {
        // Initial stats of the player
        player = new Player("Traveler", 100, 20, 0, 1);
        scan = new Scanner(System.in);
        random = new Random();
    }

    public void start() {
        GameUtils.greetings();

        // Game loop
        while (player.isAlive()) {
            enemy = Enemies.RandomEnemies();
            System.out.println("\nA wild " + enemy.getName() + " appears!");
            GameUtils.eep(0.3);

            // Encounter loop
            while (player.isAlive() && enemy.isAlive()) {
                GameUtils.displayStats(player, enemy);
                GameUtils.displayChoices();

                // Asking the move of the player
                System.out.print("\nYour move: ");
                String choice = scan.nextLine();
                GameUtils.clearConsole();

                boolean successfullyRanAway = false;
                switch (choice.toLowerCase()) {
                    case "1":
                        player.attack(enemy);
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        }
                        break;
                    case "2":
                        player.heal();
                        if (enemy.isAlive()) {
                            enemy.attack(player);
                        }
                        break;
                    case "3":
                        if (GameUtils.attemptRun()) {
                            System.out.println("You successfully ran away!");
                            GameUtils.eep(0.5);
                            successfullyRanAway = true;
                        } else {
                            System.out.println("Failed to run away! The enemy attacks!");
                            enemy.attack(player);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice! Enemy attacks you!");
                        enemy.attack(player);
                }

                // If player successfully runs away, break out of the encounter loop
                if (successfullyRanAway) {
                    break;
                }
            }

            // If player dies
            if (!player.isAlive()) {
                GameUtils.death(enemy);
                break;
            } else if (!enemy.isAlive()) {
                // If player kills the enemy
                GameUtils.kill(enemy);
                GameUtils.coinUpdate(player);
            }

            GameUtils.displayChoices2();

            // New game loop
            String options = scan.nextLine();
            switch (options.toLowerCase()) {
                case "1":
                    // New fight/hunt
                    System.out.println("Do you want to start the first level?");
                    String newFight = scan.nextLine();
                    if (newFight.equalsIgnoreCase("yes")) {
                        LevelOne levelOne = new LevelOne(player);
                        levelOne.start();
                        try {
                            levelOne.join();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println("Returning to main menu.");
                    }
                    break;
                case "2":
                    // Visiting the store
                    System.out.println("Do you want to visit the store? Yes / No");
                    String visitStore = scan.nextLine();
                    if (visitStore.equalsIgnoreCase("yes")) {
                        GameUtils.visitStore(player);
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Returning to main menu.");
            }
        }

        if (player.isAlive()) {
            System.out.println("You leave the battle victorious!");
        } else {
            System.out.println("Game is over!");
        }
    }
}
