package structural.decorator;

public class DarkRoast implements Coffee {
    @Override
    public String getDescription() {
        return "Dark Roast Coffee";
    }

    @Override
    public double getCost() {
        return 2.49;
    }
}
