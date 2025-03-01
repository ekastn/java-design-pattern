package behavioral.observer;

public class LoggingListener implements EventListener {
    @Override
    public void update(EventType eventType, String message) {
        try {
            Thread.sleep(550);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(eventType.icon + " " + message);

        try {
            Thread.sleep(550);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
