package com.yandex.practicum.service;

import com.yandex.practicum.interfaces.HistoryManager;
import com.yandex.practicum.interfaces.TaskManager;

public class Managers {

    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }

    public static TaskManager getDefaultTaskManager() {
        return new InMemoryTaskManager();
    }
}
