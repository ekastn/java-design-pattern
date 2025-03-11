package behavioral.memento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Originator class
public class TextEditor {
    private String content;
    private int cursorPosition;
    private String selectionText;

    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
        this.selectionText = "";
    }

    public void write(String text) {
        // Insert text at cursor position
        if (selectionText.isEmpty()) {
            content = content.substring(0, cursorPosition) + text + content.substring(cursorPosition);
            cursorPosition += text.length();
        } else {
            // Replace selected text
            int selectionStart = content.indexOf(selectionText);
            content = content.substring(0, selectionStart) + text +
                    content.substring(selectionStart + selectionText.length());
            cursorPosition = selectionStart + text.length();
            selectionText = "";
        }
    }

    public void delete() {
        if (!selectionText.isEmpty()) {
            // Delete selected text
            int selectionStart = content.indexOf(selectionText);
            content = content.substring(0, selectionStart) +
                    content.substring(selectionStart + selectionText.length());
            cursorPosition = selectionStart;
            selectionText = "";
        } else if (cursorPosition < content.length()) {
            // Delete character at cursor
            content = content.substring(0, cursorPosition) + content.substring(cursorPosition + 1);
        }
    }

    public void selectText(String text) {
        if (content.contains(text)) {
            selectionText = text;
            cursorPosition = content.indexOf(text) + text.length();
        }
    }

    public void moveCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            cursorPosition = position;
            selectionText = ""; // Clear selection when moving cursor
        }
    }

    public void displayState() {
        System.out.println("\nCurrent Text: \"" + content + "\"");
        System.out.println("Cursor Position: " + cursorPosition);
        if (!selectionText.isEmpty()) {
            System.out.println("Selected Text: \"" + selectionText + "\"");
        }
    }

    // Create a memento containing the current state
    public EditorMemento save() {
        return new EditorMemento(content, cursorPosition, selectionText);
    }

    // Restore state from a memento
    public void restore(EditorMemento memento) {
        this.content = memento.getContent();
        this.cursorPosition = memento.getCursorPosition();
        this.selectionText = memento.getSelectionText();
    }

    // Memento class - static nested class to increase encapsulation
    public static class EditorMemento {
        private final String content;
        private final int cursorPosition;
        private final String selectionText;
        private final LocalDateTime savedTime;

        private EditorMemento(String content, int cursorPosition, String selectionText) {
            this.content = content;
            this.cursorPosition = cursorPosition;
            this.selectionText = selectionText;
            this.savedTime = LocalDateTime.now();
        }

        // These methods are only accessible within the originator
        private String getContent() {
            return content;
        }

        private int getCursorPosition() {
            return cursorPosition;
        }

        private String getSelectionText() {
            return selectionText;
        }

        // Public method to get snapshot info for display
        public String getSnapshotInfo() {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            return "Snapshot [" + formatter.format(savedTime) + "] - " +
                    "Content: \"" + (content.length() > 15 ? content.substring(0, 15) + "..." : content) + "\"";
        }
    }
}
