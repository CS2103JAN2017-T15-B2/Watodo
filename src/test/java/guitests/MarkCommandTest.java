package guitests;

import org.junit.Test;

import watodo.logic.commands.ClearCommand;

//@@author A0119505J
public class MarkCommandTest extends TaskManagerGuiTest {
    @Test
    public void assertAddSuccess() {
        commandBox.runCommand("clear");
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("add Attend Pokemon from/02/05/2017 10:00 to/02/05/2017 12:00 p/high t/friends");
        commandBox.runCommand("mark 1 completed");
    }
}
