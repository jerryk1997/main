package seedu.address.model.util;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ProjectList;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyProjectList;
import seedu.address.model.finance.Finance;
import seedu.address.model.person.*;
import seedu.address.model.project.*;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {

    public static Person[] getSamplePersons() {

        ProfilePicture profilePicture = new ProfilePicture("data/empty_profile_picture.png");

        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"), profilePicture,
                    new Address("Blk 30 Geylang Street 29, #06-40"),
                    getTagSet("friends")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"), profilePicture,
                    new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                    getTagSet("colleagues", "friends")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"), profilePicture,
                    new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                    getTagSet("neighbours")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"), profilePicture,
                    new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getTagSet("family")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"), profilePicture,
                    new Address("Blk 47 Tampines Street 20, #17-35"),
                    getTagSet("classmates")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"), profilePicture,
                    new Address("Blk 45 Aljunied Street 85, #11-31"),
                    getTagSet("colleagues"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static List<Task> getTaskList(Task... tasks) {
        ArrayList<Task> list = new ArrayList<>();
        for (Task task : tasks) {
            list.add(task);
        }
        return list;
    }

    public static Project[] getSampleProjects() {
        try {
            return new Project[]{
                new Project(new Title("CS2103T"), new Description("The mod that takes most time"), new ArrayList<String>(),
                            getTaskList(new Task(new Description("Finish GUI"), new Time("04/04/1997 1600"), false),
                                    new Task(new Description("Finish Parser"), new Time("04/04/1997 1600"), true)), new Finance()),
                new Project(new Title("GER1000"), new Description("Free and easy"), new ArrayList<String>(),
                            getTaskList(new Task(new Description("Finish Quiz 10"), new Time("04/04/1997 1600"), false)), new Finance()),
            };
        } catch (ParseException e) {
            return new Project[]{
                new Project(new Title("CS2103T"),

                            new Description("The mod that takes most time"), new ArrayList<String>(),
                            getTaskList(new Task(new Description("Finish GUI"), false)), new Finance())
            };
        }
    }

    public static ReadOnlyProjectList getSampleProjectList() {
        ProjectList samplePl = new ProjectList();
        for (Project project : getSampleProjects()) {
            samplePl.addProject(project);
        }
        return samplePl;
    }
}
