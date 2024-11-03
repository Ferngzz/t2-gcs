package database;

import java.sql.*;

public class DatabaseUtils {
    private static Connection connection;

    private static void openConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            String url = "jdbc:mysql://localhost:3306/nome_do_banco";
            String user = "usuario";
            String password = "senha";
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
