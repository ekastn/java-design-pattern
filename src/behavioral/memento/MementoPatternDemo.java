package behavioral.memento;

public class MementoPatternDemo {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        History history = new History();

        // Initial state
        editor.write("Hello world!");
        editor.displayState();
        history.save(editor);

        // Make changes
        editor.selectText("world");
        editor.write("Java");
        editor.displayState();
        history.save(editor);

        // Make more changes
        editor.moveCursor(0);
        editor.write("Greetings: ");
        editor.displayState();
        history.save(editor);

        // View history
        history.showHistory();

        // Undo changes
        System.out.println("\n=== Performing Undo ===");
        history.undo(editor);
        editor.displayState();

        System.out.println("\n=== Performing Undo Again ===");
        history.undo(editor);
        editor.displayState();

        // Redo changes
        System.out.println("\n=== Performing Redo ===");
        history.redo(editor);
        editor.displayState();

        // Make a different change after undo
        System.out.println("\n=== Making New Changes After Undo ===");
        editor.selectText("Java");
        editor.write("Programming");
        editor.displayState();
        history.save(editor);

        // View updated history
        history.showHistory();
    }
}
