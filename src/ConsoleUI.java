import java.util.*;

public class ConsoleUI {
    private EmployeeService employeeService;
    private PayStatementService payStatementService;
    private ReportService reportService;
    private Scanner scanner;

    public ConsoleUI() {
        this.employeeService = new EmployeeService();
        this.payStatementService = new PayStatementService();
        this.reportService = new ReportService();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== Employee Management System ===");
        
        while (true) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    employeeManagementMenu();
                    break;
                case 2:
                    payStatementMenu();
                    break;
                case 3:
                    reportMenu();
                    break;
                case 4:
                    searchEmployees();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMainMenu() {
        System.out.println("\n===== MAIN MENU =====");
        System.out.println("1. Employee Management");
        System.out.println("2. Pay Statement Management");
        System.out.println("3. Reports");
        System.out.println("4. Search Employees");
        System.out.println("0. Exit");
        System.out.println("=====================");
    }

    private void employeeManagementMenu() {
        while (true) {
            System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. View Employee by ID");
            System.out.println("4. Update Employee Salary");
            System.out.println("5. Bulk Update Salaries by Range");
            System.out.println("6. Remove Employee");
            System.out.println("0. Back to Main Menu");
            System.out.println("===============================");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    viewEmployeeByID();
                    break;
                case 4:
                    updateEmployeeSalary();
                    break;
                case 5:
                    bulkUpdateSalaries();
                    break;
                case 6:
                    removeEmployee();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void payStatementMenu() {
        while (true) {
            System.out.println("\n===== PAY STATEMENT MANAGEMENT =====");
            System.out.println("1. View All Pay Statements");
            System.out.println("2. View Pay Statements by Employee ID");
            System.out.println("0. Back to Main Menu");
            System.out.println("====================================");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    viewAllPayStatements();
                    break;
                case 2:
                    viewPayStatementsByEmployeeID();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void reportMenu() {
        while (true) {
            System.out.println("\n===== REPORTS =====");
            System.out.println("1. Employee Report (with Pay Statements)");
            System.out.println("2. Pay by Division Report");
            System.out.println("3. Pay by Title Report");
            System.out.println("0. Back to Main Menu");
            System.out.println("====================");
            
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    reportService.printEmployeeReport();
                    break;
                case 2:
                    generatePayByDivisionReport();
                    break;
                case 3:
                    generatePayByTitleReport();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void searchEmployees() {
        System.out.println("\n===== SEARCH EMPLOYEES =====");
        System.out.println("1. Search by Employee ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Job Title");
        System.out.println("4. Search by Division");
        System.out.println("0. Back to Main Menu");
        
        int choice = getIntInput("Enter your choice: ");
        
        String searchType = "";
        String searchPrompt = "";
        
        switch (choice) {
            case 1:
                searchType = "id";
                searchPrompt = "Enter Employee ID to search: ";
                break;
            case 2:
                searchType = "name";
                searchPrompt = "Enter Name to search: ";
                break;
            case 3:
                searchType = "jobtitle";
                searchPrompt = "Enter Job Title to search: ";
                break;
            case 4:
                searchType = "division";
                searchPrompt = "Enter Division to search: ";
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        System.out.print(searchPrompt);
        String searchValue = scanner.nextLine().trim();
        
        if (searchValue.isEmpty()) {
            System.out.println("Search value cannot be empty.");
            return;
        }
        
        List<Employee> results = employeeService.searchEmployees(searchType, searchValue);
        
        System.out.println("\nSearch Results:");
        if (results.isEmpty()) {
            System.out.println("No employees found matching your criteria.");
        } else {
            for (Employee emp : results) {
                System.out.println(emp.toString());
            }
            System.out.println("Found " + results.size() + " employee(s).");
        }
    }

    private void addEmployee() {
        System.out.println("\n===== ADD EMPLOYEE =====");
        
        System.out.print("Enter Employee ID: ");
        String employeeID = scanner.nextLine();
        
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter SSN: ");
        String ssn = scanner.nextLine();
        
        System.out.print("Enter Job Title: ");
        String jobTitle = scanner.nextLine();
        
        System.out.print("Enter Division: ");
        String division = scanner.nextLine();
        
        double salary = getDoubleInput("Enter Salary: ");
        
        Employee employee = new Employee(employeeID, name, jobTitle, ssn, division, salary);
        
        if (employeeService.addEmployee(employee)) {
            System.out.println("Employee added successfully!");
        } else {
            System.out.println("Failed to add employee.");
        }
    }

    private void viewAllEmployees() {
        System.out.println("\n===== ALL EMPLOYEES =====");
        List<Employee> employees = employeeService.getAllEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            for (Employee emp : employees) {
                System.out.println(emp.toString());
            }
            System.out.println("Total employees: " + employees.size());
        }
    }

    private void viewEmployeeByID() {
        System.out.print("\nEnter Employee ID: ");
        String employeeID = scanner.nextLine();
        
        Employee employee = employeeService.getEmployeeByID(employeeID);
        
        if (employee != null) {
            System.out.println("\nEmployee Details:");
            System.out.println(employee.toString());
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void updateEmployeeSalary() {
        System.out.print("\nEnter Employee ID: ");
        String employeeID = scanner.nextLine();
        
        Employee employee = employeeService.getEmployeeByID(employeeID);
        
        if (employee != null) {
            System.out.println("Current salary: " + employee.getSalary());
            double newSalary = getDoubleInput("Enter new salary: ");
            
            employee.setSalary(newSalary);
            
            if (employeeService.updateEmployeeSalary(employee)) {
                System.out.println("Salary updated successfully!");
            } else {
                System.out.println("Failed to update salary.");
            }
        } else {
            System.out.println("Employee not found.");
        }
    }

    private void bulkUpdateSalaries() {
        System.out.println("\n===== BULK SALARY UPDATE =====");
        
        double minSalary = getDoubleInput("Enter minimum salary: ");
        double maxSalary = getDoubleInput("Enter maximum salary: ");
        double percentageIncrease = getDoubleInput("Enter percentage increase: ");
        
        int affectedRows = employeeService.updateSalaryByRange(minSalary, maxSalary, percentageIncrease);
        
        System.out.println("Updated " + affectedRows + " employee(s).");
    }

    private void removeEmployee() {
        System.out.print("\nEnter Employee ID to remove: ");
        String employeeID = scanner.nextLine();
        
        System.out.print("Are you sure you want to remove employee " + employeeID + "? (y/n): ");
        String confirmation = scanner.nextLine();
        
        if (confirmation.equalsIgnoreCase("y")) {
            if (employeeService.removeEmployee(employeeID)) {
                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("Failed to remove employee.");
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    private void viewAllPayStatements() {
        System.out.println("\n===== ALL PAY STATEMENTS =====");
        List<PayStatement> payStatements = payStatementService.getAllPayStatements();
        
        if (payStatements.isEmpty()) {
            System.out.println("No pay statements found.");
        } else {
            for (PayStatement ps : payStatements) {
                System.out.println(ps.toString());
            }
            System.out.println("Total pay statements: " + payStatements.size());
        }
    }

    private void viewPayStatementsByEmployeeID() {
        System.out.print("\nEnter Employee ID: ");
        String employeeID = scanner.nextLine();
        
        List<PayStatement> payStatements = payStatementService.getPayStatementsByEmployeeID(employeeID);
        
        if (payStatements.isEmpty()) {
            System.out.println("No pay statements found for employee " + employeeID);
        } else {
            System.out.println("\nPay Statements for Employee " + employeeID + ":");
            for (PayStatement ps : payStatements) {
                System.out.println(ps.toString());
            }
            System.out.println("Total pay statements: " + payStatements.size());
        }
    }

    private void generatePayByDivisionReport() {
        int year = getIntInput("Enter year: ");
        System.out.print("Enter month: ");
        String month = scanner.nextLine();
        
        reportService.printPayByDivision(year, month);
    }

    private void generatePayByTitleReport() {
        int year = getIntInput("Enter year: ");
        System.out.print("Enter month: ");
        String month = scanner.nextLine();
        
        reportService.printPayByTitle(year, month);
    }

    private int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}