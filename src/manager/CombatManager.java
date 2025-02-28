package manager;

import behavioral.command.AttackCommand;
import behavioral.command.Command;
import behavioral.command.UsePotionCommand;
import character.Enemy;
import character.Hero;
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
            System.out.println("2. Use Potion");
            System.out.print("Enter your choice (1-2): ");

            int action = scanner.nextInt();
            scanner.nextLine();

            System.out.println();

            Command command;
            if (action == 1) {
                command = new AttackCommand(player, enemy);
            } else if (action == 2) {
                command = new UsePotionCommand(player);
            } else {
                System.out.println("Invalid choice! Defaulting to attack.");
                command = new AttackCommand(player, enemy);
            }

            System.out.println();

            command.execute();
            Thread.sleep(500);

            if (enemy.isAlive()) {
                enemy.checkState();
                enemy.performAttack(player);
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

