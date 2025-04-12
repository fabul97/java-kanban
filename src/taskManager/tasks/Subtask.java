package taskManager.tasks;

import taskManager.manager.tasks.InMemoryTaskManager;

public class Subtask extends Task {

    private final int epicId;

    private final int taskId;
    private final String taskName;
    private final String taskDescription;
    private final TaskStatus status;

    // По ТЗ мы не можем менять поля у экземпляров класса. Мы должны создавать новый экземпляр и удалять старый,
    // и вместо него уже в массив класть новый с тем же task_id. Поэтому я решил сделать поля final.
    // "При обновлении данных можете считать, что на вход подаётся новый объект, который должен полностью заменить
    // старый."

    // "Фраза «информация приходит вместе с информацией по задаче» означает, что не существует отдельного метода,
    // который занимался бы только обновлением статуса задачи. Вместо этого статус задачи обновляется вместе с полным
    // обновлением задачи."

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
        this.epicId = InMemoryTaskManager.returnSubtasks().get(taskId).getEpicId();
        this.taskId = taskId;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.status = status;
        InMemoryTaskManager.returnEpics().get(this.epicId).updateStatus();
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
