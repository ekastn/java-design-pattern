package behavioral.memento;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class History {
    private final Stack<TextEditor.EditorMemento> undoStack = new Stack<>();
    private final Stack<TextEditor.EditorMemento> redoStack = new Stack<>();

    public void save(TextEditor editor) {
        redoStack.clear(); // Clear redo stack when a new state is saved
        undoStack.push(editor.save());
    }

    public void undo(TextEditor editor) {
        if (!undoStack.isEmpty()) {
            // Save current state to redo stack
            redoStack.push(editor.save());

            // Get the previous state
            TextEditor.EditorMemento memento = undoStack.pop();
            editor.restore(memento);
        } else {
            System.out.println("Nothing to undo!");
        }
    }

    public void redo(TextEditor editor) {
        if (!redoStack.isEmpty()) {
            // Save current state to undo stack
            undoStack.push(editor.save());

            // Get the next state
            TextEditor.EditorMemento memento = redoStack.pop();
            editor.restore(memento);
        } else {
            System.out.println("Nothing to redo!");
        }
    }

    public void showHistory() {
        System.out.println("\n=== History Snapshots ===");
        if (undoStack.isEmpty()) {
            System.out.println("No snapshots available");
        } else {
            List<TextEditor.EditorMemento> tempList = new ArrayList<>(undoStack);
            for (int i = tempList.size() - 1; i >= 0; i--) {
                System.out.println((tempList.size() - i) + ": " + tempList.get(i).getSnapshotInfo());
            }
        }
    }
}
