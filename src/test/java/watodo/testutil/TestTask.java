/* @@author A0119505J */
package watodo.testutil;

import watodo.model.tag.UniqueTagList;
import watodo.model.task.Name;
import watodo.model.task.Priority;
import watodo.model.task.ReadOnlyTask;
import watodo.model.task.Status;
import watodo.model.task.Time;

/**
 * A mutable task object. For testing only.
 */
public class TestTask implements ReadOnlyTask {

    private Name name;
    private Time endTime;
    private Time startTime;
    private Priority priority;
    private UniqueTagList tags;
    private Status status;

    public TestTask() {
        tags = new UniqueTagList();
    }

    /**
     * Creates a copy of {@code taskToCopy}.
     */
    public TestTask(TestTask taskToCopy) {
        this.name = taskToCopy.getName();
        this.startTime = taskToCopy.getStartTime();
        this.endTime = taskToCopy.getEndTime();
        this.priority = taskToCopy.getPriority();
        this.tags = taskToCopy.getTags();
        this.status = taskToCopy.getStatus();
    }

    public void setName(Name name) {
        this.name = name;
    }

    //@@author A0119505J
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void setTags(UniqueTagList tags) {
        this.tags = tags;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public Name getName() {
        return name;
    }

    //@@author A0143873Y
    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }
    public Time getStartTime() {
        return startTime;
    }

    public void setEndTime(Time endTime) {
        assert endTime != null;
        this.endTime = endTime;
    }

    public Time getEndTime() {
        return endTime;
    }
    //@@author
    //@@author A0119505J

    public Priority getPriority() {
        return priority;
    }

    @Override
    public UniqueTagList getTags() {
        return tags;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return getAsText();
    }

    public String getAddCommand() {
        StringBuilder sb = new StringBuilder();
        sb.append("add " + this.getName().fullName + " ");
        sb.append("from/" + this.getStartTime().value + " ");
        sb.append("to/" + this.getEndTime().toString() + " ");
        sb.append("p/" + this.getPriority().priorityLevel + " ");
        this.getTags().asObservableList().stream().forEach(s -> sb.append("t/" + s.tagName + " "));
        return sb.toString();
    }

    @Override
    public boolean isTodo() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isDeadline() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean isEvent() {
        // TODO Auto-generated method stub
        return false;
    }
}
