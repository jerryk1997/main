package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Performance;
import seedu.address.model.person.Person;
import seedu.address.model.project.Meeting;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_MEETING_DISPLAYED_INDEX;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

public class MarkAttendanceCommand extends Command {

    public static final String COMMAND_WORD = "markAttendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance for meeting specified by the index number used in the displayed meeting list\n"
            + "Parameters: MEETING_INDEX PERSON_INDEX... (INDEX must be positive integer)\n"
            + "Example: " + COMMAND_WORD + "2 1 2 4 (MEETING_INDEX: 2, PERSON_INDEX:1, 2, 4)";

    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Attendance for meeting(%1$s on %2$s) is marked for %3$s)";

    private final List<Index> targetIndexes;

    public MarkAttendanceCommand(List<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Set<Meeting> meetingSet = model.getWorkingProject().get().getListOfMeeting();
        List<Meeting> meetingListShown = meetingSet.stream()
                .sorted(Comparator.comparing(m -> m.getTime().getDate())).collect(Collectors.toList());
        List<String> personNameList = model.getWorkingProject().get().getMemberNames();
        List<Person> personList = new ArrayList<>();
        for (String personName : personNameList) {
            String[] nameKeywords = personName.trim().split("\\s+");

            //Filters the model person list one by one based on each name to find the relevant Person object
            // since project only keeps members as strings
            personList.add(model.getFilteredPersonList().
                    filtered(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords))).get(0));
        }

        Index meetingIndex = targetIndexes.remove(0);

        if (meetingIndex.getZeroBased() >= meetingListShown.size()) {
            throw new CommandException(MESSAGE_INVALID_MEETING_DISPLAYED_INDEX);
        }

        Meeting meeting = meetingListShown.get(meetingIndex.getZeroBased());
        List<Person> personsToMark = new ArrayList<>();
        List<Index> personIndexList = targetIndexes;

        for (Index personIndex : personIndexList) {
            if (personIndex.getZeroBased() >= personList.size()) {
                throw new CommandException(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX + ": " + personIndex.toString());
            }
            personsToMark.add(personList.get(personIndex.getZeroBased()));
        }

        List<Person> markedPersons = markAttendanceOf(personsToMark, meeting);
        setPersons(personsToMark, markedPersons, model);

        return new CommandResult(String.format(MESSAGE_MARK_ATTENDANCE_SUCCESS,
                meeting.getDescription().toString(),
                meeting.getTime().toString(),
                getAsString(markedPersons)), COMMAND_WORD);
    }

    private List<Person> markAttendanceOf(List<Person> personsToMark, Meeting meeting) {
        List<Person> markedPersons = new ArrayList<>();

        for (Person personToMark : personsToMark) {
            Performance previousPerformance = personToMark.getPerformance();
            previousPerformance.getMeetingsAttended().add(meeting);

            Performance updatedPerformance = new Performance(previousPerformance.getMeetingsAttended(), previousPerformance.getTasksAssigned());
            Person editedPerson = new Person(personToMark.getName(), personToMark.getPhone(), personToMark.getEmail(),
                    personToMark.getProfilePicture(), personToMark.getAddress(),
                    personToMark.getTags(), personToMark.getTimeTable(), updatedPerformance);

            markedPersons.add(editedPerson);
        }

        return markedPersons;
    }
}
