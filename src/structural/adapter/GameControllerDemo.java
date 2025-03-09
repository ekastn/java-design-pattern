package structural.adapter;

public class GameControllerDemo {
    public static void main(String[] args) {
        // Using a modern controller directly
        System.out.println("=== Playing with PS5 Controller ===");
        ModernController ps5Controller = new PS5Controller();
        Game gameWithModernController = new Game(ps5Controller);
        gameWithModernController.play();

        // Using a classic controller with adapter
        System.out.println("\n=== Playing with Classic Controller ===");
        ClassicController classicController = new ClassicController();
        ModernController adapter = new ClassicControllerAdapter(classicController);
        Game gameWithClassicController = new Game(adapter);
        gameWithClassicController.play();
    }
}
