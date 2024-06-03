package com.yandex.practicum;

import com.yandex.practicum.models.Epic;
import com.yandex.practicum.models.SubTask;
import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.models.Task;
import com.yandex.practicum.service.InMemoryTaskManager;
import com.yandex.practicum.interfaces.TaskManager;

public class Main {

    public static void main(String[] args) {
        Task task = new Task("Сходить магазин", "Купить помидоры", TaskStatus.DONE, 1);
        Task task1 = new Task("Сходить магазин техники", "Купить ноутбук", TaskStatus.DONE, 2);
        Task taskUpDate = new Task("Сходить магазин купить покушать", "Пирожок", TaskStatus.DONE, 3);
        TaskManager taskManager = new InMemoryTaskManager();
        taskManager.createNewTask(task);
        taskManager.createNewTask(task1);

        SubTask subtask = new SubTask("Встать в 9 утра","Пойти в WAY11", TaskStatus.NEW, 1);
        SubTask subTaskUpDate = new SubTask("Поставить будильник в 9 утра", "Пойти покушать", TaskStatus.DONE, 3);
        taskManager.createNewSubTask(subtask);
        subtask.setStatus(TaskStatus.IN_PROGRESS);

        Epic epic = new Epic("Встать в 10 утра", "Пойти в WAY11", TaskStatus.NEW, subtask.getId());
        Epic epicUpDate = new Epic("Встать в 12 утра", "Пойти покушать", TaskStatus.DONE, subtask.getId());
        Epic epicChangeStatusEpic = new Epic("Встать в 6 утра", "Начать бегать", TaskStatus.IN_PROGRESS, subtask.getId());
        taskManager.createNewEpic(epic);
        taskManager.updateEpic(epic);
        // ---------------------------------------
//        taskManager.deleteTask(); // Очистка кода
        // ---------------------------------------
//        taskManager.deleteEpicID(4); // удаление по id
//        taskManager.deleteSubTaskID(1);
//        taskManager.deleteSubTaskID(3);
        // ---------------------------------------
//          taskManager.upDateEpic(epicUpDate); // Обновление
//          taskManager.updateSubTask(subTaskUpDate);
//          taskManager.updateTask(taskUpDate);
        // ---------------------------------------
//           taskManager.printTaskForEpic(epicUpDate);
        // ---------------------------------------
//        taskManager.changeStatusEpic(epicChangeStatusEpic);
//          ---------------------------------------




    }
}
