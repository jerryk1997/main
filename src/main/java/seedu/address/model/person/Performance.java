package seedu.address.model.person;

import seedu.address.model.project.Meeting;
import seedu.address.model.project.Task;

import java.util.List;
import java.util.stream.Collectors;

public class Performance {
    private final List<Meeting> meetingsAttended;
    private final List<Task> tasksAssigned;

    public Performance(List<Meeting> meetingsAttended, List<Task> tasksAssigned) {
        this.meetingsAttended = meetingsAttended;
        this.tasksAssigned = tasksAssigned;
    }

    public List<Meeting> getMeetingsAttended() {
        return meetingsAttended;
    }

    public List<Task> getTasksAssigned() {
        return tasksAssigned;
    }


    public int numOfTasksDone() {
        int tasksDone = tasksAssigned.stream()
                .filter(task -> task.isDone())
                .collect(Collectors.toList())
                .size();
        return tasksDone;
    }

    public int numOfMeetingsAttended() {
        return meetingsAttended.size();
    }

    public void removeMeeting(Meeting meeting) {
        meetingsAttended.remove(meetingsAttended.indexOf(meeting));
    }

    public void removeTask(Task task) {
        tasksAssigned.remove(tasksAssigned.indexOf(task));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Performance)) {
            return false;
        }

        Performance otherPerformance = (Performance) other;

        return otherPerformance.meetingsAttended.equals(this.meetingsAttended)
                && otherPerformance.tasksAssigned.equals(this.tasksAssigned);
    }
}
