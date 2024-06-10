package com.yandex.practicum.service.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yandex.practicum.adapter.DurationAdapter;
import com.yandex.practicum.adapter.LocalDateTimeAdapter;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.server.HttpTaskServer;
import com.yandex.practicum.service.InMemoryTaskManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.time.Duration;
import java.time.LocalDateTime;

public class HttpTaskServerTest {

    public static TaskManager manager;
    public HttpTaskServer server;
    public Gson gson;

    @BeforeEach
    void setUp() throws Exception {
        manager = new InMemoryTaskManager();
        server = new HttpTaskServer(manager);
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).registerTypeAdapter(Duration.class, new DurationAdapter()).create();
        this.server.startServer();
    }

    @AfterEach
    void tearDown() {
        this.server.shutdown();
    }

}
