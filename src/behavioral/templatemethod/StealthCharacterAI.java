package behavioral.templatemethod;

public class StealthCharacterAI extends GameCharacterAI {

    @Override
    protected void evaluateThreats() {
        System.out.println("Scanning for enemies with detection abilities...");
    }

    @Override
    protected void choosePrimaryTarget() {
        System.out.println("Targeting isolated enemy with high value...");
    }

    @Override
    protected void selectAction() {
        System.out.println("Selecting ambush or assassination technique...");
        performSpecialAbility();
    }

    @Override
    protected void performSpecialAbility() {
        System.out.println("Activating shadow cloak for temporary invisibility!");
    }
}
