package guitests;

import org.junit.Test;

import watodo.commons.exceptions.IllegalValueException;
import watodo.logic.commands.ClearCommand;
import watodo.logic.commands.UndoCommand;

//@@author A0119505J
public class UndoCommandTest extends TaskManagerGuiTest {

    @Test
    public void undoOneChange() throws IllegalValueException {
        //undo a change that was never made
        commandBox.runCommand("edit 20 Buy eggs");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_FAILURE);
        //undo add
        commandBox.runCommand("add Attend Pokemon Summit from/02/05/2017 10:00 to/02/05/2017 12:00 p/high t/friends");
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

    @Test
    public void undoDeleteTest() {
        commandBox.runCommand("clear");
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("add Attend Pokemon from/02/05/2017 10:00 to/02/05/2017 12:00 p/high t/friends");
        commandBox.runCommand("delete 1");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }

    @Test
    public void undoMarkTest() {
        commandBox.runCommand("clear");
        assertResultMessage(ClearCommand.MESSAGE_SUCCESS);
        commandBox.runCommand("add Attend Pokemon from/02/05/2017 10:00 to/02/05/2017 12:00 p/high t/friends");
        commandBox.runCommand("mark 1 completed");
        commandBox.runCommand("undo");
        assertResultMessage(UndoCommand.MESSAGE_SUCCESS);
    }
}
