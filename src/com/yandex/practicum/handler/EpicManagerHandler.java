package com.yandex.practicum.handler;

import com.sun.net.httpserver.HttpExchange;
import com.yandex.practicum.interfaces.TaskManager;
import com.yandex.practicum.models.Epic;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;

public class EpicManagerHandler extends BaseHttpHandler {

    private static final Pattern EPICS_PATTERN = Pattern.compile("^/epics$");
    private static final Pattern EPIC_ID_PATTERN = Pattern.compile("^/epics/(\\d+)$");
    private static final Pattern SUBTASKS_PATTERN = Pattern.compile("^/epics/(\\d+)/subtasks$");

    public EpicManagerHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();

        Matcher matcher;
        switch (method) {
            case "GET":
                matcher = EPICS_PATTERN.matcher(path);
                if (matcher.matches()) {
                    sendEpics(exchange);
                } else {
                    matcher = EPIC_ID_PATTERN.matcher(path);
                    if (matcher.matches()) {
                        sendEpicById(exchange, matcher.group(1));
                    } else {
                        matcher = SUBTASKS_PATTERN.matcher(path);
                        if (matcher.matches()) {
                            sendSubtasksByEpicId(exchange, matcher.group(1));
                        } else {
                            sendNotFound(exchange, "Неверный путь: " + path);
                        }
                    }
                }
                break;
            case "POST":
                Epic epic = gson.fromJson(extractRequestBody(exchange), Epic.class);
                taskManager.createNewEpic(epic);
                sendText(exchange, "Задача добавлена");
                break;
            case "DELETE":
                matcher = EPIC_ID_PATTERN.matcher(path);
                if (matcher.matches()) {
                    sendDeletedEpic(exchange, matcher.group(1));
                } else {
                    sendNotFound(exchange, "Неверный путь: " + path);
                }
                break;
            default:
                sendNotFound(exchange, "BadRequest");
        }
    }

    private void sendEpics(HttpExchange exchange) throws IOException {
        String response = gson.toJson(taskManager.getEpics());
        sendText(exchange, response);
        exchange.sendResponseHeaders(200, 0);
    }

    private void sendEpicById(HttpExchange exchange, String id) throws IOException {
        int parsedId = parseInt(id);
        if (parsedId != -1) {
            Epic epic = taskManager.getEpicById(parsedId);
            String response = gson.toJson(epic);
            sendText(exchange, response);
        } else {
            sendNotFound(exchange, "Неверный идентификатор");
        }
    }

    private void sendSubtasksByEpicId(HttpExchange exchange, String id) throws IOException {
        int parsedId = parseInt(id);
        if (parsedId != -1) {
            String response = gson.toJson(taskManager.getSubtasksByEpicId(parsedId));
            sendText(exchange, response);
        } else {
            sendNotFound(exchange, "Неверный идентификатор");
        }
    }

    private void sendDeletedEpic(HttpExchange exchange, String id) throws IOException {
        int parsedId = parseInt(id);
        if (parsedId != -1) {
            taskManager.deleteEpicServer(parsedId);
            sendText(exchange, "Задача удалена");
        } else {
            sendNotFound(exchange, "Неверный идентификатор");
        }
    }
}
