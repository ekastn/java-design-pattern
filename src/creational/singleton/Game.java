package creational.singleton;

import creational.builder.HeroBuilder;
import character.*;
import behavioral.strategy.*;
import dungeon.*;
import structural.composite.Room;
import structural.composite.RoomObject;

import java.util.Iterator;
import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private Hero player;
    private Dungeon dungeon;
    private static Game instance;

    private Game() {
        this.scanner = new Scanner(System.in);
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void start() {
        clearConsole();
        System.out.println("Welcome to The Dungeon Adventure!");
        System.out.println("Your only goal is to survive!");

        chooseCharacter();
        createDungeon(5);
        exploreDungeon();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        clearConsole();
    }

    private void chooseCharacter() {
        System.out.println("\nChoose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Assassin");
        System.out.println("4. Tank");
        System.out.print("\nEnter your choice (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // clear newline

        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        HeroBuilder heroBuilder = new HeroBuilder().setName(name);

        switch (choice) {
            case 1 -> heroBuilder
                    .setHealth(120)
                    .setAttack(25)
                    .setDefense(20)
                    .setAttackStrategy(new MeleeAttack());
            case 2 -> heroBuilder
                    .setHealth(80)
                    .setAttack(30)
                    .setDefense(10)
                    .setAttackStrategy(new MagicAttack());
            case 3 -> heroBuilder
                    .setHealth(90)
                    .setAttack(35)
                    .setDefense(15)
                    .setAttackStrategy(new StealthAttack());
            case 4 -> heroBuilder
                    .setHealth(150)
                    .setAttack(15)
                    .setDefense(30)
                    .setAttackStrategy(new MeleeAttack());
            default -> {
                System.out.println("Invalid choice. Defaulting to Warrior.");
                heroBuilder
                    .setHealth(120)
                    .setAttack(25)
                    .setDefense(20)
                    .setAttackStrategy(new MeleeAttack());
            }
        }

        player = heroBuilder.build();
    }

    private void createDungeon(int roomCount) {
        dungeon = new Dungeon(roomCount);
    }

    private void exploreDungeon() {
        Iterator<Room> iterator = dungeon.iterator();
        while (iterator.hasNext()) {
            clearConsole();
            Room room = iterator.next();
            System.out.print("========= ");
            System.out.print(room.getDescription());
            System.out.print(" of ");
            System.out.print(dungeon.getRoomCount());
            System.out.print(" =========");
            System.out.println();

            for (RoomObject obj : room.getObjects()) {
                if (obj instanceof Enemy enemy) {
                    System.out.println("You encountered an enemy: " + enemy.getName() + "!");
                    System.out.println("Ready to batte!");

                    try {
                        Thread.sleep(1000);
                        handleCombat(enemy);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (!player.isAlive()) {
                        System.out.println("Game over!");
                        return;
                    } else {
                        System.out.println("You defeated " + enemy.getName() + "!");
                    }
                } else {
                    obj.interact(player);
                }
            }

            if (room.getDescription().contains("Jalan keluar")) {
                System.out.println("\n\n");
                System.out.println("🎉 Congratulations! You escaped the dungeon!");
                System.out.println("\n\n");
                return;
            }

            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }

        System.out.println("You have explored the entire dungeon!");
    }

    private void handleCombat(Enemy enemy) throws InterruptedException {
        while (enemy.isAlive() && player.isAlive()) {
            clearConsole();
            player.info();

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Use Potion");
            System.out.print("Enter your choice (1-2): ");
            int action = scanner.nextInt();

            System.out.println();
            Thread.sleep(500);

            switch (action) {
                case 1 -> player.performAttack(enemy);
                case 2 -> player.usePotion();
                default -> {
                    System.out.println("Invalid choice. Attacking by default.");
                    player.performAttack(enemy);
                }
            }

            Thread.sleep(500);

            if (enemy.isAlive()) {
                enemy.performAttack(player);
            }

            Thread.sleep(1000);
        }
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("Failed to clear the console.");
            e.printStackTrace();
        }
    }
}
