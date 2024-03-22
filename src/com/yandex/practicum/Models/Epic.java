package com.yandex.practicum.Models;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> subTaskIds;

    public Epic(String newName, String newDescription, TaskStatus status, int id) {
        super(newName, newDescription, status, id);
    }

    public ArrayList<Integer> getSubTaskIds() {
        return subTaskIds;
    }

    public void getIdSubTasks(ArrayList<Integer> idSubTasks) {
        this.subTaskIds = idSubTasks;
    }

    public void deleteSubTask(Integer id) {
        subTaskIds.remove(id);
    }

    @Override
    public String toString () {
        return "Epic{" +
                "id=" + id +
                ", idSubtasks=" + getSubTaskIds() +
                ", name='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", idTask=" + id +
                ", status=" + getStatus() +
                '}';
    }


}
