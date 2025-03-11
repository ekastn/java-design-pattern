package behavioral.visitor;

public class XMLExportVisitor implements Visitor {
    private StringBuilder xml = new StringBuilder();

    public XMLExportVisitor() {
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        xml.append("<drawing>\n");
    }

    @Override
    public void visit(Circle circle) {
        xml.append("  <circle>\n");
        xml.append("    <x>").append(circle.getX()).append("</x>\n");
        xml.append("    <y>").append(circle.getY()).append("</y>\n");
        xml.append("    <radius>").append(circle.getRadius()).append("</radius>\n");
        xml.append("  </circle>\n");
    }

    @Override
    public void visit(Rectangle rectangle) {
        xml.append("  <rectangle>\n");
        xml.append("    <x>").append(rectangle.getX()).append("</x>\n");
        xml.append("    <y>").append(rectangle.getY()).append("</y>\n");
        xml.append("    <width>").append(rectangle.getWidth()).append("</width>\n");
        xml.append("    <height>").append(rectangle.getHeight()).append("</height>\n");
        xml.append("  </rectangle>\n");
    }

    @Override
    public void visit(Triangle triangle) {
        xml.append("  <triangle>\n");
        xml.append("    <point1 x=\"").append(triangle.getX1()).append("\" y=\"").append(triangle.getY1()).append("\"/>\n");
        xml.append("    <point2 x=\"").append(triangle.getX2()).append("\" y=\"").append(triangle.getY2()).append("\"/>\n");
        xml.append("    <point3 x=\"").append(triangle.getX3()).append("\" y=\"").append(triangle.getY3()).append("\"/>\n");
        xml.append("  </triangle>\n");
    }

    @Override
    public String getResult() {
        return xml.toString() + "</drawing>";
    }
}
