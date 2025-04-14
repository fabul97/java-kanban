package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskmanager.manager.Managers;
import taskmanager.manager.tasks.TaskManager;
import taskmanager.tasks.Epic;
import taskmanager.tasks.Subtask;
import taskmanager.tasks.Task;
import taskmanager.tasks.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    @BeforeEach
    void testResetAll() {
        Subtask.resetCountId();
    }

    @Test
    void testCreateSubtask() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        assertEquals(1, subtask.getTaskId(), "Счетчик ID задач должен отличаться на 1 от ID предыдущей " +
                "задачи.");
        assertEquals("1 подзадача", subtask.getTaskName(), "У созданной подзадачи неправильное " +
                "название задачи.");
        assertEquals("Тестовое описание 2", subtask.getTaskDescription(), "У созданной подзадачи " +
                "неправильное описание.");
        assertEquals(TaskStatus.NEW, subtask.getStatus(), "У созданной подзадачи должен быть статус NEW.");
        assertEquals(TaskStatus.NEW, epic.getStatus(), "Если подзадача Эпика имеет статус NEW, то и у эпика " +
                "должен быть статус NEW.");
    }

    @Test
    void testAddSubtaskToTaskManager() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        assertNotNull(taskManager.getSubtask(subtask.getTaskId()), "Подзадача должна добавляться " +
                "в TaskManager.");
    }

    @Test
    void testGetEpicIdFromSubtask() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        assertEquals(epic.getTaskId(), subtask.getEpicId(), "Подзадача должна хранить ID родительского эпика.");
    }

    @Test
    void testUpdateSubtaskStatus() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);
        taskManager.updateSubtask(new Subtask(subtask.getTaskId(), "1 подзадача", "Обновление " +
                "описания подзадачи 1", TaskStatus.IN_PROGRESS));

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getSubtask(subtask.getTaskId()).getStatus(),
                "Подзадача должна обновить статус до IN_PROGRESS.");

        taskManager.updateSubtask(new Subtask(subtask.getTaskId(), "1 подзадача", "Обновление " +
                "описания подзадачи 2", TaskStatus.DONE));

        assertEquals(TaskStatus.DONE, taskManager.getSubtask(subtask.getTaskId()).getStatus(), "Подзадача " +
                "должна обновить статус до DONE.");
    }

    @Test
    void testDeleteSubtaskById() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        taskManager.deleteSubtask(subtask.getTaskId());

        assertNull(taskManager.getSubtask(subtask.getTaskId()), "Подзадача должна удалиться из TaskManager.");
    }

    @Test
    void testSubtaskEqualityById() {
        Task epic1 = new Epic("1 Эпик", "Тестовое описание 1");
        Task subtask1 = new Subtask("1 подзадача", "Тестовое описание 2", epic1.getTaskId());
        Task subtask2 = subtask1;

        assertEquals(subtask1, subtask2, "Подзадачи с одинаковыми id должны быть равны.");
    }
}
