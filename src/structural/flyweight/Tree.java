package structural.flyweight;

public class Tree implements GameEntity {
    // Intrinsic state - shared
    private final String textureFile;
    private final int polygons;
    private final String type;

    public Tree(String type) {
        this.type = type;

        switch(type) {
            case "Pine":
                this.textureFile = "pine_texture.png";
                this.polygons = 500;
                break;
            case "Oak":
                this.textureFile = "oak_texture.png";
                this.polygons = 600;
                break;
            case "Maple":
                this.textureFile = "maple_texture.png";
                this.polygons = 550;
                break;
            default:
                this.textureFile = "default_tree.png";
                this.polygons = 400;
        }

        // Simulate loading heavy resources
        System.out.println("Loading tree model for " + type + " with texture " + textureFile + " (" + polygons + " polygons)");
    }

    @Override
    public void render(int posX, int posY, float scale, int facing) {
        // Extrinsic state - passed in for each instance
        System.out.println("Rendering " + type + " tree at position (" + posX + ", " + posY +
                ") with scale " + scale + " and facing " + facing + " degrees");
    }
}
