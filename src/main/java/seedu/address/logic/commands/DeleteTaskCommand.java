package seedu.address.logic.commands;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Project;
import seedu.address.model.project.Task;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PROJECTS;

/**
 * Deletes a task field of a project
 */
public class DeleteTaskCommand extends Command {
    public static final String COMMAND_WORD = "deleteTask";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes this task from the current project "
            + ": Deletes the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";


    public static final String MESSAGE_DELETE_TASK_SUCCESS = "Deleted Task: %1$s from current project.";


    public final Index index;

    public DeleteTaskCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }


    @Override
    public CommandResult execute(Model model) throws CommandException {

        requireNonNull(model);
        Project projectToEdit = model.getWorkingProject().get();
        Set<Task> taskToEdit = projectToEdit.getTasks();

        if (index.getZeroBased() >= taskToEdit.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Set<Task> newTaskList = new HashSet<>();
        ArrayList<Task> taskList = new ArrayList<>();
        taskList.addAll(taskToEdit);
        Task task = taskList.remove(index.getZeroBased());
        newTaskList.addAll(taskList);

        Project editedProject = new Project(projectToEdit.getTitle(), projectToEdit.getDescription(), newTaskList);


        model.setProject(projectToEdit, editedProject);
        model.updateFilteredProjectList(PREDICATE_SHOW_ALL_PROJECTS);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS, task));
    }


    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return index.equals(e.index);
    }
}