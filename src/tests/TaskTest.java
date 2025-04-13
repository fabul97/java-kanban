package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskManager.manager.Managers;
import taskManager.manager.tasks.TaskManager;
import taskManager.tasks.*;


import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    @BeforeEach
    void testResetAll() {
        Task.resetCountId();
    }

    @Test
    void testCreateTask() {
        Task task = new Task("1 задача", "Тестовое описание 1");

        assertEquals(0, task.getTaskId(), "Счетчик ID задач должен начинаться с 0.");
        assertEquals("1 задача", task.getTaskName(), "У созданной задачи неправильное название задачи.");
        assertEquals("Тестовое описание 1", task.getTaskDescription(), "У созданной задачи неправильное описание.");
        assertEquals(TaskStatus.NEW, task.getStatus(), "у созданной задачи должен быть статус NEW.");
    }

    @Test
    void testAddTaskToTaskManager() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("1 задача", "Тестовое описание 1");
        taskManager.updateTask(task);

        assertNotNull(taskManager.getTask(task.getTaskId()), "Задача должна добавляться в TaskManager.");
    }

    @Test
    void testUpdateSubtaskStatus() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("1 задача", "Тестовое описание 1");
        taskManager.updateTask(task);

        taskManager.updateTask(new Task(task.getTaskId(), "1 задача", "Обновление задачи 1", TaskStatus.IN_PROGRESS));

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getTask(task.getTaskId()).getStatus(), "Задача должна обновить статус до IN_PROGRESS.");

        taskManager.updateTask(new Task(task.getTaskId(), "1 задача", "Обновление задачи 2", TaskStatus.DONE));

        assertEquals(TaskStatus.DONE, taskManager.getTask(task.getTaskId()).getStatus(), "Задача должна обновить статус до DONE.");
    }

    @Test
    void testDeleteTaskById() {
        TaskManager taskManager = Managers.getDefault();
        Task task = new Task("1 задача", "Тестовое описание 1");
        taskManager.updateTask(task);

        taskManager.deleteTask(task.getTaskId());

        assertNull(taskManager.getTask(task.getTaskId()), "Задача должна удалиться из TaskManager.");
    }

    @Test
    void testTaskEqualityById() {
        Task task1 = new Task("1 задача", "Тестовое описание 1");
        Task task2 = task1;

        assertEquals(task1, task2, "Задачи с одинаковыми id должны быть равны.");
    }
}