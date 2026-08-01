import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {

    ArrayList<Task> tasks = new ArrayList<>();
    String fileName = "tasks.json";


    public TaskManager() {
        loadTasks();
    }


    public void addTask(String description) {

        int id = tasks.size() + 1;

        Task task = new Task(
                id,
                description,
                "todo",
                java.time.LocalDateTime.now().toString(),
                java.time.LocalDateTime.now().toString()
        );

        tasks.add(task);
        saveTasks();

        System.out.println("Task added successfully (ID: " + id + ")");
    }


    public void listTasks() {

        if (tasks.isEmpty()) {
            System.out.println("No tasks found");
            return;
        }

        for (Task task : tasks) {

            System.out.println(
                    task.id + " - " +
                    task.description + " - " +
                    task.status
            );
        }
    }


    public void updateTask(int id, String newDescription) {

        for (Task task : tasks) {

            if (task.id == id) {

                task.description = newDescription;
                task.updatedAt = java.time.LocalDateTime.now().toString();

                saveTasks();

                System.out.println("Task updated successfully");
                return;
            }
        }

        System.out.println("Task not found");
    }


    public void deleteTask(int id) {

        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).id == id) {

                tasks.remove(i);
                saveTasks();

                System.out.println("Task deleted successfully");
                return;
            }
        }

        System.out.println("Task not found");
    }


    public void changeStatus(int id, String status) {

        for (Task task : tasks) {

            if (task.id == id) {

                task.status = status;
                task.updatedAt = java.time.LocalDateTime.now().toString();

                saveTasks();

                System.out.println("Task status updated successfully");
                return;
            }
        }

        System.out.println("Task not found");
    }


    public void saveTasks() {

        try {

            FileWriter writer = new FileWriter(fileName);

            writer.write("[\n");

            for (int i = 0; i < tasks.size(); i++) {

                Task task = tasks.get(i);

                writer.write(
                    "  {\n" +
                    "    \"id\": " + task.id + ",\n" +
                    "    \"description\": \"" + task.description + "\",\n" +
                    "    \"status\": \"" + task.status + "\"\n" +
                    "  }"
                );

                if (i < tasks.size() - 1) {
                    writer.write(",");
                }

                writer.write("\n");
            }

            writer.write("]");

            writer.close();

        } catch (IOException e) {

            System.out.println("Error saving tasks");
        }
    }


    public void loadTasks() {

        File file = new File(fileName);

        if (!file.exists()) {
            return;
        }

        try {

            Scanner scanner = new Scanner(file);

            int id = 0;
            String description = "";
            String status = "";


            while (scanner.hasNextLine()) {

                String line = scanner.nextLine().trim();


                if (line.startsWith("\"id\"")) {
                    id = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                }


                if (line.startsWith("\"description\"")) {

                    description = line.split(":")[1]
                            .replace("\"", "")
                            .replace(",", "")
                            .trim();
                }


                if (line.startsWith("\"status\"")) {

                    status = line.split(":")[1]
                            .replace("\"", "")
                            .replace(",", "")
                            .trim();


                    tasks.add(new Task(
                            id,
                            description,
                            status,
                            "",
                            ""
                    ));
                }
            }

            scanner.close();

        } catch (IOException e) {

            System.out.println("Error loading tasks");
        }
    }
}