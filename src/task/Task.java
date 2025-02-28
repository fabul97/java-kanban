package task;

public class Task {
    protected static int countId;
    protected int taskId;
    protected String taskName;
    protected String taskDescription;
    protected TaskStatus status;

    public Task(String taskName, String taskDescription) {
        this.taskId = countId++;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public int getTaskId() {
        return taskId;
    }

    public static int getCountId() {
        return countId;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public TaskStatus getStatus() {
        return status;
    }
}
