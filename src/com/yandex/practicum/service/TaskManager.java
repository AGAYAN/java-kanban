package com.yandex.practicum.service;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;
import com.yandex.practicum.models.TaskStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {

    private int idSequence;

    private final HashMap<Integer, Task> tasks;

    private final HashMap<Integer, Epic> epics;

    private final HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createNewTask(Task task) {
        idSequence++;
        task.setId(idSequence);
        tasks.put(idSequence, task);
    }

    public void createNewEpic(Epic epic) {
        idSequence++;
        epic.setId(idSequence);
        epics.put(idSequence, epic);
    }

    public void createNewSubTask(SubTask subTask) {
        idSequence++;
        subTask.setId(idSequence);
        subTasks.put(idSequence, subTask);
    }

    public void updateEpic(Epic epic) {
        epics.get(epic.getId()).setStatus(epic.getStatus());
        epics.replace(epic.getId(), epic);
    }

    public void deleteEpic(Integer id) {
        deleteSubTask(id);
        epics.remove(id);
    }

    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    public void deleteSubTask(Integer id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = epics.get(subTask.getEpicId());
        epic.deleteSubTask(id);
        subTasks.remove(id);
        updateEpicStatus(epic);
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.replace(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        updateEpicStatus(epic);
    }

    public void updateTask(Task task) {
        tasks.replace(task.getId(), task);
    }

    public ArrayList<SubTask> getSubtasksByEpicId(Integer epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<SubTask> subtasks = new ArrayList<>();

        if (epic == null) {
            return new ArrayList<>();
        }
        for (Integer subTaskId : epic.getSubTaskIds()) {
            subtasks.add(subTasks.get(subTaskId));
        }

        return subtasks;
    }

    public void updateEpicStatus(Epic epic) {
        List<Integer> subtasks = epic.getSubTaskIds();
        int news = 0;
        int done = 0;

        for (Integer subtaskId : subtasks) {
            TaskStatus subtaskStatus = subTasks.get(subtaskId).getStatus();
            if (subtaskStatus.equals(TaskStatus.NEW)) {
                news++;
            } else if (subtaskStatus.equals(TaskStatus.DONE)) {
                done++;
            }
        }

        if (done == subtasks.size()) {
            epic.setStatus(TaskStatus.DONE);
        } else if (news == subtasks.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }

    public void deleteTasksByTask() {
        tasks.clear();
    }

    public void deleteTasksByEpic() {
        epics.clear();
    }

    public void deleteTasksBySubTask() {
        subTasks.clear();
    }
}
