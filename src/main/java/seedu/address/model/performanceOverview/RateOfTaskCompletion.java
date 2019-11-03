package seedu.address.model.performanceOverview;

import seedu.address.model.project.Task;

import java.util.ArrayList;
import java.util.List;

public class RateOfTaskCompletion {
    private final List<Task> taskList = new ArrayList<>();

    public RateOfTaskCompletion(List<Task> taskList) {
        this.taskList.addAll(taskList);
    }

    private String getCompletionRate() {
        if (taskList.isEmpty()) {
            //Rate of completion is 0 if no task is done or assigned
            return "0";
        }

        double numOfTasksAssigned = taskList.size();
        long numOfCompletedTasks = taskList.stream().filter(task -> task.isDone).count();

        double completionRate = (numOfCompletedTasks/numOfTasksAssigned) * 100;
        return String.format("%.1d", completionRate);
    }
}
