package watodo.logic.commands;

import java.io.IOException;
import java.util.Optional;

import watodo.commons.core.Config;
import watodo.commons.exceptions.DataConversionException;
import watodo.commons.util.ConfigUtil;
import watodo.logic.commands.exceptions.CommandException;

/**
 * Deletes a task identified using it's last displayed index from the task manager.
 */
public class DataCommand extends Command {

    public static final String COMMAND_WORD = "change_path";
    public static String path = "/";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Changes the data file path.\n"
            + "Parameters: PATH (system directory)\n"
            + "Example: " + COMMAND_WORD + "/Users/[username]/Documents/TaskManager";

    public static final String MESSAGE_CHANGE_PATH_SUCCESS = "Changed data file location."
            + " You need to restart the app for changes to take effect.";

    public static final String MESSAGE_CHANGE_PATH_FAIL = "Config file at " + path +
            " is not in the correct format. \n" + "Using default config properties";
    private Config initializedConfig;

    public DataCommand(String path) {
        DataCommand.path = path;
    }


    @Override
    public CommandResult execute() throws CommandException {

        try {
            Optional<Config> configOptional = ConfigUtil.readConfig(Config.DEFAULT_CONFIG_FILE);
            initializedConfig = configOptional.orElse(new Config());
            initializedConfig.setTaskManagerFilePath(path);
            ConfigUtil.saveConfig(initializedConfig, Config.DEFAULT_CONFIG_FILE);
        } catch (DataConversionException | IOException e) {
            return new CommandResult(String.format(MESSAGE_CHANGE_PATH_FAIL));
        }

        return new CommandResult(String.format(MESSAGE_CHANGE_PATH_SUCCESS));
    }

}
