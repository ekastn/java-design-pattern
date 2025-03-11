package behavioral.templatemethod;

public class SupportCharacterAI extends GameCharacterAI {

    @Override
    protected void evaluateThreats() {
        System.out.println("Identifying team members in danger...");
    }

    @Override
    protected void choosePrimaryTarget() {
        System.out.println("Targeting ally with critical health status...");
    }

    @Override
    protected void selectAction() {
        System.out.println("Selecting healing ability or protective buff...");
    }
}
