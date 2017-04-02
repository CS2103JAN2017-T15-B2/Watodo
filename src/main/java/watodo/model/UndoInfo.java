//@@author A0119505J
package watodo.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import watodo.model.task.Task;

/**
 * Stores the undo information for reverting to the previous state
 * Previous state of the task is stored
 * Also, the command that was performed on this task is stored using a command ID
 * For undoing setstorage, the original filepath is stored as a string in filePath
 */
public class UndoInfo {

    private int undoID;
    private String filePath;
    private ArrayList<Task> tasks;

    public UndoInfo(int undoID, String filePath, Task... tasks) {
        this.undoID = undoID;
        Collection<Task> collection = Arrays.asList(tasks);
        this.filePath = filePath;
        this.tasks = new ArrayList<Task>(collection);
    }

    public int getUndoID() {
        return undoID;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
