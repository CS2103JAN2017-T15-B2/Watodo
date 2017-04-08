//@@author A0119505J
package watodo.logic.commands;

import java.util.ArrayList;

import watodo.model.UndoInfo;
import watodo.model.task.Task;
import watodo.model.task.UniqueTaskList;
import watodo.model.task.UniqueTaskList.TaskNotFoundException;

/**
 * Undo's the last undo action made by the user that mutated the task list
 */

public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Your previous action has been undone.";

    public static final String MESSAGE_FAILURE = "There are no changes that can be undone.";

    public static final int ADD_CMD_ID = 1;
    public static final int DEL_CMD_ID = 2;
    public static final int UPD_CMD_ID = 3;
    public static final int MARK_CMD_ID = 4;
    public static final int CLR_CMD_ID = 5;

    private static final int CURRENT_TASK = 0;
    private UndoInfo undoInfo;


    @Override
    public CommandResult execute() {
        assert model != null;
        if (model.getUndoStack().isEmpty()) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        undoInfo = model.getUndoStack().pop();
        int undoID = undoInfo.getUndoID();
        switch (undoID) {
        case ADD_CMD_ID : {
            undoAdd(undoInfo.getTasks().get(CURRENT_TASK));
            return new CommandResult(MESSAGE_SUCCESS);
        }
        case DEL_CMD_ID : {
            undoDelete(undoInfo.getTasks().get(CURRENT_TASK));
            return new CommandResult(MESSAGE_SUCCESS);
        }
        // case UPD_CMD_ID:
            // undoUpdate(undoInfo.getTasks().get(CURRENT_TASK), undoInfo.getTasks().get(ORIGINAL_TASK_INDEX));
            // return new CommandResult(MESSAGE_SUCCESS);
        case MARK_CMD_ID:
            undoMark(undoInfo.getTasks().get(CURRENT_TASK));
            return new CommandResult(MESSAGE_SUCCESS);
        case CLR_CMD_ID : {
            undoClear(undoInfo.getTasks());
            return new CommandResult(MESSAGE_SUCCESS);
        }
        default :
            return new CommandResult(MESSAGE_FAILURE);
        }
    }

    private void undoClear(ArrayList<Task> tasks) {
        try {
            model.clearTaskUndo(tasks);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
    }

    // the naming convention here is the reverse
    // developer MUST NOT change these two functions
    private void undoAdd(Task task) {
        try {
            model.deleteTaskUndo(task);
        } catch (TaskNotFoundException e) {
            assert false : "The target task cannot be missing";
        }
    }

    private void undoDelete(Task task) {
        try {
            model.addTaskUndo(task);
        } catch (UniqueTaskList.DuplicateTaskException e) {
            e.printStackTrace();
        }
    }

    private void undoMark(Task task) {
        try {
            model.markTaskUndo(task);
        } catch (TaskNotFoundException | UniqueTaskList.DuplicateTaskException e) {
            assert false : "The target task cannot be missing";
        }
    }

}

