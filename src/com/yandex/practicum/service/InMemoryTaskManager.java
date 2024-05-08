package com.yandex.practicum.service;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;
import com.yandex.practicum.models.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTaskManager implements TaskManager {

    private int idSequence;

    private final Map<Integer, Task> tasks;

    private final Map<Integer, Epic> epics;

    private final Map<Integer, SubTask> subTasks;

    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
        historyManager = Managers.getDefaultHistory();
    }

    @Override
    public void createNewTask(Task task) {
        idSequence++;
        task.setId(idSequence);
        tasks.put(idSequence, task);
    }

    @Override
    public void createNewEpic(Epic epic) {
        idSequence++;
        epic.setId(idSequence);
        epics.put(idSequence, epic);
    }

    @Override
    public void createNewSubTask(SubTask subTask) {
        idSequence++;
        subTask.setId(idSequence);
        Epic epic = epics.get(subTask.getEpicId());
        epic.addSubTaskId(idSequence);
        epic.addSubTaskId(idSequence);
        subTasks.put(idSequence, subTask);
        updateEpicStatus(epic);
    }

    @Override
    public void updateEpic(Epic epic) {
        epics.replace(epic.getId(), epic);
    }

    @Override
    public void deleteEpic(Integer id) {
        List<Integer> subTaskIds = epics.get(id).getSubTaskIds();
        if (subTaskIds != null) {
            for (Integer subTaskId : subTaskIds) {
                deleteSubTask(subTaskId);
            }
        }
        epics.remove(id);
    }

    @Override
    public void deleteTask(Integer id) {
        tasks.remove(id);
    }

    @Override
    public void deleteSubTask(Integer id) {
        SubTask subTask = subTasks.get(id);
        subTasks.remove(id);
        updateSubTask(subTask);
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        subTasks.replace(subTask.getId(), subTask);
        Epic epic = epics.get(subTask.getEpicId());
        updateEpicStatus(epic);
    }

    @Override
    public void updateTask(Task task) {
        tasks.replace(task.getId(), task);
    }

    @Override
    public List<SubTask> getSubtasksByEpicId(Integer epicId) {
        Epic epic = epics.get(epicId);
        List<SubTask> subtasks = new ArrayList<>();

        if (epic == null) {
            return new ArrayList<>();
        }
        for (Integer subTaskId : epic.getSubTaskIds()) {
            subtasks.add(subTasks.get(subTaskId));
        }

        return subtasks;
    }

    private void updateEpicStatus(Epic epic) {
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

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void deleteAllEpics() {
        epics.clear();
        subTasks.clear();
    }

    @Override
    public void deleteAllSubTasks() {
        for (Epic epic : epics.values()) {
            epic.setStatus(TaskStatus.NEW);
        }
        subTasks.clear();
        epics.clear();
    }

    @Override
    public Epic getEpicById(Integer id) {
        historyManager.add(epics.get(id));
        return epics.get(id);
    }

    @Override
    public SubTask getSubTaskById(Integer id) {
        historyManager.add(subTasks.get(id));
        return subTasks.get(id);
    }

    @Override
    public Task getTaskById(Integer id) {
        historyManager.add(tasks.get(id));
        return tasks.get(id);
    }

    public List<Task> getHistory() {
        return new ArrayList<>(historyManager.getHistory());
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<SubTask> getSubtasks() {
        return new ArrayList<>(subTasks.values());
    }
}
