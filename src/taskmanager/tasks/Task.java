package taskmanager.tasks;

import java.util.Objects;

public class Task {
    private static int countId;
    private final int taskId;
    private final String taskName;
    private final String taskDescription;
    private TaskStatus status;

    public Task(String taskName, String taskDescription) {
        this.taskId = countId++;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = TaskStatus.NEW;
    }

    public Task(int taskId, String taskName, String taskDescription, TaskStatus status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
    }

    public Task(int taskId, String taskName, String taskDescription) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public int getTaskId() {
        return taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskStatus getStatus() {
        return this.status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return (getTaskId() == task.getTaskId());
    }

    // For Tests
    public static void resetCountId() {
        countId = 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTaskId(), getTaskName(), getTaskDescription());
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                //", taskDescription.length='" + taskDescription.length() + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", status=" + status +
                '}';
    }
}
