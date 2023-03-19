import javax.print.Doc;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class DoctorManager {

    private ArrayList<Doctor> doctors;
    private Scanner scanner;

    public DoctorManager() {
        doctors = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void rund() {
        boolean running = true;
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                      **DOCTOR SECTION**");
        System.out.println("--------------------------------------------------------------------------------");
        while (running) {
            System.out.println("Enter 1 to add doctor, 2 to edit doctor, 3 to delete doctor, 4 to display doctors, or 5 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addDoctor();
                    break;
                case 2:
                    editDoctor();
                    break;
                case 3:
                    deleteDoctor();
                    break;
                case 4:
                    displayDoctors();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addDoctor() {
        System.out.println("Enter doctor name:");
        String name = scanner.nextLine();
        System.out.println("Enter doctor ID:");
        int id = scanner.nextInt();
        System.out.println("Enter qualifications:");
        String qualifications = scanner.next();
        scanner.nextLine();

        Doctor doctor = new Doctor(id, name,qualifications);
        doctors.add(doctor);

        System.out.println("Doctor added.");
    }

    private void editDoctor() {
        System.out.println("Enter doctor ID to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) {
                System.out.println("Enter new doctor name:");
                String name = scanner.nextLine();
                System.out.println("Enter his Qualifications:");
                String qualifications = scanner.nextLine();
                doctor.setName(name);
                doctor.setQualifications(qualifications);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Doctor updated.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void deleteDoctor() {
        System.out.println("Enter doctor ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = doctors.removeIf(doctor -> doctor.getId() == id);
        if (removed) {
            System.out.println("Doctor deleted.");
        } else {
            System.out.println("Doctor not found.");
        }
    }

    private void displayDoctors() {
        Scanner scan = new Scanner(System.in);
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
        try {
            File file = new File("C:\\Users\\likhi\\OneDrive\\Desktop\\doctor.txt");
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
            FileWriter output = new FileWriter("C:\\Users\\likhi\\OneDrive\\Desktop\\doctor.txt");

            output.write("--------------------------------------------------------------------------------\n");
            output.write("                       **DOCTOR LIST**\n");
            output.write("--------------------------------------------------------------------------------\n");
            for (Doctor doctor : doctors) {
                output.append("\n");
                output.append("--------------------------------------------------------------------------------\n");
                output.append("Doctor Name: " + doctor.getName());
                output.append("\n");
                output.append("Doctor ID : " + doctor.getId());
                output.append("\n");
                output.append("Doctor Qualifications:" +doctor.getQualifications());
                output.append("\n");
            }
            System.out.println("Data is written to the file.");

            // Closes the writer
            output.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public static void main(String[] args) {
        DoctorManager manager = new DoctorManager();
        manager.rund();
    }
}

class Doctor {

    private int id;
    private String name;
    private String qualifications;

    public Doctor(int id, String name,String qualifications) {
        this.id = id;
        this.name = name;
        this.qualifications = qualifications;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getQualifications(){
        return qualifications;
    }
    public void setQualifications(String qualifications){
        this.qualifications = qualifications;
    }

    @Override
    public String toString() {
        return "Doctor [id=" + id + ", name=" + name + ", qualifications= "+ qualifications+"]";
    }
}
