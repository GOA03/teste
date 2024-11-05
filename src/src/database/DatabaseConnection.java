package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root";  // Ajuste o usuário do banco de dados
    private static final String PASSWORD = "";  // Ajuste a senha do banco de dados

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
