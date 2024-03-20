import Controller.Epic;
import Controller.SubTask;
import Controller.TaskStatus;
import Controller.Task;
import Service.TaskManager;

public class Main {

    public static void main(String[] args) {
        Task task = new Task("Сходить магазин", "Купить помидоры");
        Task task1 = new Task("Сходить магазин техники", "Купить ноутбук");
        Task taskUpDate = new Task("Сходить магазин купить покушать", "Пирожок");
        TaskManager taskManager = new TaskManager();
        taskManager.createNewTask(task);
        taskManager.createNewTask(task1);

        SubTask subtask = new SubTask("Встать в 9 утра","Пойти в WAY11");
        SubTask subTaskUpDate = new SubTask("Поставить будильник в 9 утра", "Пойти покушать");
        taskManager.createNewSubTask(subtask);
        subtask.setStatus(TaskStatus.IN_PROGRESS);

        Epic epic = new Epic("Встать в 10 утра", "Пойти в WAY11");
        Epic epicUpDate = new Epic("Встать в 12 утра", "Пойти покушать");
        Epic epicChangeStatusEpic = new Epic("Встать в 6 утра", "Начать бегать");
        taskManager.createNewEpic(epic);
        taskManager.upDateEpic(epic);
        taskManager.print();
        // ---------------------------------------
//        taskManager.deleteTask(); // Очистка кода
//        taskManager.print();
        // ---------------------------------------
//        taskManager.deleteEpicID(4); // удаление по id
//        taskManager.deleteSubTaskID(1);
//        taskManager.deleteSubTaskID(3);
//        taskManager.print();
        // ---------------------------------------
//          taskManager.upDateEpic(epicUpDate); // Обновление
//          taskManager.updateSubTask(subTaskUpDate);
//          taskManager.updateTask(taskUpDate);
//          taskManager.print();
        // ---------------------------------------
//           taskManager.printTaskForEpic(epicUpDate);
//           taskManager.print();
        // ---------------------------------------
//        taskManager.changeStatusEpic(epicChangeStatusEpic);
//        taskManager.print();
        // ---------------------------------------




    }
}
