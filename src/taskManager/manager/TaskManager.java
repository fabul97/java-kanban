package taskManager.manager;

import taskManager.tasks.Epic;
import taskManager.tasks.Subtask;
import taskManager.tasks.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class TaskManager {
    static HashMap<Integer, Task> tasks = new HashMap<>();
    static HashMap<Integer, Epic> epics = new HashMap<>();
    static HashMap<Integer, Subtask> subtasks = new HashMap<>();

    //  methods for Tasks
    public static Collection<Task> getTasks() {
        return tasks.values();
    }

    public static void clearTasks() {
        tasks.clear();
    }

    public static Task getTask(int taskId) {
        return tasks.get(taskId);
    }

    public static void updateTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    public static void deleteTask(int taskId) {
        tasks.remove(taskId);
    }

    //  methods for Epics
    public static Collection<Epic> getEpics() {
        return epics.values();
    }

    public static void clearEpics() {
        epics.clear();
    }

    public static Epic getEpic(int taskId) {
        return epics.get(taskId);
    }

    public static void updateEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
    }

    public static void deleteEpic(int taskId) {
        epics.remove(taskId);
    }

    public ArrayList<Subtask> getSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();

        for (Integer subtaskId : epics.get(epicId).getSubtasks()) {
            result.add(subtasks.get(subtaskId));
        }

        return result;
    }

    //  methods for Subtasks
    public static Collection<Subtask> getSubtasks() {
        return subtasks.values();
    }

    public static void clearSubtasks() {
        subtasks.clear();
    }

    public static Subtask getSubtask(int taskId) {
        return subtasks.get(taskId);
    }

    // У меня обновляется статус Эпика при вызове метода addSubtask(int) в классе Epic
    public static void updateSubtask(Subtask subtask) {
        subtasks.put(subtask.getTaskId(), subtask);
        epics.get(subtask.getEpicId()).addSubtask(subtask.getTaskId());
        // Не совсем понимаю, почему при обновлении сабтаски, не будет обновляться эпик. Во-первых, по тестам обновляет.
        // Во-вторых, у меня сначала обновляется таска в первой строке метода. А на второй строчке
        // после добавления сабтаски в мапу я добавляю ее в список с айдишками сабатсков, который хранится в эпике.
        // Он же идет после добавления таска уже, поэтому всегда будет обновлять статус эпика?

        // Пекеджи переделал.
    }

    // Проверка на наличие taskId в листе есть в методе removeSubtask(int) в классе Epic
    public static void deleteSubtask(int taskId) {
        epics.get(subtasks.get(taskId).getEpicId()).removeSubtask(taskId);
        subtasks.remove(taskId);
    }

    public static HashMap<Integer, Task> returnTasks() {
        return tasks;
    }

    public static HashMap<Integer, Epic> returnEpics() {
        return epics;
    }

    public static HashMap<Integer, Subtask> returnSubtasks() {
        return subtasks;
    }

    @Override
    public String toString() {
        return "TaskManager{" +
                "tasks.size=" + tasks.size() +
                ", epics.size=" + epics.size() +
                ", subtasks.size=" + subtasks.size() +
                '}';
    }
}
