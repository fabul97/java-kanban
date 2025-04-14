package taskmanager.tasks;

import taskmanager.manager.Managers;

import taskmanager.manager.history.HistoryManager;
import taskmanager.manager.tasks.TaskManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {

    HashMap<Integer, Task> tasks = new HashMap<>();
    static HashMap<Integer, Epic> epics = new HashMap<>();
    static HashMap<Integer, Subtask> subtasks = new HashMap<>();

    private final HistoryManager history = Managers.getDefaultHistory();

    // Methods for Tasks
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

    // Methods for Epics
    @Override
    public Collection<Epic> getEpics() {
        return epics.values();
    }

    static void updateStatus(Epic epic) {
         epic.setStatus(checkStatus(epic));
    }

    static TaskStatus checkStatus(Epic epic) {
        if (epic.getSubtasks() == null || epic.getSubtasks().isEmpty()) return TaskStatus.NEW;

        boolean allNew = true;
        boolean allDone = true;

        for (Integer subtaskId : epic.getSubtasks()) {
            if (InMemoryTaskManager.returnSubtasks().get(subtaskId).getStatus() != TaskStatus.NEW) {
                allNew = false;
            }
            if (InMemoryTaskManager.returnSubtasks().get(subtaskId).getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allNew) {
            return TaskStatus.NEW;
        }
        if (allDone) {
            return TaskStatus.DONE;
        }

        return TaskStatus.IN_PROGRESS;
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
        InMemoryTaskManager.returnEpics().get(taskId).clearSubtasksOfEpic();
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

    // Methods for Subtasks
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
