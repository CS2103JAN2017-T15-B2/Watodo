//@@author A0119505J
package guitests;

import org.junit.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.UndoCommand;

public class UndoCommandTest extends TaskManagerGuiTest {

    @Test
    public void undoOneChange() throws IllegalValueException {
        //undo a change that was never made
        commandBox.runCommand("edit 20 Buy eggs");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAILURE);
        //undo one change
        commandBox.runCommand("add attend yoga session d/01/01/2001 c/01:00 to/02:00 p/high t/friends");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAILURE);
    }

    @Test
    public void undoClearTest() {
        commandBox.runCommand("clear");
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }

}
