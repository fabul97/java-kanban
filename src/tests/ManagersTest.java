package tests;

import org.junit.jupiter.api.Test;
import taskManager.manager.Managers;
import taskManager.manager.history.HistoryManager;
import taskManager.manager.tasks.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {

    @Test
    void testGetDefault() {
        TaskManager taskManager = Managers.getDefault();
        assertNotNull(taskManager, "Менеджер задач должен быть инициализирован.");
    }

    @Test
    void testGetDefaultHistory() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager, "Менеджер истории должен быть инициализирован.");
    }
}
