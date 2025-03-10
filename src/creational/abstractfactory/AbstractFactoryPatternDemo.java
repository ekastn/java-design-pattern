package creational.abstractfactory;

public class AbstractFactoryPatternDemo {
    public static void main(String[] args) {
        System.out.println("--- Windows Application ---");
        GUIFactory windowsFactory = new WindowsFactory();
        Application windowsApp = new Application(windowsFactory);
        windowsApp.createUI();
        windowsApp.simulateUserInteraction();

        System.out.println("\n--- MacOS Application ---");
        GUIFactory macOSFactory = new MacOSFactory();
        Application macOSApp = new Application(macOSFactory);
        macOSApp.createUI();
        macOSApp.simulateUserInteraction();
    }
}
