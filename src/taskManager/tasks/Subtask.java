package taskManager.tasks;

import taskManager.manager.TaskManager;

public class Subtask extends Task {

    private final int epicId;

        private final int taskId;
        private final String taskName;
        private final String taskDescription;
        private final TaskStatus status;

    public Subtask(String taskName, String taskDescription, int epicId) {
        super(taskName, taskDescription);
        this.epicId = epicId;
        this.taskId = super.getTaskId();
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = TaskStatus.NEW;
    }

    public Subtask(int taskId, String taskName, String taskDescription, TaskStatus status) {
        super(taskId, taskName, taskDescription, status);
        this.epicId = TaskManager.returnSubtasks().get(taskId).getEpicId();
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        TaskManager.returnEpics().get(this.epicId).updateStatus();
    }

    @Override
    public String getTaskName() {
        return taskName;
    }

    @Override
    public String getTaskDescription() {
        return taskDescription;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                //", taskDescription.length='" + taskDescription.length() + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
