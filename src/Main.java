import taskManager.*;


public class Main {

    public static void main(String[] args) {

        Task task1 = new Task("1 задача", "Тестовое описание 1");
        Epic epic1 = new Epic("1 эпик", "Тестовое описание 2");
        Epic epic2 = new Epic("2 эпик", "Тестовое описание 3");
        Subtask subtask1 = new Subtask("1 подзадача","Тестовое описание 4", epic1.getTaskId());
        Subtask subtask2 = new Subtask("2 подзадача","Тестовое описание 5", epic1.getTaskId());

        TaskManager taskManager = new TaskManager();

        System.out.println(taskManager);


        taskManager.updateTask(task1);

        taskManager.updateEpic(epic1);
        taskManager.updateEpic(epic2);

        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(subtask2);

        System.out.println("-----------------------------------------");
        taskManager.printTasks();
        taskManager.printEpics();
        taskManager.printSubtasks();
        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n\n\n");


        taskManager.updateTask(new Task(task1.getTaskId(),"1 задача", "Обновление задачи 1", TaskStatus.IN_PROGRESS));
        taskManager.updateEpic(new Epic(epic2.getTaskId(), "2 эпик", "Обновление эпика 1"));
        taskManager.updateSubtask(new Subtask(subtask2.getTaskId(), "2 подзадача", "Обновление подзадачи 1", TaskStatus.DONE));



        System.out.println("-----------------------------------------");
        taskManager.printTasks();
        taskManager.printEpics();
        taskManager.printSubtasks();
        System.out.println("-----------------------------------------");
        System.out.println(taskManager.getSubtasksOfEpic(epic1.getTaskId()));
        System.out.println("-----------------------------------------\n");
    }

    public static void printTaskEpicSub(TaskManager taskManager) {
        System.out.println(taskManager);
    }
}
