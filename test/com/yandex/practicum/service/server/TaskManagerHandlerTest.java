package com.yandex.practicum.service.server;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.models.Task;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskManagerHandlerTest extends HttpTaskServerTest {

    @Test
    public void testDeleteTask() throws IOException, InterruptedException {
        Task task1 = new Task("Task", "Task", TaskStatus.NEW, 1);
        Duration dur = Duration.parse("PT1H30M");
        task1.setDuration(dur);
        task1.setStartTime(LocalDateTime.of(2024, 1, 1, 0, 0));

        String taskJson1 = gson.toJson(task1);

        HttpClient client = HttpClient.newHttpClient();
        URI url = URI.create("http://localhost:8080/tasks");

        HttpRequest requestPost = HttpRequest.newBuilder().uri(url).POST(HttpRequest.BodyPublishers.ofString(taskJson1)).build();

        HttpResponse<String> responseCreate = client.send(requestPost, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, responseCreate.statusCode());

        HttpRequest requestDelete = HttpRequest.newBuilder().uri(URI.create("http://localhost:8080/tasks/1")).DELETE().build();
        HttpResponse<String> responseDelete = client.send(requestDelete, HttpResponse.BodyHandlers.ofString());

        assertEquals(404, responseDelete.statusCode());
    }

}