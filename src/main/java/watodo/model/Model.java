package watodo.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;

import watodo.commons.core.UnmodifiableObservableList;
import watodo.model.task.ReadOnlyTask;
import watodo.model.task.Task;
import watodo.model.task.UniqueTaskList;
import watodo.model.task.UniqueTaskList.DuplicateTaskException;
import watodo.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * The API of the Model component.
 */
public interface Model {
    /** Clears existing backing model and replaces with the provided new data. */
    void resetData(ReadOnlyTaskManager newData);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    /** Deletes the given task. */
    void deleteTask(ReadOnlyTask target) throws UniqueTaskList.TaskNotFoundException;

    /** Adds the given task */
    void addTask(Task task) throws UniqueTaskList.DuplicateTaskException;

    /**
     * Updates the task located at {@code filteredTaskListIndex} with {@code editedTask}.
     *
     * @throws DuplicateTaskException if updating the task's details causes the task to be equivalent to
     *      another existing task in the list.
     * @throws IndexOutOfBoundsException if {@code filteredTaskListIndex} < 0 or >= the size of the filtered list.
     */
    void updateTask(int filteredTaskListIndex, ReadOnlyTask editedTask)
            throws UniqueTaskList.DuplicateTaskException;

    /** Returns the filtered task list as an {@code UnmodifiableObservableList<ReadOnlyTask>} */
    UnmodifiableObservableList<ReadOnlyTask> getFilteredTaskList();

    /** Updates the filter of the filtered task list to show all tasks */
    void updateFilteredListToShowAll();

    /** Updates the filter of the filtered task list to filter by the given keywords*/
    void updateFilteredTaskList(Set<String> keywords);


    // @@author A0164393Y

    /**
     * Updates the status
     *
     * @throws TaskNotFoundException
     */
    void markTask(int index, Task editedTask) throws TaskNotFoundException;

    void addTaskUndo(Task task) throws DuplicateTaskException;

    // void updateTaskUndo(Task taskToUpdate, TaskDetails taskDetails, StartTime startTime, EndTime endTime,
    //         Priority priority, String frequency) throws IllegalValueException;

    void deleteTaskUndo(ReadOnlyTask target) throws TaskNotFoundException;

    void clearTaskUndo(ArrayList<Task> tasks) throws TaskNotFoundException;

    void markTaskUndo(int index, Task editedTask) throws TaskNotFoundException;

    LinkedList<UndoInfo> getUndoStack();

    void addToUndoStack(int strCmdId, String currentFilePath, Task... tasks);

}
