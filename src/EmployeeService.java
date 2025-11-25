import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeService {
    /*
    public List<Employee> searchEmployees(Map<String, String> searchCriteria) {
        List<Employee> employees = new ArrayList<>();
        // Build SQL query based on search criteria
        
        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
            
            for (int i = 0; i < parameters.size(); i++) {
                pstmt.setObject(i + 1, parameters.get(i));
            }
            
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error searching employees: " + e.getMessage());
        }
        
        return employees;
    }
    */
   
    public boolean updateEmployeeSalary(Employee emp) {
        String sql = "UPDATE employees SET name = ?, ssn = ?, job_title = ?, division = ?, salary = ? WHERE empid = ?";
        
        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, emp.getName());
            pstmt.setString(2, emp.getSsn());
            pstmt.setString(3, emp.getJobTitle());
            pstmt.setString(4, emp.getDivision());
            pstmt.setDouble(5, emp.getSalary());
            pstmt.setString(6, emp.getEmployeeID());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error updating employee: " + e.getMessage());
            return false;
        }
    }

    public int updateSalaryByRange(double minSalary, double maxSalary, double percentageIncrease) {
        String sql = "UPDATE employees SET salary = salary * (1 + ? / 100) WHERE salary >= ? AND salary < ?";
        
        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, percentageIncrease);
            pstmt.setDouble(2, minSalary);
            pstmt.setDouble(3, maxSalary);
            
            return pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.err.println("Error updating salaries: " + e.getMessage());
            return 0;
        }
    }

    public boolean addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (empid, name, ssn, job_title, division, salary) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employee.getEmployeeID());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getSsn());
            pstmt.setString(4, employee.getJobTitle());
            pstmt.setString(5, employee.getDivision());
            pstmt.setDouble(6, employee.getSalary());
            
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error adding employee: " + e.getMessage());
            return false;
        }
    }

    public boolean removeEmployee(String employeeID) {
        String sql = "DELETE FROM employees WHERE empid = ?";

        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeID);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
            
        } catch (SQLException e) {
            System.err.println("Error removing employee: " + e.getMessage());
            return false;
        }
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        
        try (Connection conn = SQLConnection.getInstance().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                employees.add(mapResultSetToEmployee(rs));
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting all employees: " + e.getMessage());
        }
        
        return employees;
    }

    public Employee getEmployeeByID(String employeeID) {
        String sql = "SELECT * FROM employees WHERE empid = ?";
        Employee employee = null;
        
        try (Connection conn = SQLConnection.getInstance().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, employeeID);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                employee = mapResultSetToEmployee(rs);
            }
            
        } catch (SQLException e) {
            System.err.println("Error getting employee by ID: " + e.getMessage());
        }
        
        return employee;
    }

    private Employee mapResultSetToEmployee(ResultSet rs) throws SQLException {
        return new Employee(
            rs.getString("empid"),
            rs.getString("name"),
            rs.getString("job_title"),
            rs.getString("ssn"),
            rs.getString("division"),
            rs.getDouble("salary")
        );
    }

}
