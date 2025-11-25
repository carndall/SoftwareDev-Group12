public class PayStatement {
    private String employeeID;
    private String payPeriod;
    private double amount;

    public PayStatement(String employeeID, String payPeriod, double amount) {
        this.employeeID = employeeID;
        this.payPeriod = payPeriod;
        this.amount = amount;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public double getAmount() {
        return amount;
    }

    public String toString() {
        return "Employee ID: " + employeeID + ", Pay Period: " + payPeriod + ", Amount: " + amount;
    }

}
