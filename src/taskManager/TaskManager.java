package taskManager;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {
    protected static HashMap<Integer, Task> tasks;
    protected static HashMap<Integer, Epic> epics;
    protected static HashMap<Integer, Subtask> subtasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
    }



    //  methods for Tasks
    public void updateTask(Task task) {
        tasks.put(task.getTaskId(), task);
    }

    public void printTasks() {
        System.out.println(tasks);
    }

    public static HashMap<Integer, Task> getTasks() {
        return tasks;
    }



    //  methods for Epics
    public void updateEpic(Epic epic) {
        epics.put(epic.getTaskId(), epic);
    }

    public void printEpics() {
        System.out.println(epics);
    }

    public static HashMap<Integer, Epic> getEpics() {
        return epics;
    }

    public ArrayList<Subtask> getSubtasksOfEpic(int epicId) {
        ArrayList<Subtask> result = new ArrayList<>();

        for (Integer subtaskId : epics.get(epicId).getSubtasks()) {
            result.add(subtasks.get(subtaskId));
        }

        return result;
    }



    //  methods for Subtasks
    public void updateSubtask(Subtask subtask) {
        epics.get(subtask.getEpicId()).addSubtask(subtask.getTaskId());
        subtasks.put(subtask.getTaskId(), subtask);
    }

    public void printSubtasks() {
        System.out.println(subtasks);
    }

    public static HashMap<Integer, Subtask> getSubtasks() {
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
