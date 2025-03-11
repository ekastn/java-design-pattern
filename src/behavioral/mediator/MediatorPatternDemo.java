package behavioral.mediator;


public class MediatorPatternDemo {
    public static void main(String[] args) {
        // Create the mediator
        ChatMediator chatRoom = new ChatRoom();

        // Create users
        User alice = new RegularUser(chatRoom, "Alice");
        User bob = new RegularUser(chatRoom, "Bob");
        User charlie = new PremiumUser(chatRoom, "Charlie");
        User dave = new AdminUser(chatRoom, "Dave");
        // Add users to the chat room
        chatRoom.addUser(alice);
        chatRoom.addUser(bob);
        chatRoom.addUser(charlie);
        chatRoom.addUser(dave);

        // Users send messages
        System.out.println("\n=== Regular Communication ===");
        alice.send("Hey everyone, how are you doing?");
        bob.send("I'm good, thanks for asking!");

        System.out.println("\n=== Premium User Features ===");
        PremiumUser premiumCharlie = (PremiumUser) charlie;
        premiumCharlie.send("Hello from a premium user!");
        premiumCharlie.sendPriorityMessage("This meeting is starting in 5 minutes!");

        System.out.println("\n=== Admin Features ===");
        AdminUser adminDave = (AdminUser) dave;
        adminDave.send("Hello, I'm the new admin.");
        adminDave.sendSystemMessage("The server will be down for maintenance tonight from 2-4 AM.");
    }
}
