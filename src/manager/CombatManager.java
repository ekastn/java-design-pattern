package manager;

import behavioral.command.*;
import core.Enemy;
import core.Hero;
import utils.ConsoleUtils;

import java.util.Scanner;

public class CombatManager {
    private final Scanner scanner = new Scanner(System.in);

    public void startCombat(Hero player, Enemy enemy) throws InterruptedException {
        while (enemy.isAlive() && player.isAlive()) {
            ConsoleUtils.clearConsole();
            player.info();

            System.out.println("\nChoose an action:");
            System.out.println("1. Attack");
            System.out.println("2. Defend");
            System.out.println("3. Use Potion");
            System.out.print("Enter your choice (1-2): ");

            int action = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            CombatCommand command;
            switch (action) {
                case 1 -> command = new AttackCommand();
                case 2 -> command = new DefendCommand();
                case 3 -> command = new UsePotionCommand(player);
                default -> {
                    System.out.println("Invalid choice! Defaulting to attack.");
                    command = new AttackCommand();
                }
            }

            System.out.println();

            command.execute(player, enemy);
            Thread.sleep(500);

            if (enemy.isAlive()) {
                new AttackCommand().execute(enemy, player);
            }

            Thread.sleep(1000);
        }

        if (enemy.hasEscaped()) {
            System.out.println(enemy.getName() + " has escaped!");
        } else {
            System.out.println("You defeated " + enemy.getName() + "!");
        }
    }
}

