package watodo.logic.commands;

import java.util.HashSet;
import java.util.Set;

import watodo.commons.exceptions.IllegalValueException;
import watodo.logic.commands.exceptions.CommandException;
import watodo.model.tag.Tag;
import watodo.model.tag.UniqueTagList;
import watodo.model.task.Name;
import watodo.model.task.Priority;
import watodo.model.task.Status;
import watodo.model.task.Task;
import watodo.model.task.Time;
import watodo.model.task.UniqueTaskList;

/**
 * Adds a task to the task manager.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task manager.\n"
            + "Parameters: NAME from/START_TIME to/END_TIME p/PRIORITY [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " submit report to/02/05/2017 12:00 p/high";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    private final Task toAdd;

    /**
     * Creates an AddCommand using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public AddCommand(String name, String startTime, String endTime, String priority, Set<String> tags)
            throws IllegalValueException {

        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Task(
                new Name(name),
                new Time(startTime),
                new Time(endTime),
                new Priority(priority),
                new UniqueTagList(tagSet),
                new Status(0)
        );
    }

    @Override
    public CommandResult execute() throws CommandException {
        assert model != null;
        try {
            model.addTask(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (UniqueTaskList.DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

    }

}
