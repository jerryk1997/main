package seedu.address.model.performanceOverview;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Performance;
import seedu.address.model.person.exceptions.DuplicatePerformanceOverviewException;
import seedu.address.model.person.exceptions.PerformanceOverviewNotFoundException;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UniquePerformanceOverviewList implements Iterable<PerformanceOverview> {

    private final ObservableList<PerformanceOverview> internalList = FXCollections.observableArrayList();
    private final ObservableList<PerformanceOverview> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Performance Overview as the given argument.
     */
    public boolean contains(PerformanceOverview toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSamePerformanceOverview);
    }

    /**
     * Adds a Performance overview to the list
     * The performance overview must no already exist in the list.
     * @param toAdd
     */
    public void add(PerformanceOverview toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicatePerformanceOverviewException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the person {@code target} in the list with {@code editedOverview}.
     * {@code target} must exist in the list.
     * The {@code editedOverview} should not be the same as any other overview in the list.
     */
    public void setOverview(PerformanceOverview target, PerformanceOverview editedOverview) {
        requireAllNonNull(target, editedOverview);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new PerformanceOverviewNotFoundException();
        }

        if (!target.isSame)
    }
}
