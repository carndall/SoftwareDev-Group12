public class Employee {
    private int employeeID;
    private String name;
    private String jobTitle;
    private String division;
    private double salary;
    private String ssn;

    public Employee(int employeeID, String name, String jobTitle, String division, double salary) {
        this.employeeID = employeeID;
        this.name = name;
        this.jobTitle = jobTitle;
        this.division = division;
        this.salary = salary;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getDivision() {
        return division;
    }

    public String getSsn() {
        return ssn;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

}
