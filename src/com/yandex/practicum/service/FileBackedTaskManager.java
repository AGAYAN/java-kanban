package com.yandex.practicum.service;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    private static TaskManager taskManager;

    public FileBackedTaskManager(File file) {
        this.file = file;
        loadFromFile(file);
    }

    private void save() {
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            for (Task task : tasks.values()) {
                writer.write(toStringTask(task) + "\n");
            }
            for (Epic epic : epics.values()) {
                writer.write(toStringEpic(epic) + "\n");
            }
            for (SubTask subTask : subTasks.values()) {
                writer.write(toStringSubTask(subTask) + "\n");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при записи в файла", e);
        }
    }

    public String toStringTask(Task task) {
        return task.getId() + "," + task.getTitle() + "," + task.getDescription() + "," + task.getStatus();
    }

    public String toStringEpic(Epic epic) {
        return epic.getId() + "," + epic.getTitle() + "," + epic.getDescription() + "," + epic.getStatus();
    }

    public String toStringSubTask(SubTask subTask) {
        return subTask.getId() + "," + subTask.getEpicId() + "," + subTask.getTitle() + "," + subTask.getDescription() + "," + subTask.getStatus();
    }

    public static FileBackedTaskManager loadFromFile(File file) {
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                Object object = parseObject(line);
                if (object instanceof SubTask) {
                    taskManager.getSubtasks().add(((SubTask) object).getId(), (SubTask) object);
                } else if (object instanceof Epic) {
                    taskManager.getEpics().add(((Epic) object).getId(), (Epic) object);
                } else {
                    taskManager.getTasks().add(((Task) object).getId(), (Task) object);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new ManagerSaveException("file not found", e);
        }
        return (FileBackedTaskManager) taskManager;
    }


    private static Object parseObject(String line) throws IOException {
        String[] parts = line.split(",");
        String objectType = parts[0].trim();
        int id = Integer.parseInt(parts[1].trim());
        String title = parts[2].trim();
        String description = parts[3].trim();
        TaskStatus status = TaskStatus.valueOf(parts[4].trim());

        switch (objectType) {
            case "Task":
                Task task = new Task(title, description, status, id);
                if (parts.length > 5) {
                    LocalDateTime startTime = LocalDateTime.parse(parts[5].trim());
                    task.setStartTime(startTime);
                    if (parts.length > 6) {
                        LocalDateTime endTime = LocalDateTime.parse(parts[6].trim());
                        task.setDuration(Duration.between(startTime, endTime));
                    }
                }
                return task;
            case "Epic":
                return new Epic(title, description, status, id);
            case "Subtask":
                int epicId = Integer.parseInt(parts[5].trim());
                SubTask subTask = new SubTask(title, description, status, epicId);
                if (parts.length > 6) {
                    LocalDateTime startTime = LocalDateTime.parse(parts[6].trim());
                    subTask.setStartTime(startTime);
                    if (parts.length > 7) {
                        LocalDateTime endTime = LocalDateTime.parse(parts[7].trim());
                        subTask.setDuration(Duration.between(startTime, endTime));
                    }
                }
                return subTask;
            default:
                throw new IOException("Неизвестный тип объекта");
        }
    }

    @Override
    public void createNewTask(Task task) {
        super.createNewTask(task);
        save();
    }

    @Override
    public void createNewEpic(Epic epic) {
        super.createNewEpic(epic);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        super.updateSubTask(subTask);
        save();
    }
}
