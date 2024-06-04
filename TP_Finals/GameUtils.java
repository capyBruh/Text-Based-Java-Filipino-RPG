package TP_Finals;

import java.util.Random;
import java.util.Scanner;

public class GameUtils {
    private static Scanner scan = new Scanner(System.in);

    public static void greetings() {
        System.out.println("Greeting player! Let your journey start!");
        System.out.println(" 0\n/|\\\n/ \\");
        eep(3);
        clearConsole();
    }

    public static void death(Enemies enemy) {
        eep(0.5);
        System.out.println();
        System.out.println("You have been defeated by the: " + enemy.getName());
    }

    public static void kill(Enemies enemy) {
        eep(0.5);
        System.out.println();
        System.out.println("You have killed the " + enemy.getName());
    }

    public static void eep(double seconds) {
        try {
            Thread.sleep((long) (seconds * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void displayStats(Player player, Enemies enemy) {
        System.out.print(
                " 0                                0 \n/|\\                              /|\\\n/ \\                              / \\ ");
        eep(0.3);
        System.out.print("\nPlayer HP: " + player.getHP());
        eep(0.3);
        System.out.println("                   Enemy HP: " + enemy.getHP());
        eep(0.3);
        System.out.println("Player Coins: " + player.getCoins());
    }

    public static void displayChoices() {
        System.out.println("\nChoose an action:");
        eep(0.3);
        System.out.println("1 to atk -|==>");
        eep(0.3);
        System.out.println("2 to heal <3 ++");
        eep(0.3);
        System.out.println("3 to run ~~~~~");
    }

    public static void displayChoices2() {
        System.out.println();
        System.out.println("Now what would you like to do?");
        eep(0.3);
        System.out.println();
        System.out.println("Choose an action:");
        eep(0.3);
        System.out.println("1 to hunt -|==>");
        eep(0.3);
        System.out.println("2 to store <3 ++");
    }

    public static void visitStore(Player player) {
        System.out.println("Welcome to the store!");
        System.out.println("1. Purchase Heal (25 coins)");
        System.out.println("2. Exit Store");

        String storeChoice = scan.nextLine();
        switch (storeChoice) {
            case "1":
                player.purchaseHeal();
                break;
            case "2":
                System.out.println("Exiting store.");
                break;
            default:
                System.out.println("Invalid choice! Exiting store.");
        }
    }

    public static int getRandomCoins() {
        Random random = new Random();
        return 10 + random.nextInt(11);
    }

    public static void coinUpdate(Player player) {
        int coinsEarned = getRandomCoins();
        eep(2);
        System.out.println();
        System.out.println("You earned " + coinsEarned + " coins!");
        player.earn(coinsEarned);
    }

    public static boolean attemptRun() {
        Random random = new Random();
        int chance = random.nextInt(100);
        return chance < 60; // 60% chance to successfully run away
    }
}
