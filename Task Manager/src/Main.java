public class Main {
    public static void main(String[] args) {
        // Create TaskManager instance
        TaskManager taskManager = new TaskManager();

        // Create some tasks
        Task task1 = new Task(1, "Submit report", "Submit monthly report to manager", Status.TODO, Priority.HIGH, "2025-03-15");
        Task task2 = new Task(2, "Prepare slides", "Create slides for presentation", Status.IN_PROGRESS, Priority.MEDIUM, "2025-03-18");
        Task task3 = new Task(3, "Buy groceries", "Milk, eggs, bread", Status.TODO, Priority.LOW, "2025-03-20");

        // Add tasks
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        // Retrieve all tasks
        taskManager.getAllTasks();

        // Retrieve task by ID
        taskManager.getTaskById(1);

        // Update task title
        taskManager.updateTitle(task2, "Prepare Presentation Slides");

        // Mark Task 2 as Completed
        taskManager.markAsCompleted(task2);

        // Get tasks by priority
        taskManager.getTaskByPriority(Priority.HIGH);

        // Get tasks by status
        taskManager.getTaskByStatus(Status.COMPLETED);
        System.out.println(task3.taskDueDate);

        // Delete a task
        taskManager.deleteTask(task3);

        // Retrieve all tasks after deletion
        taskManager.getAllTasks();
    }
}
