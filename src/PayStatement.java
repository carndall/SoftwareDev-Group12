public class PayStatement {
    private int employeeID;
    private String payPeriod;
    private double amount;

    public PayStatement(int employeeID, String payPeriod, double amount) {
        this.employeeID = employeeID;
        this.payPeriod = payPeriod;
        this.amount = amount;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getPayPeriod() {
        return payPeriod;
    }

    public double getAmount() {
        return amount;
    }

}
