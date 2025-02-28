package task;

public class Subtask extends Task {

    private final int epicId;

        private final int taskId;
        private String taskName;
        private String taskDescription;
        private TaskStatus status;

    public Subtask(String taskName, String taskDescription, int epicId) {
        super(taskName, taskDescription);
        this.epicId = epicId;
        this.taskId = super.taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        status = TaskStatus.NEW;

    }

    private void setToEpic(Epic epic) {
        // брать эпик из таск менеджера
        epic.addSubtask(this);
    }

    public int getEpicId() {
        return epicId;
    }


}
