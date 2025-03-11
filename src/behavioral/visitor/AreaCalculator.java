package behavioral.visitor;

public class AreaCalculator implements Visitor {
    private double totalArea = 0;

    @Override
    public void visit(Circle circle) {
        double area = Math.PI * circle.getRadius() * circle.getRadius();
        System.out.println("Circle area: " + String.format("%.2f", area));
        totalArea += area;
    }

    @Override
    public void visit(Rectangle rectangle) {
        double area = rectangle.getWidth() * rectangle.getHeight();
        System.out.println("Rectangle area: " + String.format("%.2f", area));
        totalArea += area;
    }

    @Override
    public void visit(Triangle triangle) {
        // Using Heron's formula
        double a = Math.sqrt(Math.pow(triangle.getX2() - triangle.getX1(), 2) +
                Math.pow(triangle.getY2() - triangle.getY1(), 2));
        double b = Math.sqrt(Math.pow(triangle.getX3() - triangle.getX2(), 2) +
                Math.pow(triangle.getY3() - triangle.getY2(), 2));
        double c = Math.sqrt(Math.pow(triangle.getX1() - triangle.getX3(), 2) +
                Math.pow(triangle.getY1() - triangle.getY3(), 2));
        double s = (a + b + c) / 2;
        double area = Math.sqrt(s * (s - a) * (s - b) * (s - c));

        System.out.println("Triangle area: " + String.format("%.2f", area));
        totalArea += area;
    }

    @Override
    public String getResult() {
        return "Total area: " + String.format("%.2f", totalArea);
    }
}
