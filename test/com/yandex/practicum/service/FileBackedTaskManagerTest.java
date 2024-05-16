package com.yandex.practicum.service;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileBackedTaskManagerTest {

    private static TaskManager taskManager;

    @BeforeAll
    public static void setup() {
        String filename = "file.csv";
        try {
            File file = new File(filename);
            taskManager = new FileBackedTaskManager(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createAndSaveTaskFromFile() {
        Task task = new Task("title", "description", TaskStatus.NEW, 1);
        taskManager.createNewTask(task);
        assertNotNull(taskManager.getTaskById(1));
    }

    @Test
    public void checkAddAndWriteFromFileTaskAndSubtaskAndEpic() {
        Task task = new Task("Task", "test", TaskStatus.NEW, 1);
        Epic epic = new Epic("Epic", "test", TaskStatus.NEW, 1);
        taskManager.createNewTask(task);
        taskManager.createNewEpic(epic);
        final int idTask = task.getId();
        final int idEpic = epic.getId();
        assertEquals(task, taskManager.getTaskById(idTask));
        assertEquals(epic, taskManager.getEpicById(idEpic));

    }

    @Test
    public void loadFromFile() {
        assertNotNull(taskManager.getTasks(), "getTask");
        assertNotNull(taskManager.getEpics(), "getEpic");
        assertNotNull(taskManager.getSubtasks(), "getSubtask");
    }
}