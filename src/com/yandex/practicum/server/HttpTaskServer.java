package com.yandex.practicum.server;

import com.sun.net.httpserver.HttpServer;
import com.yandex.practicum.handler.EpicManagerHandler;
import com.yandex.practicum.handler.HistoryManagerHandler;
import com.yandex.practicum.handler.SubtaskManagerHandler;
import com.yandex.practicum.handler.TaskManagerHandler;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.service.Managers;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpTaskServer {
    static final int PORT = 8080;
    private HttpServer server;
    private TaskManager taskManager;

    public HttpTaskServer(TaskManager taskManager) throws Exception {
        this.taskManager = taskManager;
        server = HttpServer.create();
    }

    public void startServer() throws IOException {
        server = HttpServer.create(new InetSocketAddress(PORT), 0);
        server.createContext("/tasks", new TaskManagerHandler(taskManager));
        server.createContext("/subtasks", new SubtaskManagerHandler(taskManager));
        server.createContext("/epics", new EpicManagerHandler(taskManager));
        server.createContext("/history", new HistoryManagerHandler(taskManager));
        server.createContext("/prioritized", new TaskManagerHandler(taskManager));

        server.start();
    }

    public void shutdown() {
        server.stop(2);
    }

    public static void main(String[] args) throws Exception {
        HttpTaskServer server1 = new HttpTaskServer(Managers.getDefaultTaskManager());
        server1.startServer();
        server1.shutdown();
    }
}
