package behavioral.chainofresponsibility;

public class Manager extends Approver {
    private static final double APPROVAL_LIMIT = 1000;

    public Manager(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= APPROVAL_LIMIT) {
            System.out.println("Manager " + approverName + " approved request #" + request.getId() +
                    " for $" + request.getAmount() + " (" + request.getPurpose() + ")");
        } else if (nextApprover != null) {
            System.out.println("Manager " + approverName + " forwards request #" + request.getId() + " to next level");
            nextApprover.processRequest(request);
        } else {
            System.out.println("Request #" + request.getId() + " cannot be approved (no higher authority)");
        }
    }
}
