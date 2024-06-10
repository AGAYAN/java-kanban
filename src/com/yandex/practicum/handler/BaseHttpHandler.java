package com.yandex.practicum.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.yandex.practicum.adapter.DurationAdapter;
import com.yandex.practicum.adapter.LocalDateTimeAdapter;
import com.yandex.practicum.interfaces.TaskManager;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;

import static java.nio.charset.StandardCharsets.UTF_8;

public abstract class BaseHttpHandler implements HttpHandler {
    protected final TaskManager taskManager;
    protected Gson gson;

    public BaseHttpHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
        gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter()).registerTypeAdapter(Duration.class, new DurationAdapter()).create();
    }

    @Override
    public abstract void handle(HttpExchange exchange) throws IOException;

    protected void respondWithText(HttpExchange exchange, String responseBody) throws IOException {
        byte[] responseBytes = responseBody.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        exchange.sendResponseHeaders(200, responseBytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBytes);
        }
    }

    protected String extractRequestBody(HttpExchange exchange) throws IOException {
        return new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
    }

    protected void respondWithError(HttpExchange exchange, int statusCode, String errorMessage) throws IOException {
        byte[] errorResponse = errorMessage.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().put("Content-Type", Collections.singletonList("application/json;charset=UTF-8"));
        exchange.sendResponseHeaders(statusCode, errorResponse.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorResponse);
        }
    }

    protected void sendText(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(StandardCharsets.UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        h.sendResponseHeaders(200, resp.length);
        h.getResponseBody().write(resp);
        h.close();
    }

    protected void sendNotFound(HttpExchange h, String text) throws IOException {
        byte[] resp = text.getBytes(UTF_8);
        h.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        h.sendResponseHeaders(404, resp.length);
        h.getResponseBody().write(resp);
        h.close();
    }

}
