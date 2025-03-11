package behavioral.templatemethod;

public class AggressiveEnemyAI extends GameCharacterAI {

    @Override
    protected void evaluateThreats() {
        System.out.println("Identifying nearest player character as primary threat...");
    }

    @Override
    protected void choosePrimaryTarget() {
        System.out.println("Targeting player with lowest health...");
    }

    @Override
    protected void selectAction() {
        System.out.println("Selecting most damaging attack for current range...");
        performSpecialAbility();
    }

    @Override
    protected void performSpecialAbility() {
        System.out.println("Activating rage mode for increased damage!");
    }
}
