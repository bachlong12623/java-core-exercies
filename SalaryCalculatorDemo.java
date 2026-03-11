import java.math.BigDecimal;

public class SalaryCalculatorDemo {
    public static void main(String[] args) {
        BigDecimal hourlySalary = new BigDecimal("50000"); // 50,000 VND per hour
        Integer monthlyWorkHours = 160;

        BigDecimal monthlySalary = calculateMonthlySalary(hourlySalary, monthlyWorkHours);

        System.out.println("Hourly salary: " + hourlySalary);
        System.out.println("Monthly work hours: " + monthlyWorkHours);
        System.out.println("Monthly salary: " + monthlySalary);
    }

    // Static method: monthly salary = hourly salary x monthly work hours.
    public static BigDecimal calculateMonthlySalary(BigDecimal hourlySalary, Integer monthlyWorkHours) {
        return hourlySalary.multiply(BigDecimal.valueOf(monthlyWorkHours));
    }
}
