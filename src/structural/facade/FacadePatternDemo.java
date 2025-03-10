package structural.facade;

public class FacadePatternDemo {
    public static void main(String[] args) {
        // Without Facade: Client would need to manage all these operations manually
        System.out.println("=== Without Facade (Complex) ===");
        CPU cpu = new CPU();
        Memory memory = new Memory();
        HardDrive hardDrive = new HardDrive();

        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();

        // With Facade: Client uses a simple interface
        System.out.println("\n=== With Facade (Simple) ===");
        ComputerFacade computer = new ComputerFacade();
        computer.start();

        // Simulating some computer usage
        System.out.println("User is working on the computer...");

        // Shutdown using the simple interface again
        computer.shutDown();
    }
}
