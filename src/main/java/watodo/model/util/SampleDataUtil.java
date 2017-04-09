package watodo.model.util;

import watodo.commons.exceptions.IllegalValueException;
import watodo.model.ReadOnlyTaskManager;
import watodo.model.TaskManager;
import watodo.model.tag.UniqueTagList;
import watodo.model.task.Name;
import watodo.model.task.Priority;
import watodo.model.task.Status;
import watodo.model.task.Task;
import watodo.model.task.Time;
import watodo.model.task.UniqueTaskList.DuplicateTaskException;

public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        try {
            return new Task[] {
                new Task(new Name("Initial Sample Task"), new Time("17/02/2017 10:00"),
                        new Time("17/02/2017 23:59"), new Priority("high"), new UniqueTagList("friends"), new Status(0))

            };
        } catch (IllegalValueException e) {
            throw new AssertionError("sample data cannot be invalid", e);
        }
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        try {
            TaskManager sampleAB = new TaskManager();
            for (Task sampleTask : getSampleTasks()) {
                sampleAB.addTask(sampleTask);
            }
            return sampleAB;
        } catch (DuplicateTaskException e) {
            throw new AssertionError("sample data cannot contain duplicate Tasks", e);
        }
    }
}
