package com.yandex.practicum.service;


import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.models.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    public FileBackedTaskManager(File file) throws FileNotFoundException {
        this.file = file;
        loadFromFile();
    }

    private void save() {
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            for (Task task : tasks.values()) {
                writer.write(task.toString());
            }
            for (Epic epic : epics.values()) {
                writer.write(epic.toString());
            }
            for (SubTask subTask : subTasks.values()) {
                writer.write(subTask.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadFromFile() throws FileNotFoundException {
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            BufferedReader bufferedReader = new BufferedReader(reader);
            while (bufferedReader.ready()) {
                String line = bufferedReader.readLine();
                Object object = parseObject(line);
                if (object instanceof SubTask) { // поменять местами
                    super.subTasks.put(((SubTask) object).getId(), (SubTask) object);
                } else if (object instanceof Epic) {
                    super.epics.put(((Epic) object).getId(), (Epic) object);
                } else {
                    super.tasks.put(((Task) object).getId(), (Task) object);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Файл не найден");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object parseObject(String line) throws Exception {
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
