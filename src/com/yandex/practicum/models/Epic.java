package com.yandex.practicum.models;

import java.util.List;

public class Epic extends Task {
    private List<Integer> subTaskIds;

    public Epic(String name, String description, TaskStatus status, int id) {
        super(name, description, status, id);
    }

    public List<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void setSubTaskIds(List<Integer> subTaskIds) {
        this.subTaskIds = subTaskIds;
    }

    public void addSubTaskId(Integer id) {
         subTaskIds.add(id);
    }

    public void deleteSubTaskId(Integer id) {
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
