package behavioral.state.game;

import core.Dungeon;
import core.Hero;
import creational.builder.DungeonBuilder;
import creational.builder.HeroBuilder;
import creational.builder.HeroDirector;
import creational.singleton.Game;

import java.util.Scanner;

public class GameStartUpState implements GameState {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void execute(Game game) {
        System.out.println("Welcome to The Dungeon Adventure!");
        System.out.println("Your only goal is to survive!");

        chooseCharacter(game);
        createDungeon(game);

        game.setGameState(new ExploringState());
    }

    private void createDungeon(Game game) {
        Dungeon dungeon = new DungeonBuilder().setRoomCount(5).build();
        game.setDungeon(dungeon);
    }

    private void chooseCharacter(Game game) {
        System.out.println("\nChoose your character:");
        System.out.println("1. Warrior");
        System.out.println("2. Mage");
        System.out.println("3. Assassin");
        System.out.println("4. Tank");
        System.out.print("\nEnter your choice (1-4): ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter character name: ");
        String name = scanner.nextLine();

        HeroDirector heroDirector = new HeroDirector(new HeroBuilder(), name);

        Hero player = switch (choice) {
            case 1 -> heroDirector.createWarrior();
            case 2 -> heroDirector.createMage();
            case 3 -> heroDirector.createAssassin();
            case 4 -> heroDirector.createTank();
            default -> {
                System.out.println("Invalid choice. Defaulting to Warrior.");
                yield heroDirector.createWarrior();
            }
        };

        game.setPlayer(player);
    }
}
