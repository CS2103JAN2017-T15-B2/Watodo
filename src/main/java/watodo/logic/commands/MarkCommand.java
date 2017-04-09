package watodo.logic.commands;

import java.util.List;
import java.util.Set;

import watodo.logic.commands.exceptions.CommandException;
import watodo.model.tag.UniqueTagList;
import watodo.model.task.Name;
import watodo.model.task.Priority;
import watodo.model.task.ReadOnlyTask;
import watodo.model.task.Status;
import watodo.model.task.Task;
import watodo.model.task.Time;
import watodo.model.task.UniqueTaskList.TaskNotFoundException;

//@@author A0164393Y

/**
 * Finds and lists all tasks in task manager whose name contains any of the
 * argument keywords. Keyword matching is case sensitive.
 */
public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    public static final String MESSAGE_INVALID_TASK = "This task is missing in the task manager.";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Marks the task completed or not completed \n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n" + "Example: " + COMMAND_WORD
            + " task_number completed OR not_completed";
    public static final String MESSAGE_SUCCESS = "Marked task: %1$s";

    private final Set<String> keywords;

    public MarkCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public CommandResult execute() throws CommandException {
        List<ReadOnlyTask> lastShownList = model.getFilteredTaskList();

        String[] parameters = keywords.toArray(new String[keywords.size()]);
        int filteredTaskListIndex = Integer.parseInt(parameters[0]) - 1;
        ReadOnlyTask taskToEdit;
        if (filteredTaskListIndex < lastShownList.size() && filteredTaskListIndex != -1)
            taskToEdit = lastShownList.get(filteredTaskListIndex);
        else
            throw new CommandException(MESSAGE_INVALID_TASK);
        Task editedTask = createEditedTask(taskToEdit, parameters[1]);

        //@@author A0119505J
        try {
            model.markTask(filteredTaskListIndex, editedTask);
        } catch (TaskNotFoundException e) {
            throw new CommandException(MESSAGE_INVALID_TASK);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(ReadOnlyTask taskToEdit, String status) {
        assert taskToEdit != null;
        int flag;
        if (status.equals("completed")) {
            flag = 1;
        } else {
            flag = 0;
        }
        Status stat = new Status(flag);
        Name updatedName = taskToEdit.getName();
        Time updatedEndTime = taskToEdit.getEndTime();
        Time updatedStartTime = taskToEdit.getStartTime();
        Status updatedStatus = stat;
        Priority updatedPriority = taskToEdit.getPriority();
        UniqueTagList updatedTags = taskToEdit.getTags();
        return new Task(updatedName, updatedStartTime, updatedEndTime, updatedPriority, updatedTags, updatedStatus);
    }
}
