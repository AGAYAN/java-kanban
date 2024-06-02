package com.yandex.practicum.models;

import com.yandex.practicum.enums.TaskStatus;

import java.util.List;
import java.util.Objects;

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

    public List<Integer> getSubtaskByEpic() {
        return subTaskIds;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTaskIds, epic.subTaskIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTaskIds);
    }
    @Override
    public String toString() {
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
