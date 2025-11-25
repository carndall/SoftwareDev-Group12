import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class PayStatementService {
    public List<PayStatement> getAllPayStatements() {
        List<PayStatement> payStatements = new ArrayList<>();
        String sql = "SELECT * FROM pay_statements";

        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                payStatements.add(mapResultSetToPayStatement(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pay statements: " + e.getMessage());
        }

        return payStatements;
    }

    public List<PayStatement> getPayStatementsByEmployeeID(String employeeID) {
        List<PayStatement> payStatements = new ArrayList<>();
        String sql = "SELECT * FROM pay_statements WHERE empid = ?";

        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, employeeID);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                payStatements.add(mapResultSetToPayStatement(rs));
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving pay statements for employee ID " + employeeID + ": " + e.getMessage());
        }

        return payStatements;
    }

    private PayStatement mapResultSetToPayStatement(ResultSet rs) throws SQLException {
        String employeeID = rs.getString("empid");
        String payPeriod = rs.getString("pay_period");
        double amount = rs.getDouble("amount");
        return new PayStatement(employeeID, payPeriod, amount);
    }
}
