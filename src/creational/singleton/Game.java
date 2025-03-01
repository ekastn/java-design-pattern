package creational.singleton;

import creational.builder.HeroBuilder;
import core.*;
import behavioral.strategy.*;
import manager.CombatManager;
import structural.composite.Room;
import structural.composite.RoomObject;
import utils.ConsoleUtils;

import java.util.Iterator;
import java.util.Scanner;

public class Game {
    private final Scanner scanner;
    private Hero player;
    private Dungeon dungeon;
    private CombatManager combatManager;
    private static Game instance;

    private Game() {
        this.scanner = new Scanner(System.in);
        this.combatManager = new CombatManager();
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void start() {
        ConsoleUtils.clearConsole();
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

        ConsoleUtils.clearConsole();
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
            ConsoleUtils.clearConsole();
            Room room = iterator.next();
            System.out.print("========= ");
            System.out.print(room.getDescription());
            System.out.print(" of ");
            System.out.print(dungeon.getRoomCount());
            System.out.print(" =========");
            System.out.println();

            for (RoomObject obj : room.getObjects()) {
                if (obj instanceof Enemy enemy) {
                    enemy.interact(player);

                    try {
                        Thread.sleep(1000);
                        combatManager.startCombat(player, enemy);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }

                    if (!player.isAlive()) {
                        System.out.println("Game over!");
                        return;
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
}
