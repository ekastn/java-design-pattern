package manager;

import behavioral.command.*;
import behavioral.observer.EventType;
import core.Enemy;
import core.Hero;
import creational.singleton.Game;
import utils.ConsoleUtils;

import java.util.Scanner;

public class CombatManager {
    private final Scanner scanner = new Scanner(System.in);

    public void startCombat(Hero player, Enemy enemy) {
        while (enemy.isAlive() && player.isAlive()) {
            ConsoleUtils.clearConsole();
            player.info();

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Use Potion");
            System.out.print("Enter your choice (1-2): ");

            int action = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            CombatCommand command;
            switch (action) {
                case 1 -> command = new AttackCommand();
                case 2 -> command = new UsePotionCommand(player);
                default -> {
                    System.out.println("Invalid choice! Defaulting to attack.");
                    command = new AttackCommand();
                }
            }

            System.out.println();

            command.execute(player, enemy);

            if (enemy.isAlive()) {
                new AttackCommand().execute(enemy, player);
            }
        }

        if (enemy.hasEscaped()) {
            Game.notify(EventType.CHARACTER_FLEEING, enemy.getName() + " has escaped!");
        } else {
            Game.notify(EventType.ENEMY_DEAD, enemy.getName() + " has been defeated!");
        }
    }
}

