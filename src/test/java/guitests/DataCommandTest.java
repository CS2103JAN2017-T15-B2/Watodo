package guitests;

import org.junit.Test;

import watodo.commons.core.Messages;

//@@author A0119505J
public class DataCommandTest extends TaskManagerGuiTest {

    @Test
    public void data_invalidCommand_fail() {
        commandBox.runCommand("changepath");
        assertResultMessage(Messages.MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void assertDataCommandSuccess() {
        commandBox.runCommand("change_path data");
        assertResultMessage("Changed data file location. You need to restart the app for changes to take effect.");
    }
}

