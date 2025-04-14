package taskmanager.manager;

import taskmanager.tasks.InMemoryTaskManager;
import taskmanager.manager.tasks.TaskManager;

import taskmanager.manager.history.HistoryManager;
import taskmanager.manager.history.InMemoryHistoryManager;

public class Managers {

    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
