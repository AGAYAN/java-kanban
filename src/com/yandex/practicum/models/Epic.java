package com.yandex.practicum.models;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskIds;

    public Epic(String name, String description, TaskStatus status, int id) {
        super(name, description, status, id);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void setSubTaskIds(ArrayList<Integer> idSubTasks) {
        this.subTaskIds = idSubTasks;
    }

    public void deleteSubTask(Integer id) {
        subTaskIds.remove(id);
    }

    @Override
    public String toString () {
        return "Epic{" +
                "id=" + getId() +
                ", idSubtasks=" + getSubTaskIds() +
                ", name='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", idTask=" + getId() +
                ", status=" + getStatus() +
                '}';
    }


}
