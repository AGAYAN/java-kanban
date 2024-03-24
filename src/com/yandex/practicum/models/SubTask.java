package com.yandex.practicum.models;

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
}
