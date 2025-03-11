package behavioral.mediator;

public class AdminUser extends User {
    public AdminUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String message) {
        System.out.println("[" + name + "] [ADMIN] Sending message: " + message);
        mediator.sendMessage(message, this);
    }

    @Override
    public void receive(String message, String from) {
        System.out.println("[" + name + "] [ADMIN] Received from " + from + ": " + message);
    }

    public void sendSystemMessage(String message) {
        System.out.println("[" + name + "] [ADMIN] Sending SYSTEM message");
        message = "SYSTEM ANNOUNCEMENT: " + message;
        mediator.sendMessage(message, this);
    }
}
