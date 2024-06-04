package TP_Finals;

import java.util.Scanner;

public class LevelOne extends Thread {
    private static Player player;
    private Scanner scan;

    public LevelOne(Player player) {
        this.player = player;
        this.scan = new Scanner(System.in);
    }

    @Override
    public void run() {
        System.out.println("Welcome to Level 1! You must defeat 5 enemies to complete this level.");
        int enemiesDefeated = 0;

        while (enemiesDefeated < 5 && player.isAlive()) {
            Enemies enemy = Enemies.RandomEnemies();
            System.out.println("\nA wild " + enemy.getName() + " appears!");
            GameUtils.eep(0.3);

            while (player.isAlive() && enemy.isAlive()) {
                GameUtils.displayStats(player, enemy);
                GameUtils.displayChoices();

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

                if (successfullyRanAway) {
                    break;
                }
            }

            if (!player.isAlive()) {
                GameUtils.death(enemy);
                break;
            } else if (!enemy.isAlive()) {
                GameUtils.kill(enemy);
                GameUtils.coinUpdate(player);
                enemiesDefeated++;
                System.out.println("You have defeated " + enemiesDefeated + " out of 5 enemies.");
            }

            if (enemiesDefeated < 5 && player.isAlive()) {
                GameUtils.displayChoices2();

                String options = scan.nextLine();
                switch (options.toLowerCase()) {
                    case "1":
                        System.out.println("Do you want to fight another monster? Yes / No");
                        String newFight = scan.nextLine();
                        if (!newFight.equalsIgnoreCase("yes")) {
                            return; // End level if player does not want to fight
                        }
                        break;
                    case "2":
                        System.out.println("Do you want to visit the store? Yes / No");
                        String visitStore = scan.nextLine();
                        if (visitStore.equalsIgnoreCase("yes")) {
                            GameUtils.visitStore(player);
                        }
                        break;
                    default:
                        System.out.println("Invalid choice! Returning to main menu.");
                        return;
                }
            }
        }

        if (player.isAlive() && enemiesDefeated == 5) {
            System.out.println("Congratulations! You have completed Level 1!");
        } else {
            System.out.println("Game is over!");
        }
    }
}
