package com.yandex.practicum.models;

import com.yandex.practicum.enums.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String title, String description, TaskStatus newStatus, int id) {
        super(title, description, newStatus, id);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "SubTask{" +
                "id=" + getId() +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }

    public LocalDateTime getEndTime() {
        return super.getStartTime().plus(Duration.ofMinutes(super.getDuration().getSeconds()));
    }
}
