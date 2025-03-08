import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.*;

public class TaskManager {
    HashMap<Integer,Task> taskList;

    public TaskManager(){
        this.taskList = new HashMap<>();
    }

    public void addTask(Task t) {
        int key = t.taskId;
        if (taskList.containsKey(key)) {
            System.out.println("Please create task with different id, task with id " + key + " already exists");
        } else {
            taskList.put(key, t);
            System.out.println("Added task to the list");
        }
    }

    public void deleteTask(Task t){
        int key = t.taskId;
        if(taskList.containsKey(key)){
            taskList.remove(key);
            System.out.println("Removed task from list of tasks");
        }
        else{
            System.out.println("This task doesn't exist please try again!");
        }
    }

    public void getTaskById(int id){
        if(taskList.containsKey(id)){
            Task curr = taskList.get(id);
            System.out.println("The details of the requested task are: ");
            System.out.print("-----------------------------------------");
            System.out.println("task id: "+curr.taskId);
            System.out.println("task title: "+ curr.taskTitle);
            System.out.println("task description: "+ curr.taskDescription);
            System.out.println("task status: "+ curr.taskStatus);
            System.out.println("task priority: "+ curr.taskPriority);
            System.out.println("task due date: "+ curr.taskDueDate);
        }
    }

    public void updateTitle(Task task, String newTitle){
        int id = task.taskId;
        if(taskList.containsKey(id)){
            taskList.get(id).taskTitle = newTitle;
            System.out.println("title of the task updated");
        }
        else{
            System.out.println("task doesn't exist please try again");
        }
    }

    public void updateDescription(Task task, String newDescription){
        int id = task.taskId;
        if(taskList.containsKey(id)){
            taskList.get(id).taskDescription = newDescription;
            System.out.println("description of the task updated");
        }
        else{
            System.out.println("task doesn't exist please try again");
        }
    }

    public void updatePriority(Task t , Priority newPriority){
        int id = t.taskId;

        if(taskList.containsKey(id)){
            taskList.get(id).taskPriority = newPriority;
            System.out.println("priority of the task updated");
        }
        else{
            System.out.println("task doesn't exist please try again");
        }

    }

    public void updateStatus(Task t , Status newState){
        int id = t.taskId;

        if(taskList.containsKey(id)){
            taskList.get(id).taskStatus= newState;
            System.out.println("status of the task updated");
        }
        else{
            System.out.println("task doesn't exist please try again");
        }

    }

    public void updateDueDate(Task t , String newDate){
        int id = t.taskId;
        if(taskList.containsKey(id)){

            taskList.get(id).taskDueDate= LocalDate.parse(newDate);
            System.out.println("due date of the task updated");
        }
        else{
            System.out.println("task doesn't exist please try again");
        }

    }

    public void getAllTasks(){
        if(taskList.isEmpty()){
            System.out.println("No task exists currently please add tasks");
        }
        else{
            System.out.println("Here is the list of all tasks ids in the list");
            for(Task curr:taskList.values()){
                System.out.print(curr.taskId +", ");
            }
            System.out.println();
        }
    }

    public void getTaskByPriority(Priority p){
        List<Integer>result = new ArrayList<>();
        for(Task curr:taskList.values()){
            if(curr.taskPriority == p){
                result.add(curr.taskId);

            }
        }

        if(result.size()==0){
            System.out.println("No task exists with given priority");
        }
        else{
            System.out.println("ID of tasks for priority "+p);
            System.out.println("-------------------------------------------");

            for(int i:result){
                System.out.print(i+",");
            }
            System.out.println();
        }

    }


    public void getTaskByStatus(Status s){
        List<Integer>result = new ArrayList<>();
        for(Task curr:taskList.values()){
            if(curr.taskStatus == s){
                result.add(curr.taskId);

            }
        }

        if(result.size()==0){
            System.out.println("No task exists with given status");
        }
        else{
            System.out.println("ID of tasks for status as "+s);
            System.out.println("-------------------------------------------");

            for(int i:result){
                System.out.print(i+",");
            }
            System.out.println();
        }
    }

    public void markAsCompleted(Task t){
        int id = t.taskId;
        if(taskList.containsKey(id)){
            taskList.get(id).taskStatus = Status.COMPLETED;
            System.out.println("Status of task marked as completed");
        }
        else{
            System.out.println("task doesn't exists, please try again");
        }
    }

}
