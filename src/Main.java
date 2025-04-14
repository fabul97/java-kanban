import taskmanager.manager.*;
import taskmanager.manager.tasks.TaskManager;
import taskmanager.tasks.*;

public class Main {

    public static void main(String[] args) {

        Task task1 = new Task("1 задача", "Тестовое описание 1");
        Task task2 = new Task("2 задача", "Тестовое описание 2");
        Epic epic1 = new Epic("1 эпик", "Тестовое описание 3");
        Epic epic2 = new Epic("2 эпик", "Тестовое описание 4");
        Subtask subtask1 = new Subtask("1 подзадача","Тестовое описание 5", epic1.getTaskId());
        Subtask subtask2 = new Subtask("2 подзадача","Тестовое описание 6", epic1.getTaskId());
        Subtask subtask3 = new Subtask("3 подзадача","Тестовое описание 7", epic2.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        System.out.println("-----------------------------------------");
        System.out.println(taskManager);
        System.out.println("-----------------------------------------\n");

        taskManager.updateTask(task1);
        taskManager.updateTask(task2);

        taskManager.updateEpic(epic1);
        taskManager.updateEpic(epic2);

        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);
        taskManager.updateSubtask(subtask3);

        System.out.println("-----------------------------------------");
        System.out.println(taskManager);
        System.out.println("-----------------------------------------\n");

        System.out.println(taskManager.getTasks() + "\n");
        System.out.println(taskManager.getEpics() + "\n");
        System.out.println(taskManager.getSubtasks() + "\n");

        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n");

        taskManager.updateTask(new Task(task1.getTaskId(), "1 задача", "Обновление задачи 1", TaskStatus.IN_PROGRESS));
        taskManager.updateTask(new Task(task2.getTaskId(), "2 задача", "Обновление задачи 2", TaskStatus.DONE));
        taskManager.updateEpic(new Epic(epic1.getTaskId(), "1 эпик", "Обновление эпика 1"));
        taskManager.updateEpic(new Epic(epic2.getTaskId(), "2 эпик", "Обновление эпика 2"));
        taskManager.updateSubtask(new Subtask(subtask1.getTaskId(), "1 подзадача", "Обновление подзадачи 1", TaskStatus.IN_PROGRESS));
        taskManager.updateSubtask(new Subtask(subtask2.getTaskId(), "2 подзадача", "Обновление подзадачи 2", TaskStatus.DONE));
        taskManager.updateSubtask(new Subtask(subtask3.getTaskId(), "3 подзадача", "Обновление подзадачи 3", TaskStatus.DONE));

        System.out.println(taskManager.getTasks() + "\n");
        System.out.println(taskManager.getEpics() + "\n");
        System.out.println(taskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");

        taskManager.getTask(task1.getTaskId());
        taskManager.getSubtask(subtask1.getTaskId());
        taskManager.getEpic(epic1.getTaskId());
        taskManager.getTask(task2.getTaskId());
        taskManager.getTask(task1.getTaskId());
        taskManager.getSubtask(subtask1.getTaskId());
        taskManager.getEpic(epic1.getTaskId());
        taskManager.getTask(task2.getTaskId());
        taskManager.getTask(task1.getTaskId());
        System.out.println("История просмотров задач:");
        System.out.println(taskManager.getHistory());
        taskManager.getSubtask(subtask1.getTaskId());
        System.out.println("История просмотров задач:");
        System.out.println(taskManager.getHistory());
        taskManager.getSubtask(subtask1.getTaskId());
        System.out.println("История просмотров задач:");
        System.out.println(taskManager.getHistory());
        taskManager.getSubtask(subtask1.getTaskId());

        System.out.println("-----------------------------------------\n");

        System.out.println("История просмотров задач:");
        System.out.println(taskManager.getHistory());
        System.out.println("-----------------------------------------\n");

        taskManager.deleteTask(task2.getTaskId());
        taskManager.deleteSubtask(subtask1.getTaskId());
        taskManager.deleteEpic(epic2.getTaskId());

        System.out.println(taskManager.getTasks() + "\n");
        System.out.println(taskManager.getEpics() + "\n");
        System.out.println(taskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");

        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n");

        taskManager.clearTasks();
        taskManager.clearEpics();
        taskManager.clearSubtasks();

        System.out.println(taskManager.getTasks() + "\n");
        System.out.println(taskManager.getEpics() + "\n");
        System.out.println(taskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");
        System.out.println(taskManager);
    }
}
