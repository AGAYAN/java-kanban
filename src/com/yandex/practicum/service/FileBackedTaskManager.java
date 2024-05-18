package com.yandex.practicum.service;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
        loadFromFile();
    }

    private void save() {
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            for (Task task : tasks.values()) {
                writer.write(task.toString() + "\n");
            }
            for (Epic epic : epics.values()) {
                writer.write(epic.toString() + "\n");
            }
            for (SubTask subTask : subTasks.values()) {
                writer.write(subTask.toString() + "\n");
            }
        } catch (IOException e) {
            throw new CustomFileWriteException("Ошибка при записи в файл", e);
        }
    }

    private void loadFromFile() throws CustomFileWriteException { // Простите у меня тут не получилось бы с static он у меня жалуется и не записывает данные с тестов
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                Object object = parseObject(line);
                if (object instanceof SubTask) {
                    super.subTasks.put(((SubTask) object).getId(), (SubTask) object);
                } else if (object instanceof Epic) {
                    super.epics.put(((Epic) object).getId(), (Epic) object);
                } else {
                    super.tasks.put(((Task) object).getId(), (Task) object);
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new CustomFileWriteException("Файл не найден", e);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object parseObject(String line) throws Exception {
        String[] parts = line.split(",");
        String objectType = parts[0].trim();
        switch (objectType) {
            case "Task":
                return new Task(parts[1].trim(), parts[2].trim(),
                        TaskStatus.valueOf(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()));
            case "Epic":
                return new Epic(parts[1].trim(), parts[2].trim(),
                        TaskStatus.valueOf(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()));
            case "SubTask":
                return new SubTask(parts[1].trim(), parts[2].trim(),
                        TaskStatus.valueOf(parts[3].trim()),
                        Integer.parseInt(parts[4].trim()));
            default:
                throw new Exception("Неизвестный тип объекта");
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
