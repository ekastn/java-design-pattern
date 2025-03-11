package behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

public class ChatRoom implements ChatMediator {
    private List<User> users;

    public ChatRoom() {
        this.users = new ArrayList<>();
    }

    @Override
    public void addUser(User user) {
        users.add(user);
        System.out.println("[ChatRoom] " + user.getName() + " joined the chat");
    }

    @Override
    public void sendMessage(String message, User sender) {
        System.out.println("\n[ChatRoom] " + sender.getName() + " sends: " + message);

        // Distribute the message to all users except the sender
        for (User user : users) {
            if (user != sender) {
                user.receive(message, sender.getName());
            }
        }
    }
}
