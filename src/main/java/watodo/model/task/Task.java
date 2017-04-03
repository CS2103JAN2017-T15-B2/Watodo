package watodo.model.task;

import java.util.Objects;

import watodo.commons.util.CollectionUtil;
import watodo.model.tag.UniqueTagList;

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
    public Task(Name name, Time startTime, Priority priority, UniqueTagList tags, Status status) {
        this(name, startTime, null, priority, tags, status);

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
    /**
     * Check whether this {@code Task} is todo.
     * @return {@code true} if and only if this {@code Task} has neither startTime nor endTime.
     */
    public boolean isTodo() {
        return startTime == null && endTime == null;
    }

    /**
     * Check whether this {@code Task} is deadline.
     * @return {@code true} if and only if this {@code Task} has endTime but not startTime.
     */
    public boolean isDeadline() {
        return startTime == null && endTime != null;
    }

    /**
     * Check whether this {@code Task} is an event.
     * @return {@code true} if and only if this {@code Task} has both startTime and endTime.
     */
    public boolean isEvent() {
        return startTime != null && endTime != null;
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
