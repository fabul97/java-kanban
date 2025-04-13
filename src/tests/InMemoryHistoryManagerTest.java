package tests;

import org.junit.jupiter.api.Test;
import taskManager.manager.Managers;
import taskManager.manager.history.HistoryManager;
import taskManager.manager.tasks.TaskManager;
import taskManager.tasks.Task;
import taskManager.tasks.TaskStatus;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    @Test
    void testAddToHistory() {
        Task task = new Task("Test Task", "Test Task description");

        HistoryManager historyManager = Managers.getDefaultHistory();

        historyManager.add(task);

        assertEquals(1, historyManager.getHistory().size(), "История должна содержать 1 задачу.");
        assertEquals(task, historyManager.getHistory().getFirst(), "Задача в истории должна совпадать с добавленной.");
    }

    @Test
    void testHistorySizeLimit() {
        HistoryManager historyManager = Managers.getDefaultHistory();

        for (int i = 1; i <= 15; i++) {
            Task task = new Task("Задача " + i, "Описание " + i);
            historyManager.add(task);
        }

        assertEquals(10, historyManager.getHistory().size(), "История должна содержать не более 10 задач.");
    }

    @Test
    void testHistoryPreservesOriginalTaskData() {

        Task task = new Task("1 задача", "Тестовое описание 1");

        TaskManager taskManager = Managers.getDefault();
        taskManager.updateTask(task);

        taskManager.getTask(task.getTaskId());

        taskManager.updateTask(new Task(task.getTaskId(), "1 задача", "Обновленное описание 1", TaskStatus.IN_PROGRESS));

        taskManager.getTask(task.getTaskId());

        assertEquals("Тестовое описание 1", taskManager.getHistory().get(0).getTaskDescription(), "История должна хранить переданное состояние задачи.");
        assertEquals("Обновленное описание 1", taskManager.getHistory().get(1).getTaskDescription(), "История должна хранить оригинальное состояние задачи.");
    }

}
