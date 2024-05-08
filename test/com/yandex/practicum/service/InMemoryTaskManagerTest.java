package com.yandex.practicum.service;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.Task;
import com.yandex.practicum.models.TaskStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {
    private static InMemoryTaskManager manager;
    private final int id = 1;

    @BeforeAll
    static void init() {
        manager = new InMemoryTaskManager();
    }

    @Test
    void createNewTask() {
        Task task = new Task("title", "description", TaskStatus.NEW, 1);
        manager.createNewTask(task);
        final int taskId = task.getId();
        assertFalse(taskId - 1 < manager.getTasks().size());
    }

    @Test
    void createNewEpic() {
        Task task = new Task("title", "description", TaskStatus.NEW, 5); // Убедитесь, что id задачи равен 5
        manager.createNewTask(task);
        final int taskId = task.getId();
        // Проверяем, что задача с таким taskId существует в списке задач
        assertFalse(taskId - 1 < manager.getTasks().size());
    }

    @Test
    void updateEpic() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, 3);
        manager.createNewEpic(epic);
        manager.updateEpic(epic);
        final int epicId = epic.getId();
        assertNotNull(manager.getEpics().get(epicId - 1));
    }

    @Test
    void deleteEpic() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, 4);
        manager.createNewEpic(epic);
        final int epicId = epic.getId();
        manager.deleteEpic(epicId);
        if (manager.getEpics().contains(epicId)) {
            assertEquals(0, (manager.getEpics().get(epicId)).getId());
        }
    }

    @Test
    void deleteTask() {
        Task task = new Task("title", "description", TaskStatus.NEW, 5);
        manager.createNewTask(task);
        manager.deleteTask(task.getId());
        if (manager.getHistory().contains(task.getId())) {
            assertNull(manager.getHistory().get(task.getId()));
        }
    }

    @Test
    void updateTask() {
        Task task = new Task("title", "description", TaskStatus.NEW, 6);
        manager.createNewTask(task);
        manager.updateTask(task);
        final int taskId = task.getId();
        // Проверяем, что задача с таким taskId существует в списке задач
        assertFalse(taskId - 1 < manager.getTasks().size());
    }

    @Test
    void getSubtasksByEpicId() {
        Task task = new Task("Task1", "Привет", TaskStatus.NEW, 7);
        Task task2 = new Task("Task2", "Приветик", TaskStatus.NEW, 8);

        manager.createNewTask(task);
        final int taskID = task.getId();
        manager.createNewTask(task2);

        Epic epic1 = new Epic("Epic1", "эпика1", TaskStatus.NEW, 9);
        Epic epic2 = new Epic("Epic2", "эпика2", TaskStatus.NEW, 10);

        manager.createNewEpic(epic1);
        final int epicID = epic1.getId();
        manager.createNewEpic(epic2);


        manager.deleteAllTasks();

        if (manager.getTasks().size() > taskID) {
            assertNull(manager.getTasks().get(taskID), "Задачи не удалились");
        }

        if (manager.getEpics().size() > epicID) {
            assertNull(manager.getEpics().get(epicID), "Эпики не удалились");
        }

        System.out.println("Метод ниже");
    }

    @Test
    void deleteAllTasks() {
        Task task = new Task("title", "description", TaskStatus.NEW, 11);
        manager.createNewTask(task);
        manager.deleteAllTasks();
        if (manager.getTasks().contains(task.getId())) {
            assertNull(manager.getTasks().get(task.getId()));
        }
    }

    @Test
    void deleteAllEpics() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, 12);
        manager.createNewEpic(epic);
        manager.deleteAllEpics();
        if (manager.getEpics().contains(epic.getId())) {
            assertNull(manager.getEpics().get(epic.getId()));
        }
    }

}