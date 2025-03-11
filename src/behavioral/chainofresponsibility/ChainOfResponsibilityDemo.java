package behavioral.chainofresponsibility;

public class ChainOfResponsibilityDemo {
    public static void main(String[] args) {
        // Create the chain of responsibility
        Approver manager = new Manager("John");
        Approver director = new Director("Sarah");
        Approver vp = new VP("Michael");
        Approver ceo = new CEO("Lisa");

        // Set up the chain
        manager.setNext(director);
        director.setNext(vp);
        vp.setNext(ceo);

        // Create some purchase requests
        PurchaseRequest request1 = new PurchaseRequest(1001, 850.0, "Office supplies");
        PurchaseRequest request2 = new PurchaseRequest(1002, 2500.0, "Project hardware");
        PurchaseRequest request3 = new PurchaseRequest(1003, 7500.0, "Marketing campaign");
        PurchaseRequest request4 = new PurchaseRequest(1004, 25000.0, "New servers");
        PurchaseRequest request5 = new PurchaseRequest(1005, 100000.0, "Company acquisition");

        // Process the requests
        System.out.println("\n=== Processing Purchase Requests ===\n");
        manager.processRequest(request1);
        manager.processRequest(request2);
        manager.processRequest(request3);
        manager.processRequest(request4);
        manager.processRequest(request5);
    }
}
