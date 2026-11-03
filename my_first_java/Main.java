// Declare the package that this class belongs to.
package my_first_java;

// Import Java classes for working with files and input/output streams.
import java.io.*;

// Import ArrayList to store a resizable list of tasks.
import java.util.ArrayList;

// Import Scanner to read user input from the terminal.
import java.util.Scanner;

public class Main {

    // Define the name of the file used to store tasks.
    private static final String FILE_NAME = "tasks.txt";

    // The main method is the entry point of the program.
    public static void main(String[] args) {

        // Create an ArrayList to store task descriptions.
        ArrayList<String> todoList = new ArrayList<>();

        // Create a Scanner to read input from the terminal.
        Scanner scanner = new Scanner(System.in);

        // Load previously saved tasks when the program starts.
        loadTasksFromFile(todoList);

        // Display a welcome message.
        System.out.println("--- Welcome to use Chuan's Java Todolist ---");

        // Keep the program running until the user enters "quit".
        while (true) {
            // Display the task list heading.
            System.out.println("\n=================================");
            System.out.println("[Current Todos Task]:");

            // Check whether the task list is empty.
            if (todoList.isEmpty()) {
                System.out.println("(Empty now, Go to add some task!)");
            } else {
                // Loop through the list and display each task.
                for (int i = 0; i < todoList.size(); i++) {
                    // Display task numbers starting from 1.
                    System.out.println((i + 1) + ". " + todoList.get(i));
                }
            }

            // Display the available commands.
            System.out.println("=================================");
            System.out.println(
                    "Tip: Type 'quit' to exit | Type 'remove <number>' to delete task | Type text directly to add task");
            System.out.print("Please enter command: ");

            // Read a full line of input and remove leading and trailing spaces.
            String input = scanner.nextLine().trim();

            // Check whether the user wants to exit.
            // The comparison ignores letter case.
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for using, goodbye!");

                // Exit the while loop.
                break;
            }

            // Check whether the user entered a remove command.
            else if (input.startsWith("remove ")) {
                try {
                    // Extract the text after the "remove " prefix.
                    String indexStr = input.substring(7);

                    // Convert the text to an integer and subtract 1
                    // to get the zero-based list index.
                    int index = Integer.parseInt(indexStr) - 1;

                    // Check whether the index is within the list's bounds.
                    if (index >= 0 && index < todoList.size()) {
                        // Remove the task and store its description.
                        String removedTask = todoList.remove(index);

                        // Display a confirmation message.
                        System.out.println(
                                "[-] Successfully deleted task: " + removedTask);

                        // Save the updated task list after deleting a task.
                        saveTasksToFile(todoList);
                    } else {
                        // Display an error if the task number is out of range.
                        System.out.println(
                                "[x] Index out of range, task not found!");
                    }
                } catch (NumberFormatException e) {
                    // Handle input that cannot be parsed as an int.
                    System.out.println(
                            "[x] Please enter a valid task number, e.g., 'remove 1'");
                }
            }

            // Treat any other non-empty input as a new task.
            else if (!input.isEmpty()) {
                // Add the new task to the end of the list.
                todoList.add(input);

                // Display a confirmation message.
                System.out.println("[+] Successfully added task: " + input);

                // Save the updated task list after adding a task.
                saveTasksToFile(todoList);
            }
        }

        // Close the Scanner after the loop ends.
        scanner.close();
    }

    // Read saved tasks from the file and add them to the provided list.
    private static void loadTasksFromFile(ArrayList<String> list) {

        // Create a File object representing the task file.
        File file = new File(FILE_NAME);

        // Skip loading if the file does not exist, such as on the first run.
        if (!file.exists()) {
            return;
        }

        // Open a buffered reader to read the file.
        // Try-with-resources automatically closes the reader afterward.
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Declare a variable to store each line read from the file.
            String line;

            // Read one line at a time until the end of the file.
            while ((line = reader.readLine()) != null) {

                // Skip empty lines and lines containing only whitespace.
                if (!line.trim().isEmpty()) {
                    // Add the original line to the task list.
                    list.add(line);
                }
            }
        } catch (IOException e) {
            // Display an error if an I/O problem occurs while loading tasks.
            System.out.println("[System Error] Failed to load data from file.");
        }
    }

    // Write the current task list to the file, replacing its previous contents.
    private static void saveTasksToFile(ArrayList<String> list) {

        // Open a buffered writer, creating the file if it does not exist.
        // Try-with-resources automatically closes the writer afterward.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            // Loop through all tasks in the list.
            for (String task : list) {
                // Write the task description to the file.
                writer.write(task);

                // Add a line separator so each task is stored on its own line.
                writer.newLine();
            }
        } catch (IOException e) {
            // Display an error if an I/O problem occurs while saving tasks.
            System.out.println("[System Error] Failed to save data to file.");
        }
    }
}