public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        if (args.length == 0) {
            System.out.println("Please enter a command");
            return;
        }


        String command = args[0];


        if (command.equals("add")) {

            manager.addTask(args[1]);


        } else if (command.equals("list")) {

            manager.listTasks();


        } else if (command.equals("delete")) {

            manager.deleteTask(Integer.parseInt(args[1]));


        } else if (command.equals("update")) {

            manager.updateTask(
                    Integer.parseInt(args[1]),
                    args[2]
            );


        } else if (command.equals("mark-done")) {

            manager.changeStatus(
                    Integer.parseInt(args[1]),
                    "done"
            );


        } else if (command.equals("mark-in-progress")) {

            manager.changeStatus(
                    Integer.parseInt(args[1]),
                    "in-progress"
            );


        } else {

            System.out.println("Unknown command");
        }
    }
}