import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    private EmployeeService employeeService;
    private PayStatementService payStatementService;

    public ReportService() {
        this.employeeService = new EmployeeService();
        this.payStatementService = new PayStatementService();
    }  

    public Map<Employee, List<PayStatement>> generateEmployeeReport() {
        Map<Employee, List<PayStatement>> employeePayMap = new HashMap<>();
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee emp : employees) {
            List<PayStatement> payStatements = payStatementService.getPayStatementsByEmployeeID(Integer.parseInt(emp.getEmployeeID()));
            employeePayMap.put(emp, payStatements);
        }

        return employeePayMap;
    }

    public Map<String, Double> generatePayByDivision(int year, String month) {
        Map<String, Double> payByDivision = new HashMap<>();
        List<PayStatement> payStatements = payStatementService.getAllPayStatements();

        for (PayStatement ps : payStatements) {
            String payPeriod = ps.getPayPeriod();
            String[] parts = payPeriod.split(" ");
            String psMonth = parts[0];
            int psYear = Integer.parseInt(parts[1]);

            if (psYear == year && psMonth.equals(month)) {
                Employee emp = employeeService.getEmployeeByID(String.valueOf(ps.getEmployeeID()));
                String division = emp.getDivision();
                double amount = ps.getAmount();

                payByDivision.put(division, payByDivision.getOrDefault(division, 0.0) + amount);
            }
        }

        return payByDivision;
    }

    public Map<String, Double> generatePayByTitle(int year, String month) {
        Map<String, Double> payByTitle = new HashMap<>();
        List<PayStatement> payStatements = payStatementService.getAllPayStatements();

        for (PayStatement ps : payStatements) {
            String payPeriod = ps.getPayPeriod();
            String[] parts = payPeriod.split(" ");
            String psMonth = parts[0];
            int psYear = Integer.parseInt(parts[1]);

            if (psYear == year && psMonth.equals(month)) {
                Employee emp = employeeService.getEmployeeByID(String.valueOf(ps.getEmployeeID()));
                String jobTitle = emp.getJobTitle();
                double amount = ps.getAmount();

                payByTitle.put(jobTitle, payByTitle.getOrDefault(jobTitle, 0.0) + amount);
            }
        }

        return payByTitle;
    }

    public void printEmployeeReport() {
        Map<Employee, List<PayStatement>> report = generateEmployeeReport();
        for (Map.Entry<Employee, List<PayStatement>> entry : report.entrySet()) {
            Employee emp = entry.getKey();
            List<PayStatement> payStatements = entry.getValue();
            System.out.println(emp.toString());

            for (PayStatement ps : payStatements) {
                System.out.println("  " + ps.toString());
            }
        }
    }
}
