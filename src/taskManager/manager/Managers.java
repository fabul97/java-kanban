package taskManager.manager;

import taskManager.manager.tasks.InMemoryTaskManager;
import taskManager.manager.tasks.TaskManager;

import taskManager.manager.history.HistoryManager;
import taskManager.manager.history.InMemoryHistoryManager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
