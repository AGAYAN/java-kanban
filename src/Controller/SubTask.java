package Controller;

import java.util.Objects;

public class SubTask extends Task {
    private int id;

    public SubTask(String newTitle, String newDescription) {
        super(newTitle, newDescription);
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
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
