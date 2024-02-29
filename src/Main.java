package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Connection connection;

        {
            try {
                connection = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/postgres",
                        "postgres", "mirlik98");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        Statement statement;

        {
            try {
                statement = connection.createStatement();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}

