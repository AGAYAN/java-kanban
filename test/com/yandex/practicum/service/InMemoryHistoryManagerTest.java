package com.yandex.practicum.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryHistoryManagerTest {

    @Test
    void addHistory() {
        assertNotNull(Managers.getDefaultHistory());
    }

    @Test
    void getHistory() {
        assertNotNull(Managers.getDefaultTaskManager());
    }
}