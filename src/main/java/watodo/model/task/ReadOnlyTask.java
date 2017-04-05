package watodo.model.task;

import watodo.model.tag.UniqueTagList;

/**
 * A read-only immutable interface for a Task in the task manager.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyTask {

    Name getName();
    Time getStartTime();
    Time getEndTime();
    Status getStatus();
    Priority getPriority();

    /**
     * The returned TagList is a deep copy of the internal TagList,
     * changes on the returned list will not affect the task's internal tags.
     */
    UniqueTagList getTags();

    /**
     * Returns true if both have the same state. (interfaces cannot override .equals)
     */
    default boolean isSameStateAs(ReadOnlyTask other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                && other.getName().equals(this.getName()) // state checks here onwards
                && other.getStartTime().equals(this.getStartTime())
                && other.getEndTime().equals(this.getEndTime())
                );
    }

    //@@author A0130138U
    /**
     * Check whether this {@code Task} is todo.
     * @return {@code true} if and only if this {@code Task} has neither startTime nor endTime.
     */
    public boolean isTodo();

    /**
     * Check whether this {@code Task} is deadline.
     * @return {@code true} if and only if this {@code Task} has endTime but not startTime.
     */
    public boolean isDeadline();

    /**
     * Check whether this {@code Task} is an event.
     * @return {@code true} if and only if this {@code Task} has both startTime and endTime.
     */
    public boolean isEvent();
    //@@author


    /**
     * Formats the task as text, showing all contact details.
     */
    default String getAsText() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" StartTime: ")
                .append(getStartTime())
                .append(" endTime: ")
                .append(getEndTime())
                .append(" Priority: ")
                .append(getPriority())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
