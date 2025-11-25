import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLConnection {
    private static SQLConnection instance;
    private String url = "jdbc:mysql://localhost:3306/gptest?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private String username = "root";
    private String password = "password";
    private Connection connection;

    private SQLConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            //System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("MySQL JDBC Driver not found.", ex);
        }
    }

    public static SQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SQLConnection();
        } else if (!instance.isConnected()) {
            instance.closeConnection();
            instance = new SQLConnection();
        }
        return instance;
    }

    private boolean isConnected() throws SQLException {
        try{
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public int executeUpdate(String updateQuery) {
        try {
            return connection.createStatement().executeUpdate(updateQuery);
        } catch (SQLException e) {
            System.err.println("Error executing update: " + e.getMessage());
            return -1;
        }
    }
}
