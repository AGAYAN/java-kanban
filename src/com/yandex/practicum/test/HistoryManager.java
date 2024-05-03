package com.yandex.practicum.test;

import com.yandex.practicum.models.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    void remove(int id);

    List<Task> getHistory();
}
