import java.time.LocalDate;
import java.util.*;
public class Task {
    int taskId;
    String taskTitle;
    String taskDescription;
    Status taskStatus;
    Priority taskPriority;
    LocalDate taskDueDate;

    public Task(int id, String title, String description, Status state, Priority p, String due){
        this.taskId =id;
        this.taskTitle =title;
        this.taskDescription =description;
        this.taskStatus = state;
        this.taskPriority = p;
        this.taskDueDate = LocalDate.parse(due);
    }

}
