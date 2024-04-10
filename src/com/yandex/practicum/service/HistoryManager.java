package com.yandex.practicum.service;

import com.yandex.practicum.models.Task;

import java.util.List;

public interface HistoryManager {

    void addHistory(Task task);

    List<Task> getHistory();
}
