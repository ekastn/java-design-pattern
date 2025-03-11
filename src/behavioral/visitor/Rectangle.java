package behavioral.visitor;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private double x;
    private double y;

    public Rectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
