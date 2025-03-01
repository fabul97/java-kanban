import taskManager.*;


public class Main {

    public static void main(String[] args) {

        Task task1 = new Task("1 задача", "Тестовое описание 1");
        Task task2 = new Task("2 задача", "Тестовое описание 2");
        Epic epic1 = new Epic("1 эпик", "Тестовое описание 3");
        Epic epic2 = new Epic("2 эпик", "Тестовое описание 4");
        Subtask subtask1 = new Subtask("1 подзадача","Тестовое описание 5", epic1.getTaskId());
        Subtask subtask2 = new Subtask("2 подзадача","Тестовое описание 6", epic1.getTaskId());
        Subtask subtask3 = new Subtask("3 подзадача","Тестовое описание 7", epic2.getTaskId());

        TaskManager taskManager = new TaskManager();

        System.out.println("-----------------------------------------");
        System.out.println(taskManager);
        System.out.println("-----------------------------------------\n");

        TaskManager.updateTask(task1);
        TaskManager.updateTask(task2);

        TaskManager.updateEpic(epic1);
        TaskManager.updateEpic(epic2);

        TaskManager.updateSubtask(subtask1);
        TaskManager.updateSubtask(subtask2);
        TaskManager.updateSubtask(subtask3);

        System.out.println("-----------------------------------------");
        System.out.println(taskManager);
        System.out.println("-----------------------------------------\n");

        System.out.println(TaskManager.getTasks() + "\n");
        System.out.println(TaskManager.getEpics() + "\n");
        System.out.println(TaskManager.getSubtasks() + "\n");

        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n");

        TaskManager.updateTask(new Task(task1.getTaskId(),"1 задача", "Обновление задачи 1", TaskStatus.IN_PROGRESS));
        TaskManager.updateTask(new Task(task2.getTaskId(),"2 задача", "Обновление задачи 2", TaskStatus.DONE));
        TaskManager.updateEpic(new Epic(epic1.getTaskId(), "1 эпик", "Обновление эпика 1"));
        TaskManager.updateEpic(new Epic(epic2.getTaskId(), "2 эпик", "Обновление эпика 2"));
        TaskManager.updateSubtask(new Subtask(subtask1.getTaskId(), "1 подзадача", "Обновление подзадачи 1", TaskStatus.IN_PROGRESS));
        TaskManager.updateSubtask(new Subtask(subtask2.getTaskId(), "2 подзадача", "Обновление подзадачи 2", TaskStatus.DONE));
        TaskManager.updateSubtask(new Subtask(subtask3.getTaskId(), "3 подзадача", "Обновление подзадачи 3", TaskStatus.DONE));

        System.out.println(TaskManager.getTasks() + "\n");
        System.out.println(TaskManager.getEpics() + "\n");
        System.out.println(TaskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");

        TaskManager.deleteTask(task2.getTaskId());
        TaskManager.deleteSubtask(subtask1.getTaskId());
        TaskManager.deleteEpic(epic2.getTaskId());

        System.out.println(TaskManager.getTasks() + "\n");
        System.out.println(TaskManager.getEpics() + "\n");
        System.out.println(TaskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");

        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n");

        TaskManager.clearTasks();
        TaskManager.clearEpics();
        TaskManager.clearSubtasks();

        System.out.println(TaskManager.getTasks() + "\n");
        System.out.println(TaskManager.getEpics() + "\n");
        System.out.println(TaskManager.getSubtasks() + "\n");
        System.out.println("-----------------------------------------\n");
        System.out.println(taskManager);


    }
}
