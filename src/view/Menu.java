package view;

import service.EmployeeService;

import java.util.Scanner;

public class Menu {
    private EmployeeService employeeService;
    private Scanner scanner;

    public Menu() {
        this.employeeService = new EmployeeService();
        this.scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        String cyan = "\u001B[36m";
        String reset = "\u001B[0m";
        String red = "\u001B[31m";
        while (true) {
            System.out.println(cyan + "=== Employee Management System ===" + reset);
            System.out.println("1. Add Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print(red + "Choose option: " + reset);
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    employeeService.createEmployee(scanner);
                    System.out.println("Employee added successfully!");
                    break;
                case 2:
                    employeeService.displayAllEmployees();
                    break;
                case 3:
                    System.out.print("Enter ID of the employee to update: ");
                    String updateID = scanner.nextLine();
                    employeeService.updateEmployee(updateID, scanner);
                    break;
                case 4:
                    System.out.print("Enter ID of the employee to delete: ");
                    String deleteID = scanner.nextLine();
                    employeeService.deleteEmployee(deleteID);
                    break;
                case 5:
                    System.out.println("Count Employee");
                    System.out.println(employeeService.getTotalEmployees() + "Employee");
                    break;
                case 6:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option! Please choose again.");
                    break;
            }
        }
    }
}

