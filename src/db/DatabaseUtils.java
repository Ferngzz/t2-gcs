package db;

import java.sql.*;

public class DatabaseUtils {
    private static Connection connection;

    public static void openConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/t2gcs?useSSL=false&serverTimezone=UTC";
            String user = "root";
            String password = "root";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("╔══════════════════════════════════════════════════════╗");
                System.out.println("║                 Connecting to database...            ║");
                System.out.println("╚══════════════════════════════════════════════════════╝");
            } catch (ClassNotFoundException e) {
                System.err.println("Connector nao encontrado");
            }

            connection = DriverManager.getConnection(url, user, password);
        }
    }


    public static ResultSet executeQuery(String sql, Object... params) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeQuery();
    }

    public static ResultSet executeQueryNoParam(String sql) throws SQLException {
        openConnection();
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(sql);
    }

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeUpdate();
    }

    public static int executeInsert(String sql, Object... params) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        setParameters(stmt, params);
        int affectedRows = stmt.executeUpdate();

        if (affectedRows > 0) {
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                }
            }
        }

        return -1;
    }

    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}