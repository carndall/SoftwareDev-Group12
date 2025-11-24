import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PayStatementService {
    public List<PayStatement> getAllPayStatements() {
        List<PayStatement> payStatements = new ArrayList<>();
        String sql = "SELECT * FROM pay_statements";

        try {
            ResultSet rs = SQLConnection.getInstance().executeQuery(sql);
            while (rs.next()) {
                payStatements.add(mapResultSetToPayStatement(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pay statements: " + e.getMessage());
        }

        return payStatements;
    }

    public List<PayStatement> getPayStatementsByEmployeeID(int employeeID) {
        List<PayStatement> payStatements = new ArrayList<>();
        String sql = "SELECT * FROM pay_statements WHERE employee_id = " + employeeID;

        try {
            ResultSet rs = SQLConnection.getInstance().executeQuery(sql);
            while (rs.next()) {
                payStatements.add(mapResultSetToPayStatement(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pay statements for employee ID " + employeeID + ": " + e.getMessage());
        }

        return payStatements;
    }

    private PayStatement mapResultSetToPayStatement(ResultSet rs) throws SQLException {
        int employeeID = rs.getInt("employee_id");
        String payPeriod = rs.getString("pay_period");
        double amount = rs.getDouble("amount");
        return new PayStatement(employeeID, payPeriod, amount);
    }
}
