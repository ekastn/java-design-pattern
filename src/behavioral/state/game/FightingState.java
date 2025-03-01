package behavioral.state.game;

import core.Enemy;
import creational.singleton.Game;
import manager.CombatManager;

public class FightingState implements GameState {
    private final Enemy enemy;
    private final CombatManager combatManager = new CombatManager();

    public FightingState(Enemy enemy) {
        this.enemy = enemy;
    }

    @Override
    public void execute(Game game) {
        try {
            combatManager.startCombat(game.getPlayer(), enemy);
        } catch (Exception e) {
            System.out.println("An error occurred during combat: " + e.getMessage());
        }

        if (!game.getPlayer().isAlive()) {
            game.setGameState(new GameOverState());
        } else {
            game.setGameState(new ExploringState());
        }
    }
}
