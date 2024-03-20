package Service;


import Controller.Epic;
import Controller.SubTask;
import Controller.Task;
import Controller.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private int id;
    private HashMap<Integer, Task> taskHashMap;

    private HashMap<Integer, Epic> epicHashMap;

    private HashMap<Integer, SubTask> subTaskHashMap;


    public TaskManager(){
        taskHashMap = new HashMap<>();
        epicHashMap = new HashMap<>();
        subTaskHashMap = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void createNewTask(Task task) {
        if(id == getId()){
            id++;
            task.setId(id);
            task.setStatus(TaskStatus.NEW);
            taskHashMap.put(id, task);
            System.out.println("Добавление выполнено");
        }else {
            System.out.println("Добавление не выполнено");
        }
    }
    public void createNewEpic(Epic epic) {
        if(id == getId()){
            id++;
            epic.setId(id);
            epic.setStatus(TaskStatus.NEW);
            epicHashMap.put(id, epic);
            System.out.println("Добавление выполнено");
        }else {
            System.out.println("Добавление не выполнено");
        }
    }
    public void createNewSubTask(SubTask subTask) {
        if(id == getId()){
            id++;
            subTask.setId(id);
            subTask.setStatus(TaskStatus.NEW);
            subTaskHashMap.put(id, subTask);
            System.out.println("Добавление выполнено");
        }else {
            System.out.println("Добавление не выполнено");
        }
    }
    public void upDateEpic(Epic epic) {
        for(Map.Entry<Integer, Epic> entry : epicHashMap.entrySet()){
            epicHashMap.put(entry.getKey(), epic);
            System.out.println("Подзадача успешно обновлена.");
            return;
        }
    }
    public void print(){
        System.out.println(taskHashMap);
        System.out.println(epicHashMap);
        System.out.println(subTaskHashMap);
    }
    public void deleteTask(){
        taskHashMap.clear();
        epicHashMap.clear();
        subTaskHashMap.clear();
    }

    public void deleteEpicID(Integer id) {
        if (epicHashMap.containsKey(id)) {
            epicHashMap.remove(id);
            System.out.println("Epic успешно удален");
        } else {
            System.out.println("Epic с указанным ID не найден");
        }
    }

    public void deleteTaskID(Integer id) {
        if (taskHashMap.containsKey(id)) {
            taskHashMap.remove(id);
            System.out.println("Task успешно удален");
        } else {
            System.out.println("Task с указанным ID не найден");
        }
    }
    public void deleteSubTaskID(Integer id) {
        if (subTaskHashMap.containsKey(id)) {
            subTaskHashMap.remove(id);
            System.out.println("SubTask успешно удален.");
        } else {
            System.out.println("SubTask с указанным ID не найден");
        }
    }

    public void updateSubTask(SubTask subTask) {
        for (Map.Entry<Integer, SubTask> entry : subTaskHashMap.entrySet()) {
            subTaskHashMap.put(entry.getKey(), subTask);
            System.out.println("Подзадача успешно обновлена.");
            return;
        }
    }
    public void updateTask(Task task) {
        for (Map.Entry<Integer, Task> entry : taskHashMap.entrySet()) {
            taskHashMap.put(entry.getKey(), task);
            System.out.println("Подзадача успешно обновлена.");
            return;
        }
    }

    public ArrayList<SubTask> printTaskForEpic(Epic epic) {
        ArrayList<SubTask> temp = new ArrayList<>();
        for(Integer i : epic.getIdSubTasks()) {
            temp.add(subTaskHashMap.get(i));
        }
        return temp;
    }

    public void changeStatusEpic(Epic epic) {
        List<Integer> subtask = epic.getIdSubTasks();
        int New = 0;
        int Done = 0;

        for (Integer subtaskId : subtask) {
            TaskStatus subtaskStatus = subTaskHashMap.get(subtaskId).getStatus();
            if (subtaskStatus.equals(TaskStatus.NEW)) {
                New++;
            } else if (subtaskStatus.equals(TaskStatus.DONE)) {
                Done++;
            }
        }

        if (Done == subtask.size()) {
            epic.setStatus(TaskStatus.DONE); // если все подзадачи завершены
        } else if (New == subtask.size()) {
            epic.setStatus(TaskStatus.NEW); // если все подзадачи новые
        } else {
            epic.setStatus(TaskStatus.IN_PROGRESS); // во всех остальных случаях
        }
    }
}
