import task.Epic;
import task.Subtask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        Task task1 = new Task("Первая задача", "Тестовое описание 1");
        Epic epic1 = new Epic("Первый эпик", "Тестовое описание 2");
        Subtask subtask1 = new Subtask("Первая подзадача","Тестовое описание 3", epic1.getTaskId());

        printTaskEpicSub(task1, epic1, subtask1);
        // возможно подзадачи нельзя создавать, можно только приводить задачи к подзадачам сразу в эпик
        epic1.addSubtask(subtask1);
        epic1.addNewSubtask("Вторая подзадача", "Тестовое описание 4");

        printTaskEpicSub(task1, epic1, subtask1);

        System.out.println(epic1.getSubtasks());


    }

    public static void printTaskEpicSub(Task task, Epic epic, Subtask subtask) {
        System.out.println("Task1: id - " + task.getTaskId() + "; name - " + task.getTaskName());
        System.out.println("Task2: id - " + epic.getTaskId() + "; name - " + epic.getTaskName());
        System.out.println("Task3: id - " + subtask.getTaskId() + "; name - " + subtask.getTaskName() + "; epicId - " + subtask.getEpicId());
        System.out.println("--------------------------------------------\n");
    }
}
