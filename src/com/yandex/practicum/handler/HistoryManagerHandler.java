package com.yandex.practicum.handler;

import com.sun.net.httpserver.HttpExchange;
import com.yandex.practicum.interfaces.TaskManager;

import java.io.IOException;

public class HistoryManagerHandler extends BaseHttpHandler {
    public HistoryManagerHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        String method = exchange.getRequestMethod();
        if (path.equals("/history") && method.equals("GET")) {
            super.sendText(exchange, gson.toJson(taskManager.getTasks()));
            respondWithText(exchange, "OK");
        } else {
            respondWithError(exchange, 404, "Not found");
        }
    }
}
