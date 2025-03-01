package taskManager;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subtaskIds;

    private final int taskId;
    private final String taskName;
    private final String taskDescription;
    private TaskStatus status;

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription);
        this.taskId = super.getTaskId();
        this.subtaskIds = new ArrayList<Integer>();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = checkStatus();
    }

    public Epic(int taskId, String taskName, String taskDescription) {
        super(taskId, taskName, taskDescription);
        this.taskId = taskId;
        this.subtaskIds = TaskManager.epics.get(taskId).getSubtasks();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = checkStatus();
    }

    public void addSubtask(int taskId) {
        if (!subtaskIds.contains(taskId)) subtaskIds.add(taskId);
        this.updateStatus();
    }

    void updateStatus() {
        this.status = checkStatus();
    }

    private TaskStatus checkStatus() {
        if (subtaskIds == null || subtaskIds.isEmpty()) return TaskStatus.NEW;

        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : TaskManager.subtasks.values()) {
            if (subtask.getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
            if (subtask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            return TaskStatus.NEW;
        }
        if (allDone) {
            return TaskStatus.DONE;
        }

        return TaskStatus.IN_PROGRESS;
    }

    public ArrayList<Integer> getSubtasks() {
        return subtaskIds;
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String getTaskDescription() {
        return taskDescription;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "taskId=" + taskId +
                ", subtaskIds=" + subtaskIds +
                ", taskName='" + taskName + '\'' +
                //", taskDescription.length='" + taskDescription.length() + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
