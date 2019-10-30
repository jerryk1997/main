package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Performance;
import seedu.address.model.project.Meeting;
import seedu.address.model.project.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import java.text.ParseException;

public class JsonAdaptedPerformance {

    private final List<JsonAdaptedMeeting> meetingsAttended = new ArrayList<>();
    private final List<JsonAdaptedTask> tasksAssigned = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerformance} with the given performance details.
     */
    @JsonCreator
    public JsonAdaptedPerformance(@JsonProperty("meetingsAttended") List<JsonAdaptedMeeting> meetingsAttended,
                                  @JsonProperty("tasksAssigned") List<JsonAdaptedTask> tasksAssigned) {
        if (meetingsAttended != null) {
            this.meetingsAttended.addAll(meetingsAttended);
        }

        if (tasksAssigned != null) {
            this.tasksAssigned.addAll(tasksAssigned);
        }
    }

    /**
     * Converts a given {@code Performance} into this class for Jackson use.
     */
    public JsonAdaptedPerformance(Performance source) {
        if (source.getMeetingsAttended() != null) {
            meetingsAttended.addAll(source.getMeetingsAttended().stream()
                    .map(JsonAdaptedMeeting::new)
                    .collect(Collectors.toList()));
        }

        if (source.getTasksAssigned() != null) {
            tasksAssigned.addAll(source.getTasksAssigned().stream()
                    .map(JsonAdaptedTask::new)
                    .collect(Collectors.toList()));
        }
    }

    /**
     * Converts this Jackson-friendly Performance object into the model's {@code Performance} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Performance.
     */
    public Performance toModelType() throws IllegalValueException, ParseException {
        final List<Meeting> modelMeetings = new ArrayList<>();
        final List<Task> modelTasks = new ArrayList<>();

        for (JsonAdaptedMeeting meeting : meetingsAttended) {
            modelMeetings.add(meeting.toModelType());
        }

        for (JsonAdaptedTask task : tasksAssigned) {
            modelTasks.add(task.toModelType());
        }

        return new Performance(modelMeetings, modelTasks);
    }
}
