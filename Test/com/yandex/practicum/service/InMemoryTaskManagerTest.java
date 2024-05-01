package com.yandex.practicum.service;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
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
        Task task = new Task("title", "description", TaskStatus.NEW, id);
        manager.createNewTask(task);
        final int taskId = task.getId();
        assertNotNull(manager.getHistory().get(taskId - 1));
    }

    @Test
    void createNewEpic() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        final int epicId = epic.getId();
        assertNotNull(manager.getEpics().get(epicId - 1));
    }

    @Test
    void createNewSubTask() {
        SubTask subTask = new SubTask("title", "description", TaskStatus.NEW, id);
        manager.createNewSubTask(subTask);
        final int subTaskId = subTask.getId();
        if (!manager.getSubtasks().isEmpty()) {
            assertNotNull(manager.getSubtasks().get(subTaskId - 1));
        }
    }

    @Test
    void updateEpic() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        manager.updateEpic(epic);
        final int epicId = epic.getId();
        assertNotNull(manager.getEpics().get(epicId - 1));
    }

    @Test
    void deleteEpic() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        final int epicId = epic.getId();
        manager.deleteEpic(epicId);
        if (manager.getEpics().contains(epicId)) {
            assertEquals(0, (manager.getEpics().get(epicId)).getId());
        }
    }

    @Test
    void deleteTask() {
        Task task = new Task("title", "description", TaskStatus.NEW, id);
        manager.createNewTask(task);
        manager.deleteTask(task.getId());
        if (manager.getHistory().contains(task.getId())) {
            assertNull(manager.getHistory().get(task.getId()));
        }
    }

    @Test
    void deleteSubTask() {
        SubTask subTask = new SubTask("title", "description", TaskStatus.NEW, id);
        manager.createNewSubTask(subTask);
        final int subID = subTask.getId();
        assertFalse(manager.getSubtasks().contains(subID));
    }

    @Test
    void updateTask() {
        Task task = new Task("title", "description", TaskStatus.NEW, id);
        manager.createNewTask(task);
        manager.updateTask(task);
        final int taskId = task.getId();
        assertNotNull(manager.getHistory().get(taskId - 1));
    }

    @Test
    void getSubtasksByEpicId() {
        Task task = new Task("Task1", "Привет", TaskStatus.NEW, id);
        Task task2 = new Task("Task2", "Приветик", TaskStatus.NEW, id);

        manager.createNewTask(task);
        final int taskID = task.getId();
        manager.createNewTask(task2);

        SubTask subTask1 = new SubTask("Subtask1", "подзадачи1", TaskStatus.NEW, id);
        SubTask subTask2 = new SubTask("Subtask2", "подхадача2", TaskStatus.NEW, id);

        manager.createNewSubTask(subTask1);
        final int subID = subTask1.getId();
        manager.createNewSubTask(subTask2);

        Epic epic1 = new Epic("Epic1", "эпика1", TaskStatus.NEW, id);
        Epic epic2 = new Epic("Epic2", "эпика2", TaskStatus.NEW, id);

        manager.createNewEpic(epic1);
        final int epicID = epic1.getId();
        manager.createNewEpic(epic2);

        manager.deleteAllTasks();

        if (manager.getHistory().size() > taskID) {
            assertNull(manager.getHistory().get(taskID), "Задачи не удалились");
        }

        if (manager.getSubtasks().size() > subID) {
            assertNull(manager.getSubtasks().get(subID), "Подзадачи не удалились");
        }

        if (manager.getEpics().size() > epicID) {
            assertNull(manager.getEpics().get(epicID), "Эпики не удалились");
        }

        System.out.println("Метод ниже");
    }

    @Test
    void deleteAllTasks() {
        Task task = new Task("title", "description", TaskStatus.NEW, id);
        manager.createNewTask(task);
        manager.deleteAllTasks();
        if (manager.getHistory().contains(task.getId())) {
            assertNull(manager.getHistory().get(task.getId()));
        }
    }

    @Test
    void deleteAllEpics() {
        Epic epic = new Epic("title", "description", TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        manager.deleteAllEpics();
        if (manager.getEpics().contains(epic.getId())) {
            assertNull(manager.getEpics().get(epic.getId()));
        }
    }

    @Test
    void deleteAllSubTasks() {
        SubTask subTask = new SubTask("title", "description", TaskStatus.NEW, id);
        manager.createNewSubTask(subTask);
        manager.deleteAllSubTasks();
        if (manager.getSubtasks().contains(subTask.getId())) {
            assertNull(manager.getSubtasks().get(subTask.getId()));
        }
    }
}