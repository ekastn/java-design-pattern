package behavioral.mediator;

public class PremiumUser extends User {
    public PremiumUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println("[" + name + "] ★ Sending message: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String from) {
        System.out.println("[" + name + "] ★ Received from " + from + ": " + message);
    }

    public void sendPriorityMessage(String message) {
        System.out.println("[" + name + "] ★★ Sending PRIORITY message: " + message);
        message = "PRIORITY: " + message;
        mediator.sendMessage(message, this);
    }
}
