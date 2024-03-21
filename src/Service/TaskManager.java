package Service;


import Model.Epic;
import Model.SubTask;
import Model.Task;
import Model.TaskStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private int idSequence;
    private HashMap<Integer, Task> tasks;

    private HashMap<Integer, Epic> epics;

    private HashMap<Integer, SubTask> subTasks;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subTasks = new HashMap<>();
    }

    public void createNewTask(Task task) {
        idSequence++;
        task.setId(idSequence);
        task.setStatus(TaskStatus.NEW);
        tasks.put(idSequence, task);
    }

    public void createNewEpic(Epic epic) {
        idSequence++;
        epic.setId(idSequence);
        epic.setStatus(TaskStatus.NEW);
        epics.put(idSequence, epic);

    }

    public void createNewSubTask(SubTask subTask) {
        idSequence++;
        subTask.setId(idSequence);
        subTask.setStatus(TaskStatus.NEW);
        subTasks.put(idSequence, subTask);
    }

    public void upDateEpic(Epic epic) {
        for(Map.Entry<Integer, Epic> entry : epics.entrySet()){
            epics.put(entry.getKey(), epic);
            return;
        }
    }

    public void print(){
        System.out.println(tasks);
        System.out.println(epics);
        System.out.println(subTasks);
    }

    public void deleteTask() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    public void deleteEpicID(Integer id) {
        epics.remove(id);
    }

    public void deleteTaskID(Integer id) {
        tasks.remove(id);

    }

    public void deleteSubTaskID(Integer id) {
        subTasks.remove(id);
    }

    public void updateSubTask(SubTask subTask) {
        for (Map.Entry<Integer, SubTask> entry : subTasks.entrySet()) {
            subTasks.put(entry.getKey(), subTask);
            return;
        }
    }

    public void updateTask(Task task) {
        for (Map.Entry<Integer, Task> entry : tasks.entrySet()) {
            tasks.put(entry.getKey(), task);
            return;
        }
    }

    public ArrayList<SubTask> printTaskForEpic(Epic epic) {
        ArrayList<SubTask> temp = new ArrayList<>();
        for(Integer i : epic.getIdSubTasks()) {
            temp.add(subTasks.get(i));
        }
        return temp;
    }

    public void changeStatusEpic(Epic epic) {
        List<Integer> subtask = epic.getIdSubTasks();
        int New = 0;
        int Done = 0;

        for (Integer subtaskId : subtask) {
            TaskStatus subtaskStatus = subTasks.get(subtaskId).getStatus();
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
