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
        Epic epic = new Epic("title", "description",  TaskStatus.NEW, id);
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
        Epic epic = new Epic("title", "description",  TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        manager.updateEpic(epic);
        final int epicId = epic.getId();
        assertNotNull(manager.getEpics().get(epicId - 1));
    }

    @Test
    void deleteEpic() {
        Epic epic = new Epic("title", "description",  TaskStatus.NEW, id);
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
    void updateSubTask() {
        SubTask subTask = new SubTask("title", "description", TaskStatus.NEW, id);
        manager.createNewSubTask(subTask);
        manager.updateSubTask(subTask);
        final int subTaskId = subTask.getId();
        assertNotNull(manager.getSubtasks().get(subTaskId - 1));
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
        Epic epic = new Epic("title", "description", TaskStatus.NEW, id);
        for (int i = 0; i < 5; i++) {
            SubTask subtask = new SubTask("subtask" + i, "description", TaskStatus.NEW, id);
            manager.createNewSubTask(subtask);
            epic.addSubTaskIds(subtask.getId());
        }

        assertEquals(TaskStatus.NEW, epic.getStatus());

        for (Integer subtaskId : epic.getSubTaskIds()) {
            SubTask subtask = manager.getSubtasks().get(subtaskId);
            if (subtaskId % 2 == 0) {
                subtask.setStatus(TaskStatus.DONE);
                manager.updateSubTask(subtask);
            }
        }

        manager.updateEpicStatus(epic);

        assertEquals(TaskStatus.IN_PROGRESS, epic.getStatus());

        for (Integer subtaskId : epic.getSubTaskIds()) {
            SubTask subtask = manager.getSubtasks().get(subtaskId);
            if (subtaskId % 2 == 0) {
                subtask.setStatus(TaskStatus.DONE);
            } else {
                subtask.setStatus(TaskStatus.NEW);
            }
            manager.updateSubTask(subtask);
        }

        manager.updateEpicStatus(epic);

        assertEquals(TaskStatus.DONE, epic.getStatus());
    }

    @Test
    void updateEpicStatus() {
        Epic epic = new Epic("title", "description",  TaskStatus.NEW, id);
        manager.createNewEpic(epic);
        manager.updateEpicStatus(epic);
        final int epicId = epic.getId();
        if (manager.getEpics().contains(epicId)) {
            assertNotNull(manager.getEpics().get(epicId));
        }
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
        Epic epic = new Epic("title", "description",  TaskStatus.NEW, id);
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