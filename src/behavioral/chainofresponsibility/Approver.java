package behavioral.chainofresponsibility;

public abstract class Approver {
    protected Approver nextApprover;
    protected String approverName;

    public Approver(String name) {
        this.approverName = name;
    }

    public void setNext(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    public abstract void processRequest(PurchaseRequest request);
}
