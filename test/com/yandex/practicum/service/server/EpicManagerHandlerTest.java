package com.yandex.practicum.service.server;

import com.yandex.practicum.enums.TaskStatus;
import com.yandex.practicum.models.Epic;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EpicManagerHandlerTest extends HttpTaskServerTest {
    @Test
    public void getAndPostAndDeleteByEpic() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        Epic epic = new Epic("Epic", "Epic", TaskStatus.NEW, 1);
        epic.setStartTime(LocalDateTime.of(2024, 2, 1, 0, 0));
        epic.setId(1);

        String epicToJson = gson.toJson(epic);

        URI uri = URI.create("http://localhost:8080/epics");
        HttpRequest request = HttpRequest.newBuilder().POST(HttpRequest.BodyPublishers.ofString(epicToJson)).uri(uri).build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, response.statusCode());

        uri = URI.create("http://localhost:8080/epics/1");
        request = HttpRequest.newBuilder().GET().uri(uri).build();
        response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Epic epicAfterGet = gson.fromJson(response.body(), Epic.class);
        assertEquals(epic, epicAfterGet);

    }
}
