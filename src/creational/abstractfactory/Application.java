package creational.abstractfactory;

public class Application {
    private Button button;
    private Checkbox checkbox;
    private TextField textField;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
        textField = factory.createTextField();
    }

    public void createUI() {
        System.out.println("\nCreating UI components:");
        button.render();
        checkbox.render();
        textField.render();
    }

    public void simulateUserInteraction() {
        System.out.println("\nSimulating user interaction:");
        button.onClick();
        checkbox.toggle();
        checkbox.render();
        textField.getText();
    }
}
