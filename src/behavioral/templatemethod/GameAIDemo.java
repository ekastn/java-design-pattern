package behavioral.templatemethod;

public class GameAIDemo {
    public static void main(String[] args) {
        GameCharacterAI enemy = new AggressiveEnemyAI();
        GameCharacterAI support = new SupportCharacterAI();
        GameCharacterAI assassin = new StealthCharacterAI();

        System.out.println("Enemy AI Turn:");
        enemy.executeTurn();

        System.out.println("\nSupport AI Turn:");
        support.executeTurn();

        System.out.println("\nStealth Character AI Turn:");
        assassin.executeTurn();
    }
}
