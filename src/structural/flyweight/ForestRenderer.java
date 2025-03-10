package structural.flyweight;

import java.util.Random;

public class ForestRenderer {
    private final GameEntity[][] forest;
    private final int[][] positions;
    private final float[][] scales;
    private final int[][] facings;
    private final int width;
    private final int height;

    public ForestRenderer(int width, int height) {
        this.width = width;
        this.height = height;
        forest = new GameEntity[width][height];
        positions = new int[width][height];
        scales = new float[width][height];
        facings = new int[width][height];

        populateForest();
    }

    private void populateForest() {
        String[] treeTypes = {"Pine", "Oak", "Maple"};
        Random random = new Random();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                // Randomly select tree type
                String type = treeTypes[random.nextInt(treeTypes.length)];

                // Get flyweight tree object
                forest[i][j] = GameEntityFactory.getTree(type);

                // Set extrinsic state
                positions[i][j] = 10 + random.nextInt(10);  // Slight position offset
                scales[i][j] = 0.8f + random.nextFloat() * 0.4f;  // Random size variation
                facings[i][j] = random.nextInt(360);  // Random rotation
            }
        }
    }

    public void renderForest() {
        System.out.println("\nRendering forest with " + (width * height) + " trees:");
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int posX = i * 100 + positions[i][j];
                int posY = j * 100 + positions[i][j];
                forest[i][j].render(posX, posY, scales[i][j], facings[i][j]);
            }
        }
    }
}
