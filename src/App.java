public class App {
    public static void main(String[] args) throws Exception {

        ReportService reportService = new ReportService();

        // Report test calls
        reportService.printEmployeeReport();
        System.out.println();
        reportService.printPayByDivision(2024, "January");
        System.out.println();
        reportService.printPayByTitle(2024, "January");
    }
}
