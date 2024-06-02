package com.yandex.practicum.service;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class FileBackedTaskManagerTest extends TaskManagerTest<FileBackedTaskManager> {

    private static TaskManager taskManager;

    private static TaskManager taskManager2;

    public static String filename = "file.csv";


    @BeforeAll
    public static void setup() {
        File file = new File(filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        taskManager = new FileBackedTaskManager(file);
        taskManager2 = new FileBackedTaskManager(file);
    }

    @Test
    public void createAndSaveTaskFromFile() {
        Task task = new Task("qwfq", "qwfqwf", TaskStatus.NEW, 1);
        LocalDate data = LocalDate.of(2004, 4, 15);
        LocalTime time = LocalTime.now();
        LocalDateTime dataTime = LocalDateTime.now();
        Duration duration = Duration.ofMinutes(12_545_655);
        task.setStartTime(dataTime);
        task.setDuration(duration);

        taskManager.createNewTask(task);

        assertNotNull(taskManager.getTasks());
        assertEquals(1, taskManager.getTasks().size());

        taskManager2.createNewTask(task);
        assertNotNull(taskManager2.getTasks());
        assertEquals(1, taskManager2.getTasks().size());
        if (!taskManager2.getTasks().isEmpty()) {
            Task loadedTask = taskManager2.getTasks().get(0);
            assertEquals(task, loadedTask);
        }
    }

    @Test
    public void createAndSaveEpicFromFile() {
        Epic task = new Epic("title", "description", TaskStatus.NEW, 1);
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