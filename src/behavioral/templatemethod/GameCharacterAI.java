package behavioral.templatemethod;

public abstract class GameCharacterAI {

    // Template method defining character behavior cycle
    public final void executeTurn() {
        gatherInformation();
        evaluateThreats();
        choosePrimaryTarget();
        selectAction();
        performAction();
        updateState();
    }

    // Common operations
    private void gatherInformation() {
        System.out.println("Gathering environment and game state information...");
    }

    private void performAction() {
        System.out.println("Executing selected action...");
    }

    private void updateState() {
        System.out.println("Updating character state after action...");
    }

    // Abstract operations that vary by character type
    protected abstract void evaluateThreats();
    protected abstract void choosePrimaryTarget();
    protected abstract void selectAction();

    // Hook method
    protected void performSpecialAbility() {
        // Default implementation does nothing
    }
}
