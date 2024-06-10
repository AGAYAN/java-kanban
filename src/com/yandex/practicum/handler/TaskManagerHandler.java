package com.yandex.practicum.handler;

import com.sun.net.httpserver.HttpExchange;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Task;

import java.io.IOException;

import static java.lang.Integer.parseInt;

public class TaskManagerHandler extends BaseHttpHandler {

    public TaskManagerHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        try {
            switch (method) {
                case "GET":
                    if (path.equals("/tasks") && exchange.getRequestURI().getQuery() == null) {
                        super.sendText(exchange, gson.toJson(taskManager.getTasks()));
                        respondWithText(exchange, "OK");
                    } else if (path.equals("/tasks") && exchange.getRequestURI().getQuery() != null) {
                        int id = parseInt(exchange.getRequestURI().getQuery().substring(1));
                        super.sendText(exchange, gson.toJson(taskManager.getTaskById(id)));
                        respondWithText(exchange, "OK");
                    } else {
                        respondWithError(exchange, 404, "Not found");
                    }
                    break;
                case "POST":
                    if (path.equals("/tasks")) {
                        String requestBody = extractRequestBody(exchange);
                        taskManager.createNewTask(gson.fromJson(requestBody, Task.class));
                        super.sendText(exchange, gson.toJson(taskManager.getTasks()));
                        respondWithText(exchange, "OK");
                    } else {
                        respondWithError(exchange, 404, "Not found");
                    }
                    break;
                case "DELETE":
                    if (path.equals("/tasks") && exchange.getRequestURI().getQuery() != null) {
                        int id = parseInt(exchange.getRequestURI().getQuery().substring(1));
                        super.sendText(exchange, gson.toJson(taskManager.deleteTaskServer(id)));
                        respondWithText(exchange, "OK");
                    } else {
                        respondWithError(exchange, 404, "Not found");
                    }
                    break;
            }
        } catch (Exception e) {
            respondWithError(exchange, 400, e.getMessage());
        }
    }
}
