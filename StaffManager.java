import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class StaffManager {

    private ArrayList<Staff> staffList;
    private Scanner scanner;

    public StaffManager() {
        staffList = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                      **STAFF SECTION**");
        System.out.println("--------------------------------------------------------------------------------");
        while (running) {
            System.out.println("Enter 1 to add staff, 2 to edit staff, 3 to delete staff, 4 to display staff, or 5 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    addStaff();
                    break;
                case 2:
                    editStaff();
                    break;
                case 3:
                    deleteStaff();
                    break;
                case 4:
                    displayStaff();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addStaff() {
        System.out.println("Enter staff name:");
        String name = scanner.nextLine();
        System.out.println("Enter staff ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter staff designation:");
        String designation = scanner.nextLine();
        System.out.println("Enter staff salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine();

        Staff staff = new Staff(id, name, designation, salary);
        staffList.add(staff);

        System.out.println("Staff added.");
    }

    private void editStaff() {
        System.out.println("Enter staff ID to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Staff staff : staffList) {
            if (staff.getId() == id) {
                System.out.println("Enter new staff name:");
                String name = scanner.nextLine();
                System.out.println("Enter new staff designation:");
                String designation = scanner.nextLine();
                System.out.println("Enter new staff salary:");
                double salary = scanner.nextDouble();
                scanner.nextLine(); // consume newline character
                staff.setName(name);
                staff.setDesignation(designation);
                staff.setSalary(salary);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Staff updated.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    private void deleteStaff() {
        System.out.println("Enter staff ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        boolean removed = staffList.removeIf(staff -> staff.getId() == id);
        if (removed) {
            System.out.println("Staff deleted.");
        } else {
            System.out.println("Staff not found.");
        }
    }

    private void displayStaff() {
        Scanner scan = new Scanner(System.in);
        for (Staff staff : staffList) {
            System.out.println(staff);
        }
        try {
            File file = new File("C:\\Users\\likhi\\OneDrive\\Desktop\\staff.txt");
            boolean value = file.createNewFile();
            if (value) {
                System.out.println("The new file is created.");
            } else {
                System.out.println("The file already exists. Do you want to overwrite it? (y/n)");
                String answer = scan.next();
                if (!answer.equalsIgnoreCase("y")) {
                    System.out.println("Exiting program.");
                    return;
                }
            }
        }catch(Exception e){
            e.getStackTrace();
        }


        try {
            FileWriter output = new FileWriter("C:\\Users\\likhi\\OneDrive\\Desktop\\staff.txt");

            output.write("--------------------------------------------------------------------------------\n");
            output.write("                       **MEDICINE LIST**\n");
            output.write("--------------------------------------------------------------------------------\n");
            for (Staff staff : staffList) {
                output.append("\n");
                output.append("--------------------------------------------------------------------------------\n");
                output.append("Staff Name: " + staff.getName());
                output.append("\n");
                output.append("Staff ID : " + staff.getId());
                output.append("\n");
                output.append("Staff Designation:" +staff.getDesignation());
                output.append("\n");
                output.append("Staff Salary:" +staff.getSalary());
            }
            System.out.println("Data is written to the file.");


            output.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        StaffManager manager = new StaffManager();
        manager.run();
    }
}

class Staff {

    private int id;
    private String name;
    private String designation;
    private double salary;

    public Staff(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name= name;
    }
    public String getDesignation(){
        return designation;
    }
    public void setDesignation(String designation){
        this.designation = designation;
    }
    public double getSalary(){
        return salary;
    }
    public void setSalary(double salary){
        this.salary = salary;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String toString() {
        return "Staff [id=" + id + ", name=" + name + ", designation= "+ designation+", salary= "+salary+"]";
    }
}