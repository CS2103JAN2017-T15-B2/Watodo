package seedu.address.model.task;

import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated.
 */

public class Task implements ReadOnlyTask {

    private Name name;
    private Time startTime;
    private Time endTime;
    private Status status;
    private UniqueTagList tags;
    private Priority priority;


    /**
     * constructing a task without a specified end time.
     * @param
     */
    public Task(Name name, Time StartTime, Priority priority, UniqueTagList tags, Status status) {
        this(name, StartTime, null, priority, tags, status);

    }

    /**
     * Constructor with all parameters, allowing null inputs.
     * @param
     */

    public Task(Name name, Time startTime, Time endTime,
        Priority priority, UniqueTagList tags, Status status) {

        assert !CollectionUtil.isAnyNull(name, startTime, priority, tags, status);
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
        this.status = status;
    }


    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {

        this(source.getName(), source.getStartTime(), source.getEndTime(),
             source.getPriority(), source.getTags(), source.getStatus());
    }

    public void setName(Name name) {
        assert name != null;
        this.name = name;
    }

    @Override
    public Name getName() {
        return name;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        assert priority != null;
        this.priority = priority;
    }

    //@@author A0143873Y
    public void setTime(Time endTime) {
        assert endTime != null;
        this.endTime = endTime;
    }

    public Time getTime() {
        return endTime;
    }

    public void setStartTime(Time startTime) {
        assert startTime != null;
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

    @Override
    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }

    /**
     * Replaces this task's tags with the tags in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        tags.setTags(replacement);
    }

    public void setStatus(Status status) {
        assert status.status != this.status.status;
        this.status = status;
    }

    @Override
    public Status getStatus() {
        return status;
    }

    /**
     * Updates this task with the details of {@code replacement}.
     */
    public void resetData(ReadOnlyTask replacement) {
        assert replacement != null;

        this.setName(replacement.getName());
        this.setStartTime(replacement.getStartTime());
        this.setEndTime(replacement.getEndTime());
        this.setPriority(replacement.getPriority());
        //this.setAddress(replacement.getAddress());
        this.setTags(replacement.getTags());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }

    //@@author A0143873Y
    public boolean isFloating() {
        return (this.endTime != null && this.endTime != null); //eventually will startTime will replace endTime.
    }

    public boolean isDeadline() {
        return (this.endTime != null && this.endTime == null);
    }

    public boolean isEvent() {
        return (this.endTime != null && this.endTime != null);
    }
    //@@author

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, startTime, endTime, priority, tags, status);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
