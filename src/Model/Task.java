package Model;

import java.util.Objects;

public class Task {
    private String title;
    private String description;
    protected int id;
    private TaskStatus status;

    public Task(String newName, String newDescription, TaskStatus newStatus, int id) {
        this.title = newName;
        this.description = newDescription;
        this.status = newStatus;
        this.id = id;
    }

    public String getTitle() {
        return title ;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", status=" + status +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Task task = (Task) o;

        if (id != task.id && !Objects.equals(title, task.title)
                && !Objects.equals(description, task.description)
                && !Objects.equals(status, task.status)) {
            return false;
        }
        return status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, id, status);
    }
}
