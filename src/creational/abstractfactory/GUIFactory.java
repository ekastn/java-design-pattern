package creational.abstractfactory;

public interface GUIFactory {
    Button createButton();

    Checkbox createCheckbox();

    TextField createTextField();
}
