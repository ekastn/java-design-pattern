package behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private final List<EventListener> listeners = new ArrayList<>();

    public void subscribe(EventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        listeners.remove(listener);
    }

    public void notify(EventType eventType, String message) {
        for (EventListener listener : listeners) {
            listener.update(eventType, message);
        }
    }
}
