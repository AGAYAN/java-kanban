package com.yandex.practicum.service;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;

import java.util.ArrayList;

public interface TaskManager {
    void createNewTask(Task task);

    void createNewEpic(Epic epic);

    void createNewSubTask(SubTask subTask);

    void updateEpic(Epic epic);

    void deleteEpic(Integer id);

    void deleteTask(Integer id);

    void deleteSubTask(Integer id);

    void updateSubTask(SubTask subTask);

    void updateTask(Task task);

    ArrayList<SubTask> getSubtasksByEpicId(Integer epicId);

    void updateEpicStatus(Epic epic);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();
}
