package taskmanager.manager.tasks;

import taskmanager.tasks.Epic;
import taskmanager.tasks.Subtask;
import taskmanager.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public interface TaskManager {

    Collection<Task> getTasks();
    void clearTasks();
    Task getTask(int taskId);
    void updateTask(Task task);
    void deleteTask(int taskId);

    Collection<Epic> getEpics();
    void clearEpics();
    Epic getEpic(int taskId);
    void updateEpic(Epic epic);
    void deleteEpic(int taskId);
    ArrayList<Subtask> getSubtasksOfEpic(int epicId);

    Collection<Subtask> getSubtasks();
    void clearSubtasks();
    Subtask getSubtask(int taskId);
    void updateSubtask(Subtask subtask);
    void deleteSubtask(int taskId);

    HashMap<Integer, Task> returnTasks();

    ArrayList<Task> getHistory();
}
