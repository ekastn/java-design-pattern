package behavioral.visitor;

public class VisitorPatternDemo {
    public static void main(String[] args) {
        // Create a drawing with various shapes
        Drawing drawing = new Drawing();
        drawing.add(new Circle(5, 5, 10));
        drawing.add(new Rectangle(10, 10, 20, 15));
        drawing.add(new Triangle(0, 0, 0, 5, 5, 0));

        // Calculate areas
        System.out.println("=== Area Calculations ===");
        AreaCalculator areaCalculator = new AreaCalculator();
        drawing.accept(areaCalculator);
        System.out.println(areaCalculator.getResult());

        // Calculate perimeters
        System.out.println("\n=== Perimeter Calculations ===");
        PerimeterCalculator perimeterCalculator = new PerimeterCalculator();
        drawing.accept(perimeterCalculator);
        System.out.println(perimeterCalculator.getResult());

        // Export to XML
        System.out.println("\n=== XML Export ===");
        XMLExportVisitor xmlExportVisitor = new XMLExportVisitor();
        drawing.accept(xmlExportVisitor);
        System.out.println(xmlExportVisitor.getResult());
    }
}
