package taskManager.tasks;

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
        this.subtaskIds = new ArrayList<>();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = InMemoryTaskManager.checkStatus(this);
    }

    public Epic(int taskId, String taskName, String taskDescription) {
        super(taskId, taskName, taskDescription);
        this.taskId = taskId;
        this.subtaskIds = InMemoryTaskManager.returnEpics().get(taskId).getSubtasks();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = InMemoryTaskManager.checkStatus(this);
    }

    void addSubtask(int taskId) {
        if (!subtaskIds.contains(taskId)) subtaskIds.add(taskId);
        this.status = InMemoryTaskManager.checkStatus(this);
    }

    void removeSubtask(int taskId) {
        // Безопасное удаление
        subtaskIds.removeIf(integer -> integer == taskId);
    }

    void setStatus(TaskStatus status) {
        this.status = status;
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
