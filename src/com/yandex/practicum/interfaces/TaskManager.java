package com.yandex.practicum.interfaces;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;

import java.util.List;

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

    List<SubTask> getSubtasksByEpicId(Integer epicId);

    void deleteAllTasks();

    void deleteAllEpics();

    void deleteAllSubTasks();

    Epic getEpicById(Integer epicId);

    SubTask getSubTaskById(Integer subTaskId);

    Task getTaskById(Integer epicId);

    List<Epic> getEpics();

    List<SubTask> getSubtasks();

    List<Task> getTasks();
}
