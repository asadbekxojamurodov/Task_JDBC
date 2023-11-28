package code.uz;

import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {


        do {
            System.out.println("""
                    1) Create task
                    2) Active task list
                    3) Finished task list
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
                case "1" -> System.out.println();
                case "2" -> System.out.println();
                case "3" -> System.out.println();
                case "4" -> System.out.println();
                case "5" -> System.out.println();
                case "6" -> System.out.println();
            }

        } while (true);


    }
}