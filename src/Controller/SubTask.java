package Controller;

public class SubTask extends Task {
    private int id;

    public SubTask(String newtitle, String newDescription){
        super(newtitle, newDescription);
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
        return "Subtask{" +
                "idSubtask=" + id +
                ", name='" + getTitle() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", status=" + getStatus() +
                '}';
    }

}
