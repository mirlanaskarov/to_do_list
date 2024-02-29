package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TaskRepo {
    static final String URL = "jdbc:postgresql://127.0.0.1:5432/postgres";
    static final String USER = "postgres";
    static final String PASS = "mirlik98";

    public Connection connectToDataBase() {

        Connection connection = null;
        {
            try {
                connection = DriverManager.getConnection(URL, USER, PASS);
            } catch (SQLException e) {
                System.out.println("Ошибка при соединение56789 ! "+e.getMessage());
            }
        }
        return connection;
    }
}

