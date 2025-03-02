package creational.singleton;

import behavioral.observer.EventManager;
import behavioral.observer.EventType;
import behavioral.observer.LoggingListener;
import behavioral.state.game.ExploringState;
import behavioral.state.game.GameState;
import creational.builder.DungeonBuilder;
import creational.builder.HeroBuilder;
import core.*;
import creational.builder.HeroDirector;
import utils.ConsoleUtils;

import java.util.Scanner;

public class Game {
    private boolean running = true;
    private Hero player;
    private Dungeon dungeon;
    private GameState gameState;

    private final Scanner scanner;

    private static Game instance;
    private static final EventManager eventManager = new EventManager();

    private Game() {
        this.scanner = new Scanner(System.in);
        this.gameState = new ExploringState();
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public static void notify(EventType eventType, String message) {
        eventManager.notify(eventType, message);
    }

    public void start() {
        eventManager.subscribe(new LoggingListener());

        ConsoleUtils.clearConsole();
        System.out.println("Welcome to The Dungeon Adventure!");
        System.out.println("Your only goal is to survive!");

        chooseCharacter();
        createDungeon();

        dungeon.startExploration();

        while (running) {
            gameState.execute(this);
        }

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

        HeroDirector heroDirector = new HeroDirector(new HeroBuilder(), name);

        player = switch (choice) {
            case 1 -> heroDirector.createWarrior();
            case 2 -> heroDirector.createMage();
            case 3 -> heroDirector.createAssassin();
            case 4 -> heroDirector.createTank();
            default -> {
                System.out.println("Invalid choice. Defaulting to Warrior.");
                yield heroDirector.createWarrior();
            }
        };
    }

    private void createDungeon() {
        dungeon = new DungeonBuilder().setRoomCount(5).build();
    }

    public Dungeon getDungeon() {
        return dungeon;
    }

    public Hero getPlayer() {
        return player;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
