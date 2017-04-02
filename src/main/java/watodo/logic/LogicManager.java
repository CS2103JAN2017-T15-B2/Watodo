package watodo.logic;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import watodo.commons.core.ComponentManager;
import watodo.commons.core.LogsCenter;
import watodo.logic.commands.Command;
import watodo.logic.commands.CommandResult;
import watodo.logic.commands.exceptions.CommandException;
import watodo.logic.parser.Parser;
import watodo.model.Model;
import watodo.model.task.ReadOnlyTask;
import watodo.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager extends ComponentManager implements Logic {
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Parser parser;

    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.parser = new Parser();
    }

    @Override
    /**
     * First parse the command line to find the corresponding command
     * Second send the model to the command, for it to accordingly make changes on.
     * Lastly, execute() will make the changes in the model, then return message result
     */
    public CommandResult execute(String commandText) throws CommandException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");
        Command command = parser.parseCommand(commandText);
        command.setData(model);   //pass in the existing model for modification
        return command.execute();
    }

    /**
     *
     */
    @Override
    public ObservableList<ReadOnlyTask> getFilteredTaskList() {
        return model.getFilteredTaskList();
    }
}
