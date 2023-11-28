package code.uz.service;

import code.uz.model.Status;
import code.uz.model.Todos;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ToDoMethods {
    static Scanner scanner = new Scanner(System.in);
    static Scanner scannerInt = new Scanner(System.in);
    static Todos todos = new Todos();
    static Connection con = null;

    public static void enterVariables() {
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

    public static void variableForUpdate() {
        System.out.println("Enter title");
        String title = scanner.nextLine();
        System.out.println("Enter content");
        String content = scanner.nextLine();
        System.out.println("Enter id");
        int id = scannerInt.nextInt();
        todos.setTitle(title);
        todos.setContent(content);
        update(id, todos);
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

            ResultSet res = statement.executeQuery("select * from todos where status = '" + Status.ACTIVE + "' ");
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

    public static List<Todos> getFinishedTodolist() {
        List<Todos> todosList = new LinkedList<>();
        String url = "jdbc:postgresql://localhost:5432/db_lesson_jon";
        Properties info = new Properties();
        info.put("user", "asadbek");
        info.put("password", "1");


        try {
            con = DriverManager.getConnection(url, info);

//            Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement statement = con.createStatement();

            ResultSet res = statement.executeQuery("select * from todos where status = '" + Status.DONE + "' ");
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

    public static void update(int id, Todos dto) {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "asadbek", "1");

            Statement statement = con.createStatement();

            String sql = "update todos set title = '%s' , content = '%s' where id = " + id;
            sql = String.format(sql, dto.getTitle(), dto.getContent(), id);

            int effectedRows = statement.executeUpdate(sql);
            if (effectedRows > 0) {
                System.out.println("successfully updated");
            } else {
                System.out.println("todo not found");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(int id) {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "asadbek", "1");

            Statement statement = con.createStatement();

            String sql = "delete from todos where id = " + id;

            int effectedRows = statement.executeUpdate(sql);
            if (effectedRows > 0) {
                System.out.println("successfully deleted");
            } else {
                System.out.println("todo not found");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static int requestId() {
        System.out.println("Enter id");
        int id = scannerInt.nextInt();
        return id;
    }

    public static void mark() {
        todos.setStatus(Status.DONE);
        System.out.println("Enter id");
        int id = scannerInt.nextInt();
        markAsDone(id, todos);
    }

    public static void markAsDone(int id, Todos dto) {
        try {
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/db_lesson_jon",
                    "asadbek", "1");

            Statement statement = con.createStatement();

            String sql = "update todos set status = '%s'  where   id = " + id;
            sql = String.format(sql, dto.getStatus(), id);
            int effectedRows = statement.executeUpdate(sql);
            if (effectedRows > 0) {
                System.out.println("status changed successfully");

            } else {
                System.out.println("todo not found");
            }
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
