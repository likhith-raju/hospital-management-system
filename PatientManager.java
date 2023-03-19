import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class PatientManager {

    private ArrayList<Patient> patients;
    private Scanner scanner;

    public PatientManager() {
        patients = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void runp() {
        boolean running = true;
        while (running) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("                      **PATIENT SECTION**");
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("Enter 1 to add patient, 2 to edit patient, 3 to delete patient, 4 to display patients, or 5 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addPatient();
                    break;
                case 2:
                    editPatient();
                    break;
                case 3:
                    deletePatient();
                    break;
                case 4:
                    displayPatients();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }



    private void addPatient() {
        System.out.println("Enter patient name:");
        String name = scanner.nextLine();
        System.out.println("Enter patient ID:");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("The patient is diagnosed with:");
        String diagnosis = scanner.nextLine();

        Patient patient = new Patient(id, name,diagnosis);
        patients.add(patient);

        System.out.println("Patient added.");
    }

    private void editPatient() {
        System.out.println("Enter patient ID to edit:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character

        boolean found = false;
        for (Patient patient : patients) {
            if (patient.getId() == id) {
                System.out.println("Enter new patient name:");
                String name = scanner.nextLine();
                System.out.println("The patient is diagnosed with:");
                String diagnosis = scanner.nextLine();
                patient.setName(name);
                patient.setDiagnosis(diagnosis);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Patient updated.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void deletePatient() {
        System.out.println("Enter patient ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = patients.removeIf(patient -> patient.getId() == id);
        if (removed) {
            System.out.println("Patient deleted.");
        } else {
            System.out.println("Patient not found.");
        }
    }

    private void displayPatients() {
        Scanner scan = new Scanner(System.in);
        for (Patient patient : patients) {
            System.out.println(patient);
        }
        try {
            File file = new File("C:\\Users\\likhi\\OneDrive\\Desktop\\patient.txt");
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
                    FileWriter output = new FileWriter("C:\\Users\\likhi\\OneDrive\\Desktop\\patient.txt");

                    output.write("--------------------------------------------------------------------------------\n");
                    output.write("                       **PATIENT LIST**\n");
                    output.write("--------------------------------------------------------------------------------\n");
                    for (Patient patient : patients) {
                        output.append("\n");
                        output.append("--------------------------------------------------------------------------------\n");
                        output.append("Patient Name: " + patient.name);
                        output.append("\n");
                        output.append("Patient ID : " + patient.id);
                        output.append("\n");
                        output.append("Patient is Diagnoised with:" + patient.diagnosis);
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
        PatientManager manager = new PatientManager();
        manager.runp();
    }
}

class Patient {

    public  int id;
    public String name;
    public String diagnosis;

    public Patient(int id, String name,String diagnosis) {
        this.id = id;
        this.name = name;
        this.diagnosis =diagnosis;
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
    public String getDiagnosis(){
        return diagnosis;
    }
    public void setDiagnosis(String diagnosis){
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "Patient [id=" + id + ", name=" + name + ", diagnosis="+diagnosis+"]";
    }
}
