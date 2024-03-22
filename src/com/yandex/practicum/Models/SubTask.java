package com.yandex.practicum.Models;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String newTitle, String newDescription, TaskStatus newStatus, int id) {
        super(newTitle, newDescription, newStatus, id);
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
                "id=" + id +
                ", title='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }
}
