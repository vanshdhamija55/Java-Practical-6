import java.util.*;
import java.io.*;

class Employee {
    String name;
    int id;
    String designation;
    double salary;

    Employee(String name, int id, String designation, double salary) {
        this.name = name;
        this.id = id;
        this.designation = designation;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Designation: " + designation + " | Salary: " + salary;
    }

    public String toFileString() {
        return id + "," + name + "," + designation + "," + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split(",");
        return new Employee(parts[1], Integer.parseInt(parts[0]), parts[2], Double.parseDouble(parts[3]));
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String fileName = "employees.txt";
        while (true) {
            System.out.println("\n1. Add Employee\n2. Display All Employees\n3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Enter Designation: ");
                String designation = sc.nextLine();
                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();
                sc.nextLine();
                Employee emp = new Employee(name, id, designation, salary);
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                    bw.write(emp.toFileString());
                    bw.newLine();
                    System.out.println("Employee added successfully.");
                } catch (IOException e) {
                    System.out.println("Error writing to file: " + e.getMessage());
                }
            } else if (choice == 2) {
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    boolean hasData = false;
                    while ((line = br.readLine()) != null) {
                        Employee emp = Employee.fromFileString(line);
                        System.out.println(emp);
                        hasData = true;
                    }
                    if (!hasData) System.out.println("No employee records found.");
                } catch (FileNotFoundException e) {
                    System.out.println("File not found. No records to display.");
                } catch (IOException e) {
                    System.out.println("Error reading file: " + e.getMessage());
                }
            } else if (choice == 3) {
                System.out.println("Exiting application.");
                break;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
        sc.close();
    }
}
