package task;

import java.util.ArrayList;

public class Epic extends Task {

    private ArrayList<Subtask> subtasks;

    private final int taskId;
    private String taskName;
    private String taskDescription;
    private TaskStatus status;

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription);
        this.taskId = super.taskId;
        subtasks = new ArrayList<>();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        status = TaskStatus.NEW;
    }

    private TaskStatus setStatus() {
        if (subtasks.isEmpty()) return TaskStatus.NEW;

        // надо подумать


        return TaskStatus.IN_PROGRESS;
    }

    public void addNewSubtask(String taskName, String taskDescription) {
        subtasks.add(new Subtask(taskName, taskDescription, this.taskId));
    }
    public void addSubtask(Subtask subtask) {
        if (this.taskId == subtask.getEpicId()) subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }
}
