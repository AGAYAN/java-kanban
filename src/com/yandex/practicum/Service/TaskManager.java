package com.yandex.practicum.Service;

import com.yandex.practicum.Models.Epic;
import com.yandex.practicum.Models.SubTask;
import com.yandex.practicum.Models.Task;
import com.yandex.practicum.Models.TaskStatus;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        task.setStatus(TaskStatus.NEW);
        tasks.put(idSequence, task);
    }

    public void createNewEpic(Epic epic) {
        idSequence++;
        epic.setId(idSequence);
        epic.setStatus(TaskStatus.NEW);
        epics.put(idSequence, epic);

    }
    public void createNewSubTask(SubTask subTask) {
        idSequence++;
        subTask.setId(idSequence);
        subTask.setStatus(TaskStatus.NEW);
        subTasks.put(idSequence, subTask);
    }

    public void updateEpic(Epic epic) {
        epics.get(epic.getId()).setStatus(epic.getStatus());
        epics.replace(epic.getId(), epic);
    }

    public void deleteEpic(Integer id) {
        epics.remove(id);
    }

    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    public void deleteSubTask(Integer id) {
        SubTask subTask = subTasks.get(id);
        Epic epic = epics.get(subTask.getEpicId());
        epic.deleteSubTask(id);
        updateEpicStatus(epic);
        subTasks.remove(id);
    }

    public void updateSubTask(SubTask subTask) {
        subTasks.replace(subTask.getId(), subTask);
    }

    public void updateTask(Task task) {
        tasks.replace(task.getId(), task);
    }

    public ArrayList<SubTask> getSubtasksByEpicId(Integer epicId) {
        Epic epic = epics.get(epicId);
        ArrayList<SubTask> temp = new ArrayList<>();

        if (epic != null) {
            for (Integer subTaskId : epic.getSubTaskIds()) {
                temp.add(subTasks.get(subTaskId));
            }
        }

        return temp;
    }

    public void updateEpicStatus(Epic epic) {
        List<Integer> subtasks = epic.getSubTaskIds();
        int New = 0;
        int Done = 0;

        for (Integer subtaskId : subtasks) {
            TaskStatus subtaskStatus = subTasks.get(subtaskId).getStatus();
            if (subtaskStatus.equals(TaskStatus.NEW)) {
                New++;
            } else if (subtaskStatus.equals(TaskStatus.DONE)) {
                Done++;
            }
        }

        if (Done == subtasks.size()) {
            epic.setStatus(TaskStatus.DONE);
        } else if (New == subtasks.size()) {
            epic.setStatus(TaskStatus.NEW);
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS);
        }
    }
}
