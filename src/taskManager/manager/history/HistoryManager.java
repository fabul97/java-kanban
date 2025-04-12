package taskManager.manager.history;

import taskManager.tasks.Task;

import java.util.ArrayList;

public interface HistoryManager {
    void add(Task task);  // добавляет задачу в историю просмотров
    ArrayList<Task> getHistory();  // возвращает список просмотренных задач
}
