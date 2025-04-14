package taskmanager.tasks;

import java.util.ArrayList;

public class Epic extends Task {

    private final ArrayList<Integer> subtaskIds;


    private TaskStatus status;

    public Epic(String taskName, String taskDescription) {
        super(taskName, taskDescription);
        this.subtaskIds = new ArrayList<>();
        this.status = InMemoryTaskManager.checkStatus(this);
    }

    public Epic(int taskId, String taskName, String taskDescription) {
        super(taskId, taskName, taskDescription);
        this.subtaskIds = InMemoryTaskManager.returnEpics().get(taskId).getSubtasks();
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

    void clearSubtasksOfEpic() {
        subtaskIds.clear();
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    @Override
    public String getTaskDescription() {
        return super.getTaskDescription();
    }

    @Override
    public TaskStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "taskId=" + super.getTaskId() +
                ", subtaskIds=" + subtaskIds +
                ", taskName='" + super.getTaskName() + '\'' +
                //", taskDescription.length='" + taskDescription.length() + '\'' +
                ", taskDescription='" + super.getTaskDescription() + '\'' +
                ", status=" + status +
                '}';
    }
}
