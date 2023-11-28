package code.uz.ui;

import code.uz.model.Status;
import code.uz.model.Todos;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Todos todos = new Todos();
    static Connection con = null;

    public static void main(String[] args) throws ClassNotFoundException {

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
                    enterVariables();
                    createTask(todos);
                }
                case "2" -> getActiveTodos().forEach(System.out::println);

                case "3" -> System.out.println();
                case "4" -> System.out.println();
                case "5" -> System.out.println();
                case "6" -> System.out.println();
            }

        } while (true);

    }

    private static void enterVariables() {
        System.out.println("Enter title");
        String title = scanner.nextLine();
        System.out.println("Enter content");
        String content = scanner.nextLine();
        todos.setTitle(title);
        todos.setContent(content);
        todos.setStatus(Status.ACTIVE);
        todos.setCreated_date(LocalDateTime.parse(LocalDateTime.now().toString()));
    }


    public static void createTask(Todos dto) {

        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "asadbek", "1");

            Statement statement = con.createStatement();

            String sql = "insert into todos(title,content,status,created_date) values('%s','%s','%s','%s')";
            sql = String.format(sql, dto.getTitle(), dto.getContent(), Status.ACTIVE, dto.getCreated_date());

            int effectedRows = statement.executeUpdate(sql);
            System.out.println("effectedRows = " + effectedRows);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                if (con != null) {
                    System.out.println("new task was created✏️✏️✏️✏️✏️");
                    con.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static List<Todos> getActiveTodos() {

        List<Todos> todosList = new LinkedList<>();
        String url = "jdbc:postgresql://localhost:5432/db_lesson_jon";
        Properties info = new Properties();
        info.put("user", "asadbek");
        info.put("password", "1");


        try {
            con = DriverManager.getConnection(url, info);

//            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement statement = con.createStatement();

            ResultSet res = statement.executeQuery("select * from todos ");
            while (res.next()) {
                Todos todo = new Todos();
                todo.setId(res.getInt("id"));
                todo.setTitle(res.getString("title"));
                todo.setContent(res.getString("content"));
                todo.setStatus(Status.valueOf(res.getString("status")));
                todo.setCreated_date(res.getTimestamp("created_date").toLocalDateTime());
                todo.setFinished_date(res.getTimestamp("finished_date"));
                todosList.add(todo);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return todosList;

    }


}