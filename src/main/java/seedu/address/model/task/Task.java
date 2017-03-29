package seedu.address.model.task;

import java.time.LocalTime;
import java.util.Objects;

import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.tag.UniqueTagList;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated.
 */
/**
 * @author Darius
 *
 */
public class Task implements ReadOnlyTask {

    private Name name;
    private Time time;
    private ClockTime clockTime;
    private LocalTime startTime;
    private LocalTime endTime;
    private Address address;
    private Status status;
    private UniqueTagList tags;
    private Priority priority;


    /**
     * constructing a task without a specified end time.
     * @param
     */
    public Task(Name name, Time time, ClockTime clockTime, Priority priority, UniqueTagList tags, Status status) {
        this(name, time, clockTime, null, priority, tags, status);

    }

    /**
     * Constructor with all parameters, allowing null inputs.
     * @param
     */

    public Task(Name name, Time time, ClockTime clockTime, LocalTime endTime,
        Priority priority, UniqueTagList tags, Status status) {
        assert !CollectionUtil.isAnyNull(name, time, priority, tags, status);
        this.name = name;
        this.time = time;
        this.clockTime = clockTime;
        this.endTime = endTime;
        this.priority = priority;
        this.tags = new UniqueTagList(tags); // protect internal tags from changes in the arg list
        this.status = status;
    }


    /**
     * Creates a copy of the given ReadOnlyTask.
     */
    public Task(ReadOnlyTask source) {

        this(source.getName(), source.getTime(), source.getClockTime(), source.getEndTime(),
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

    public void setTime(Time time) {
        assert time != null;
        this.time = time;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        assert priority != null;
        this.priority = priority;
    }

    @Override
    public Time getTime() {
        return time;
    }

    //@@author A0143873Y
    public void setClockTime(ClockTime clockTime) {
        assert clockTime != null;
        this.clockTime = clockTime;
    }

    public ClockTime getClockTime() {
        return clockTime;
    }

    public void setStartTime(LocalTime startTime) {
        assert startTime != null;
        this.startTime = startTime;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setEndTime(LocalTime endTime) {
        assert endTime != null;
        this.endTime = endTime;
    }

    public LocalTime getEndTime() {
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
        this.setTime(replacement.getTime());
        this.setClockTime(replacement.getClockTime());
        //this.setAddress(replacement.getAddress());
        this.setTags(replacement.getTags());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ReadOnlyTask // instanceof handles nulls
                && this.isSameStateAs((ReadOnlyTask) other));
    }


    public boolean isFloating() {
        return (this.clockTime != null && this.endTime != null); //eventually will startTime will replace clockTime.
    }

    public boolean isDeadline() {
        return (this.endTime != null && this.clockTime == null);
    }

    public boolean isEvent() {
        return (this.clockTime != null && this.endTime != null);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, time, clockTime, address, tags);
    }

    @Override
    public String toString() {
        return getAsText();
    }

}
