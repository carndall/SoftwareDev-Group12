import java.sql.SQLException;

public class App {
    public static void main(String[] args) throws Exception {

        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                SQLConnection.getInstance().closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }));
    }
}
