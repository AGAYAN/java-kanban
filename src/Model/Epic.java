package Model;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> subTaskUds;

    public Epic(String newName, String newDescription, TaskStatus status, int id) {
        super(newName, newDescription, status, id);
    }

    public ArrayList<Integer> getIdSubTasks() {
        return subTaskUds;
    }

    public void setIdSubTasks(ArrayList<Integer> idSubTasks) {
        this.subTaskUds = idSubTasks;
    }

    @Override
    public String toString () {
        return "Epic{" +
                "id=" + id +
                ", idSubtasks=" + getIdSubTasks() +
                ", name='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", idTask=" + id +
                ", status=" + getStatus() +
                '}';
    }


}
