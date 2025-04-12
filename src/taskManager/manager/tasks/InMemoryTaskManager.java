package taskManager.manager.tasks;

import taskManager.manager.Managers;

import taskManager.manager.history.HistoryManager;
import taskManager.tasks.Epic;
import taskManager.tasks.Subtask;
import taskManager.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    private final static HashMap<Integer, Task> tasks = new HashMap<>();
    private final static HashMap<Integer, Epic> epics = new HashMap<>();
    private final static HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager history = Managers.getDefaultHistory();

    @Override
    public Collection<Task> getTasks() {
        return tasks.values();
    }

    @Override
    public void clearTasks() {
        tasks.clear();
    }

    @Override
    public Task getTask(int taskId) {
        if (tasks.get(taskId) != null) {
            history.add(tasks.get(taskId));
        }
        return tasks.get(taskId);
    }

    @Override
    public void updateTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    @Override
    public void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    @Override
    public Collection<Epic> getEpics() {
        return epics.values();
    }

    @Override
    public void clearEpics() {
        epics.clear();
    }

    @Override
    public Epic getEpic(int taskId) {
        if (epics.get(taskId) != null) {
            history.add(epics.get(taskId));
        }
        return epics.get(taskId);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
    }

    @Override
    public void deleteEpic(int taskId) {
        epics.remove(taskId);
    }

    @Override
    public ArrayList<Subtask> getSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();
        for (Integer subtaskId : epics.get(epicId).getSubtasks()) {
            result.add(subtasks.get(subtaskId));
        }
        return result;
    }

    @Override
    public Collection<Subtask> getSubtasks() {
        return subtasks.values();
    }

    @Override
    public void clearSubtasks() {
        subtasks.clear();
    }

    @Override
    public Subtask getSubtask(int taskId) {
        if (subtasks.get(taskId) != null) {
            history.add(subtasks.get(taskId));
        }
        return subtasks.get(taskId);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getTaskId(), subtask);
        epics.get(subtask.getEpicId()).addSubtask(subtask.getTaskId());
    }

    @Override
    public void deleteSubtask(int taskId) {
        epics.get(subtasks.get(taskId).getEpicId()).removeSubtask(taskId);
        subtasks.remove(taskId);
    }

    @Override
    public HashMap<Integer, Task> returnTasks() {
        return tasks;
    }

    @Override
    public ArrayList<Task> getHistory() {
        return history.getHistory();
    }

    public static HashMap<Integer, Epic> returnEpics() {
        return epics;
    }

    public static HashMap<Integer, Subtask> returnSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "InMemoryTaskManager{" +
                "tasks.size=" + tasks.size() +
                ", epics.size=" + epics.size() +
                ", subtasks.size=" + subtasks.size() +
                '}';
    }
}
