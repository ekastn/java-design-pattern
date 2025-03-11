package behavioral.chainofresponsibility;

public class CEO extends Approver {
    private static final double APPROVAL_LIMIT = 50000;

    public CEO(String name) {
        super(name);
    }

    @Override
    public void processRequest(PurchaseRequest request) {
        if (request.getAmount() <= APPROVAL_LIMIT) {
            System.out.println("CEO " + approverName + " approved request #" + request.getId() +
                    " for $" + request.getAmount() + " (" + request.getPurpose() + ")");
        } else if (nextApprover != null) {
            System.out.println("CEO " + approverName + " forwards request #" + request.getId() + " to next level");
            nextApprover.processRequest(request);
        } else {
            System.out.println("Request #" + request.getId() +
                    " cannot be approved - requires board approval");
        }
    }
}
