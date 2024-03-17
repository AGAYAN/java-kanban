package Controller;

import java.util.ArrayList;

public class Epic extends Task{
    private ArrayList<Integer> idSubTasks;

    public Epic(String newName, String newDescription){
        super(newName, newDescription);
        idSubTasks = new ArrayList<>();
    }

    public ArrayList<Integer> getIdSubTasks() {
        return idSubTasks;
    }

    public void setIdSubTasks(ArrayList<Integer> idSubTasks) {
        this.idSubTasks = idSubTasks;
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
