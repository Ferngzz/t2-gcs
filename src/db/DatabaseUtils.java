package db;

import java.sql.*;

public class DatabaseUtils {
    private static Connection connection;

    public static void openConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/t2gcs";
            String user = "root";
            String password = "root";

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Connecting to database...");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
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

    public static int executeUpdate(String sql, Object... params) throws SQLException {
        openConnection();
        PreparedStatement stmt = connection.prepareStatement(sql);
        setParameters(stmt, params);
        return stmt.executeUpdate();
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