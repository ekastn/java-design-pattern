package creational.singleton;

import behavioral.observer.*;
import behavioral.state.game.GameStartUpState;
import behavioral.state.game.GameState;
import core.Dungeon;
import core.Hero;
import utils.ConsoleUtils;

public class Game {
    private boolean running = true;
    private Hero player;
    private Dungeon dungeon;
    private GameState gameState;

    private static Game instance;
    private static final EventManager eventManager = new EventManager();

    private Game() {
        this.gameState = new GameStartUpState();
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
        eventManager.subscribe(new GameStateListener());
        eventManager.subscribe(new DungeonProgressListener());
        ConsoleUtils.clearConsole();

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

    public Dungeon getDungeon() {
        return dungeon;
    }

    public void setDungeon(Dungeon dungeon) {
        this.dungeon = dungeon;
    }

    public Hero getPlayer() {
        return player;
    }

    public void setPlayer(Hero player) {
        this.player = player;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void setGameState(GameState gameState) {
        eventManager.notify(EventType.GAME_STATE, "Game state changed to " + gameState.getClass().getSimpleName());
        this.gameState = gameState;
    }

    public GameState getGameState() {
        return gameState;
    }
}
