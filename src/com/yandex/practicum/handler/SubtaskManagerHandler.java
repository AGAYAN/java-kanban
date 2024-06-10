package com.yandex.practicum.handler;

import com.sun.net.httpserver.HttpExchange;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.SubTask;

import java.io.IOException;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class SubtaskManagerHandler extends BaseHttpHandler {
    public SubtaskManagerHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        try {
            switch (method) {
                case "GET":
                    if (path.equals("/subtasks") && exchange.getRequestURI().getQuery() == null) {
                        super.sendText(exchange, gson.toJson(taskManager.getSubtasks()));
                        respondWithText(exchange, "OK");
                    } else if (path.equals("/subtasks") && exchange.getRequestURI().getQuery() != null) {
                        int id = parseInt(exchange.getRequestURI().getQuery().substring(1));
                        super.sendText(exchange, gson.toJson(taskManager.getSubTaskById(id)));
                        respondWithText(exchange, "OK");
                    } else {
                        respondWithError(exchange, 404, "Not found");
                    }
                    break;
                case "POST": {
                    if (Pattern.matches("^/subtasks$", path)) {
                        String subFromJson = extractRequestBody(exchange);
                        SubTask subtask = gson.fromJson(subFromJson, SubTask.class);
                        taskManager.createNewSubTask(subtask);
                        sendText(exchange, "Задача добавлена");
                    } else {
                        sendNotFound(exchange, "Неверный путь");
                    }
                }
                case "DELETE":
                    if (path.equals("/subtasks") && exchange.getRequestURI().getQuery() != null) {
                        int id = parseInt(exchange.getRequestURI().getQuery().substring(1));
                        super.sendText(exchange, gson.toJson(taskManager.deleteSubTaskServer(id)));
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
