package service;

import dao.ExperienceDAO;
import dao.FresherDAO;
import dao.InternDAO;
import model.Employee;
import model.Experience;
import model.Fresher;
import model.Intern;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeService {
    private List<Employee> employees;
    private ExperienceDAO experienceDAO;
    private FresherDAO fresherDAO;
    private InternDAO internDAO;

    public EmployeeService() {
        this.employees = new ArrayList<>();
        experienceDAO = new ExperienceDAO();
        fresherDAO = new FresherDAO();
        internDAO = new InternDAO();
    }

    public void getEmployee() {
        List<Experience> experiences = experienceDAO.getAllExperiences();
        List<Fresher> freshers = fresherDAO.getAllFreshers();
        List<Intern> interns = internDAO.getAllInterns();
        employees.addAll(experiences);
        employees.addAll(freshers);
        employees.addAll(interns);
        Employee.setEmployeeCount(experiences.size() + freshers.size() + interns.size());
    }

    public void createEmployee(Scanner scanner) {
        System.out.print("Enter ID: ");
        String ID = scanner.nextLine();
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        System.out.print("Enter Birth Day: ");
        String birthDay = scanner.nextLine();
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (true) {
            System.out.println("Enter Employee Type");
            System.out.println("1 - Experience");
            System.out.println("2 - Fresher");
            System.out.println("3 - Intern");
            String employeeType = scanner.nextLine();
            if (employeeType.equals("1")) {
                System.out.print("Enter Years of Experience: ");
                int expInYear = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                System.out.print("Enter Professional Skill: ");
                String proSkill = scanner.nextLine();
                experienceDAO.addExperience(new Experience(ID, fullName, birthDay, phone, email, expInYear, proSkill));
                return;
            } else if (employeeType.equals("2")) {
                System.out.print("Enter Graduation Date: ");
                String graduationDate = scanner.nextLine();
                System.out.print("Enter Graduation Rank: ");
                String graduationRank = scanner.nextLine();
                System.out.print("Enter Education: ");
                String education = scanner.nextLine();
                fresherDAO.addFresher(new Fresher(ID, fullName, birthDay, phone, email, graduationDate, graduationRank, education));
                return;
            } else if (employeeType.equals("3")) {
                System.out.print("Enter Majors: ");
                String majors = scanner.nextLine();
                System.out.print("Enter Semester: ");
                String semester = scanner.nextLine();
                System.out.print("Enter University Name: ");
                String universityName = scanner.nextLine();
                internDAO.addIntern(new Intern(ID, fullName, birthDay, phone, email, majors, semester, universityName));
                return;
            } else {
                System.out.println("Invalid Employee Type!");
            }
        }
    }

    public void updateEmployee(String ID, Scanner scanner) {
        Employee employeeToUpdate = findEmployeeByID(ID);
        if (employeeToUpdate != null) {
            System.out.println("Updating employee with ID: " + ID);
            System.out.println("1 - Full Name");
            System.out.println("2 - Birth Day");
            System.out.println("3 - Phone");
            System.out.println("4 - Email");
            System.out.println("5 - Employee Type");
            System.out.println("6 - Other Information");

            System.out.print("Choose field to update: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter new Full Name: ");
                    employeeToUpdate.setFullName(scanner.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new Birth Day: ");
                    employeeToUpdate.setBirthDay(scanner.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new Phone: ");
                    employeeToUpdate.setPhone(scanner.nextLine());
                    break;
                case 4:
                    System.out.print("Enter new Email: ");
                    employeeToUpdate.setEmail(scanner.nextLine());
                    break;
                case 5:
                    System.out.print("Enter new Employee Type: ");
                    employeeToUpdate.setEmployeeType(scanner.nextLine());
                    break;
                case 6:
                    if (employeeToUpdate instanceof Experience) {
                        Experience experience = (Experience) employeeToUpdate;
                        System.out.print("Enter new Years of Experience: ");
                        experience.setExpInYear(scanner.nextInt());
                        scanner.nextLine();
                        System.out.print("Enter new Professional Skill: ");
                        experience.setProSkill(scanner.nextLine());
                    } else if (employeeToUpdate instanceof Fresher) {
                        Fresher fresher = (Fresher) employeeToUpdate;
                        System.out.print("Enter new Graduation Date: ");
                        fresher.setGraduationDate(scanner.nextLine());
                        System.out.print("Enter new Graduation Rank: ");
                        fresher.setGraduationRank(scanner.nextLine());
                        System.out.print("Enter new Education: ");
                        fresher.setEducation(scanner.nextLine());
                    } else if (employeeToUpdate instanceof Intern) {
                        Intern intern = (Intern) employeeToUpdate;
                        System.out.print("Enter new Majors: ");
                        intern.setMajors(scanner.nextLine());
                        System.out.print("Enter new Semester: ");
                        intern.setSemester(scanner.nextLine());
                        System.out.print("Enter new University Name: ");
                        intern.setUniversityName(scanner.nextLine());
                    }
                    break;
                default:
                    System.out.println("Invalid choice!");
                    return;
            }
            if (employeeToUpdate instanceof Experience) {
                experienceDAO.updateExperience((Experience) employeeToUpdate);
            } else if (employeeToUpdate instanceof Fresher) {
                fresherDAO.updateFresher((Fresher) employeeToUpdate);
            } else if (employeeToUpdate instanceof Intern) {
                internDAO.updateIntern((Intern) employeeToUpdate);
            }
        } else {
            System.out.println("Employee with ID " + ID + " not found!");
        }
    }

    public void deleteEmployee(String ID) {
        Employee employeeToDelete = findEmployeeByID(ID);
        if (employeeToDelete != null) {
            if (employeeToDelete instanceof Experience) {
                experienceDAO.deleteExperience(employeeToDelete.getID());
            } else if (employeeToDelete instanceof Fresher) {
                fresherDAO.deleteFresher(employeeToDelete.getID());
            } else if (employeeToDelete instanceof Intern) {
                internDAO.deleteIntern(employeeToDelete.getID());
            }
            System.out.println("Employee with ID " + ID + " has been deleted.");
        } else {
            System.out.println("Employee with ID " + ID + " not found!");
        }
    }

    private Employee findEmployeeByID(String ID) {
        getEmployee();
        for (Employee employee : employees) {
            if (employee.getID().equals(ID)) {
                return employee;
            }
        }
        return null;
    }

    public void displayAllEmployees() {
        getEmployee();
        System.out.println("Employee List:");
        for (Employee employee : employees) {
            employee.showInfo();
            System.out.println("---------------------------");
        }
        System.out.println("Total number of employees: " + Employee.getEmployeeCount());
    }

    public int getTotalEmployees() {
        return Employee.getEmployeeCount();
    }
}

