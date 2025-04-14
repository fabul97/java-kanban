package taskmanager.tasks;

public class Subtask extends Task {

    private final int epicId;


    public Subtask(String taskName, String taskDescription, int epicId) {
        super(taskName, taskDescription);
        this.epicId = epicId;
    }

    public Subtask(int taskId, String taskName, String taskDescription, TaskStatus status) {
        super(taskId, taskName, taskDescription, status);
        this.epicId = InMemoryTaskManager.returnSubtasks().get(taskId).getEpicId();
        InMemoryTaskManager.updateStatus(InMemoryTaskManager.returnEpics().get(this.epicId));
    }

    @Override
    public String getTaskName() {
        return super.getTaskName();
    }

    @Override
    public String getTaskDescription() {
        return super.getTaskDescription();
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public TaskStatus getStatus() {
        return super.getStatus();
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epicId=" + epicId +
                ", taskId=" + super.getTaskId() +
                ", taskName='" + super.getTaskName() + '\'' +
                //", taskDescription.length='" + taskDescription.length() + '\'' +
                ", taskDescription='" + super.getTaskDescription() + '\'' +
                ", status=" + super.getTaskDescription() +
                '}';
    }
}
