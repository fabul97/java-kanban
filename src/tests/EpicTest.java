package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import taskManager.manager.Managers;
import taskManager.manager.tasks.TaskManager;
import taskManager.tasks.Epic;
import taskManager.tasks.Subtask;
import taskManager.tasks.Task;
import taskManager.tasks.TaskStatus;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @BeforeEach
    void testResetAll() {
        Subtask.resetCountId();
    }

    @Test
    void testCreateSubtask() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");

        assertEquals(0, epic.getTaskId(), "Счетчик ID задач должен начинаться с 0.");
        assertEquals("1 эпик", epic.getTaskName(), "У созданной задачи неправильное название " +
                "задачи.");
        assertEquals("Тестовое описание 1", epic.getTaskDescription(), "У созданной задачи " +
                "неправильное описание.");
        assertEquals(TaskStatus.NEW, epic.getStatus(), "У созданной задачи должен быть статус NEW.");
    }

    @Test
    void testAddEpicToTaskManager() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);

        assertNotNull(taskManager.getEpic(epic.getTaskId()), "Эпик должен добавляться в TaskManager.");
    }

    @Test
    void testGetSubtaskListFromEpic() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        assertNotNull(taskManager.getEpic(epic.getTaskId()).getSubtasks(), "Список подзадач в эпике " +
                "не должен быть пустым после добавления подзадачи к эпику.");
    }

    @Test
    void testUpdateSubtaskStatus() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");
        Subtask subtask = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());
        Subtask subtask1 = new Subtask("1 подзадача","Тестовое описание 2", epic.getTaskId());

        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);
        taskManager.updateSubtask(subtask);

        assertEquals(TaskStatus.NEW, taskManager.getEpic(epic.getTaskId()).getStatus(),
                "Статус должен оставаться NEW при добавлении подзадачи со статусом NEW.");

        taskManager.updateSubtask(new Subtask(subtask.getTaskId(), "1 подзадача", "Обновление " +
                "описания подзадачи 1", TaskStatus.IN_PROGRESS));

        assertEquals(TaskStatus.IN_PROGRESS, taskManager.getEpic(epic.getTaskId()).getStatus(),
                "Эпик должен обновить статус до IN_PROGRESS, если добавлена подзадача со статусом " +
                        "IN_PROGRESS.");

        taskManager.updateSubtask(subtask1);
        taskManager.updateSubtask(new Subtask(subtask.getTaskId(), "1 подзадача", "Обновление " +
                "описания подзадачи 1", TaskStatus.DONE));

        assertNotEquals(TaskStatus.DONE, taskManager.getEpic(epic.getTaskId()).getStatus(),
                "Эпик не должен менять статус на DONE, если хотя бы одна подзадача не имеет статуса DONE.");

        taskManager.updateSubtask(new Subtask(subtask1.getTaskId(), "1 подзадача", "Обновление " +
                "описания подзадачи 2", TaskStatus.DONE));

        assertEquals(TaskStatus.DONE, taskManager.getEpic(epic.getTaskId()).getStatus(), "Эпик " +
                "должен обновить статус до DONE, если все его подзадачи имеют статус DONE.");
    }

    @Test
    void testDeleteEpicById() {
        Epic epic = new Epic("1 эпик", "Тестовое описание 1");


        TaskManager taskManager = Managers.getDefault();

        taskManager.updateEpic(epic);


        taskManager.deleteEpic(epic.getTaskId());

        assertNull(taskManager.getSubtask(epic.getTaskId()), "Эпик должен удалиться из TaskManager.");
    }

    @Test
    void testEpicEqualityById() {
        Task epic1 = new Epic("1 Эпик", "Тестовое описание 1");
        Task epic2 = epic1;

        assertEquals(epic1, epic2, "Эпики с одинаковыми id должны быть равны.");
    }
}
