import Controller.Epic;
import Controller.SubTask;
import Controller.TaskStatus;
import Controller.Task;
import Service.TaskManager;

public class Main {

    public static void main(String[] args) {
        Task task = new Task("Сходить магазин", "Купить помидоры");
        Task task1 = new Task("Сходить магазин техники", "Купить ноутбук");
        TaskManager taskManager = new TaskManager();
        taskManager.createNewTask(task);
        taskManager.createNewTask(task1);

        SubTask subtask = new SubTask("Встать в 9 утра","Пойти в WAY11");
        taskManager.createNewSubTask(subtask);
        subtask.setStatus(TaskStatus.IN_PROGRESS);

        Epic epic = new Epic("Встать в 10 утра", "Пойти в WAY11");
        taskManager.createNewEpic(epic);
        taskManager.upDateEpic(epic);
        taskManager.print();




    }
}
