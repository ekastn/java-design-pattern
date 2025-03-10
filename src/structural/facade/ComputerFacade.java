package structural.facade;

public class ComputerFacade {
    private final CPU cpu;
    private final Memory memory;
    private final HardDrive hardDrive;

    public ComputerFacade() {
        this.cpu = new CPU();
        this.memory = new Memory();
        this.hardDrive = new HardDrive();
    }

    public void start() {
        System.out.println("\nComputer startup sequence initiated...");
        cpu.freeze();
        memory.load(0, hardDrive.read(0, 1024));
        cpu.jump(0);
        cpu.execute();
        System.out.println("Computer startup completed successfully!\n");
    }

    public void shutDown() {
        System.out.println("\nComputer shutdown sequence initiated...");
        // Simulate shutdown operations
        System.out.println("Saving data to disk...");
        System.out.println("Closing applications...");
        System.out.println("Stopping services...");
        System.out.println("Computer shutdown completed successfully!\n");
    }
}
