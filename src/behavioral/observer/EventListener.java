package behavioral.observer;

public interface EventListener {
    void update(EventType eventType, String message);
}
