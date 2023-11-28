package code.uz.service;

import code.uz.model.Todos;

import java.util.Scanner;

public class Service {

    static Scanner scanner = new Scanner(System.in);
    static Todos todos = new Todos();

    public void run() throws ClassNotFoundException {

        Class.forName("org.postgresql.Driver");

        do {
            System.out.println("""
                                        
                    1) Create todo
                    2) Active todo list
                    3) Finished todo list
                    4) Update
                    5) Delete
                    6) Mark as done
                    0) Exit
                       """);
            String option = scanner.nextLine();
            if (option.equals("0")) {
                System.out.println("Task completed");
                return;
            }
            switch (option) {
                case "1" -> {
                    ToDoMethods.enterVariables();
                    ToDoMethods.createTask(todos);
                }
                case "2" -> ToDoMethods.getActiveTodos().forEach(System.out::println);
                case "3" -> ToDoMethods.getFinishedTodolist().forEach(System.out::println);
                case "4" -> ToDoMethods.variableForUpdate();
                case "5" -> ToDoMethods.delete(ToDoMethods.requestId());
                case "6" -> ToDoMethods.mark();
            }

        } while (true);

    }

}
