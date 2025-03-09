package structural.decorator;

public class CoffeeShopDemo {
    public static void main(String[] args) {
        // Order a simple coffee
        Coffee simpleCoffee = new SimpleCoffee();
        printCoffeeDetails(simpleCoffee);

        // Order a coffee with milk
        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        printCoffeeDetails(milkCoffee);

        // Order an espresso with milk and chocolate
        Coffee fancyEspresso = new ChocolateDecorator(new MilkDecorator(new Espresso()));
        printCoffeeDetails(fancyEspresso);

        // Order a complex coffee with multiple decorators
        Coffee veryFancyCoffee = new WhippedCreamDecorator(
                new ChocolateDecorator(
                        new CaramelDecorator(
                                new VanillaDecorator(
                                        new DarkRoast()))));
        printCoffeeDetails(veryFancyCoffee);
    }

    private static void printCoffeeDetails(Coffee coffee) {
        System.out.println("Description: " + coffee.getDescription());
        System.out.println("Cost: $" + String.format("%.2f", coffee.getCost()));
        System.out.println("------------------------");
    }
}
