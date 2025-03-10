package structural.flyweight;

public class GameFlyweightDemo {
    public static void main(String[] args) {
        System.out.println("Game initialization...");

        // Create a 5x5 forest (25 trees)
        ForestRenderer forest = new ForestRenderer(5, 5);

        // Print memory usage info
        System.out.println("\nMemory usage:");
        System.out.println("Without Flyweight: Would need 25 separate tree objects with textures and models");
        System.out.println("With Flyweight: Only 3 tree objects (one per type) with shared textures and models");

        // Render the forest
        forest.renderForest();
    }
}
