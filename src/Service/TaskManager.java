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
            System.out.println("Выполнено");
        }else {
            System.out.println("Не выполнено");
        }
    }
    public void createNewEpic(Epic epic) {
        if(id == getId()){
            id++;
            epic.setId(id);
            epic.setStatus(TaskStatus.NEW);
            epicHashMap.put(id, epic);
            System.out.println("Выполнено");
        }else {
            System.out.println("Не выполнено");
        }
    }
    public void createNewSubTask(SubTask subTask) {
        if(id == getId()){
            id++;
            subTask.setId(id);
            subTask.setStatus(TaskStatus.NEW);
            subTaskHashMap.put(id, subTask);
            System.out.println("Выполнено");
        }else {
            System.out.println("Не выполнено");
        }
    }
    public void upDateEpic(Epic epic) {
        if (epicHashMap.containsKey(epic.getId())) {
            epicHashMap.put(epic.getId(), epic);
            System.out.println("обновлено");
        } else {
            System.out.println("Ошибка при обнавлении");
        }
    }
    public void print(){
        System.out.println(taskHashMap);
        System.out.println(epicHashMap);
        System.out.println(subTaskHashMap);
    }

//    public void upDateSubTask(SubTask subTask){
//
//    }



}
