package lab5;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter Employee details
        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Employee Email: ");
        String email = scanner.nextLine();

        System.out.print("Enter Hourly Rate: ");
        double hourRate = scanner.nextDouble();

        System.out.print("Enter Working Capacity (hours per day): ");
        int capacity = scanner.nextInt();

        System.out.print("Enter Free Days: ");
        int freeDays = scanner.nextInt();


        Employee employee = new Employee(name, email, hourRate, capacity, freeDays);


        int dailyIncome = employee.calculateDailyIncome();
        System.out.println("Daily Income: $" + dailyIncome);


        employee.printMonthlyIncome();

        scanner.close();
    }
}
