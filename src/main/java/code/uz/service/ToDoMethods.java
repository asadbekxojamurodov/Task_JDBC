package code.uz.service;

import code.uz.model.Status;
import code.uz.model.Todos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ToDoMethods {
    static Scanner scanner = new Scanner(System.in);
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
            sql = String.format(sql, dto.getTitle(), dto.getContent(),Status.ACTIVE,dto.getCreated_date() );

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

}
