package my_first_java;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // 创建一个动态数组，用来存放待办事项
        ArrayList<String> todoList = new ArrayList<>();
        // 创建一个扫描器，用来接收你在终端敲入的文字
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Welcome to use Chuan's Java Todolist ---");

        // 使用 while(true) 让程序一直运行，直到你输入 quit 才会退出
        while (true) {
            System.out.println("\n=================================");
            System.out.println("[Current Todos Task]:");
            if (todoList.isEmpty()) {
                System.out.println("(Empty now, Go to add some task!)");
            } else {
                // 遍历数组，打印出每一项任务
                for (int i = 0; i < todoList.size(); i++) {
                    System.out.println((i + 1) + ". " + todoList.get(i));
                }
            }
            System.out.println("=================================");
            System.out.println(
                    "Tip: Type 'quit' to exit | Type 'remove <number>' to delete task | Type text directly to add task");
            System.out.print("Please enter command: ");

            // 接收用户整行输入并去掉前后空格
            String input = scanner.nextLine().trim();

            // 1. 判断是否要退出
            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for using. Goodbye!");
                break; // 跳出 while 循环，程序结束
            }

            // 2. 判断是否要删除任务
            else if (input.startsWith("remove ")) {
                try {
                    // 把 "remove 1" 里面的数字 "1" 提取出来并转成整数
                    String indexStr = input.substring(7);
                    int index = Integer.parseInt(indexStr) - 1;

                    if (index >= 0 && index < todoList.size()) {
                        String removedTask = todoList.remove(index);
                        System.out.println("[-] Successfully deleted task: " + removedTask);
                    } else {
                        System.out.println("[x] Index out of range. Task not found!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[x] Please enter a valid task number, e.g., 'remove 1'");
                }
            }

            // 3. 用户如果直接输入一句话，就当做新任务加进列表
            else if (!input.isEmpty()) {
                todoList.add(input);
                System.out.println("[+] Successfully added task: " + input);
            }
        }

        scanner.close(); // 良好习惯：关闭扫描器
    }
}
