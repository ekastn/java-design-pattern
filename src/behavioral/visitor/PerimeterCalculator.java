package behavioral.visitor;

public class PerimeterCalculator implements Visitor {
    private double totalPerimeter = 0;

    @Override
    public void visit(Circle circle) {
        double perimeter = 2 * Math.PI * circle.getRadius();
        System.out.println("Circle perimeter: " + String.format("%.2f", perimeter));
        totalPerimeter += perimeter;
    }

    @Override
    public void visit(Rectangle rectangle) {
        double perimeter = 2 * (rectangle.getWidth() + rectangle.getHeight());
        System.out.println("Rectangle perimeter: " + String.format("%.2f", perimeter));
        totalPerimeter += perimeter;
    }

    @Override
    public void visit(Triangle triangle) {
        double a = Math.sqrt(Math.pow(triangle.getX2() - triangle.getX1(), 2) +
                Math.pow(triangle.getY2() - triangle.getY1(), 2));
        double b = Math.sqrt(Math.pow(triangle.getX3() - triangle.getX2(), 2) +
                Math.pow(triangle.getY3() - triangle.getY2(), 2));
        double c = Math.sqrt(Math.pow(triangle.getX1() - triangle.getX3(), 2) +
                Math.pow(triangle.getY1() - triangle.getY3(), 2));

        double perimeter = a + b + c;
        System.out.println("Triangle perimeter: " + String.format("%.2f", perimeter));
        totalPerimeter += perimeter;
    }

    @Override
    public String getResult() {
        return "Total perimeter: " + String.format("%.2f", totalPerimeter);
    }
}
