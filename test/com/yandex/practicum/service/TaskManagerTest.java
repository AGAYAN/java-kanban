package com.yandex.practicum.service;


import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class TaskManagerTest<T extends TaskManager> {

    @Test
    public void idEqualityCheckFromTask() {
        Task task = new Task("Task", "description", TaskStatus.NEW, 1);
        task.setId(1);
        Task task1 = new Task("Task", "no description", TaskStatus.NEW, 1);
        task1.setId(1);
        assertEquals(task.getId(), task1.getId(), "Задачи не совпадают");
    }

    @Test
    public void idEqualityCheckFromSubtask() {
        SubTask sub = new SubTask("Subtask", "description", TaskStatus.NEW, 1);
        sub.setId(1);
        SubTask sub2 = new SubTask("Subtask", "no description", TaskStatus.NEW, 1);
        sub2.setId(1);
        assertEquals(sub.getId(), sub2.getId(), "Ошибка сравнения подзадач");
    }

    @Test
    public void idEqualityCheckFromEpic() {
        Epic epic = new Epic("Epic", "description", TaskStatus.NEW, 1);
        epic.setId(1);
        Epic epic1 = new Epic("Epic", "description", TaskStatus.NEW, 1);
        epic1.setId(1);
        assertEquals(epic, epic1, "Ошибка в сравнении эпиков");
    }
}
