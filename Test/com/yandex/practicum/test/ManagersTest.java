package com.yandex.practicum.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ManagersTest {

    @Test
    public void createNewHistoryManagerNotNull() {
        assertNotNull(Managers.getDefaultHistory());
    }

    @Test
    public void createNewTaskManagerNotNull() {
        assertNotNull(Managers.getDefaultTaskManager());
    }
}
