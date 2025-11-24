import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLConnection {
    private static SQLConnection instance;
    private String url;
    private String username;
    private String password;
    private Connection connection;

    private SQLConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException ex) {
            throw new SQLException("MySQL JDBC Driver not found.", ex);
        }
    }

    public static SQLConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new SQLConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new SQLConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public ResultSet executeQuery(String query) {
        // Implementation for executing the query and returning the ResultSet
        return null; // Placeholder return
    }

    public void closeConnection() {
        // Implementation for closing the connection
    }

    public int executeUpdate(String updateQuery) {
        // Implementation for executing the update and returning the number of affected rows
        return 0; // Placeholder return
    }
}
