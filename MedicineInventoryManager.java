import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
public class MedicineInventoryManager {

    private ArrayList<Medicine> medicines;
    private Scanner scanner;

    public MedicineInventoryManager() {
        medicines = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void run() {
        boolean running = true;
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("                      **MEDICINE SECTION**");
        System.out.println("--------------------------------------------------------------------------------");
        while (running) {
            System.out.println("Enter 1 to add medicine, 2 to edit medicine, 3 to delete medicine, 4 to display inventory, or 5 to exit:");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            switch (choice) {
                case 1:
                    addMedicine();
                    break;
                case 2:
                    editMedicine();
                    break;
                case 3:
                    deleteMedicine();
                    break;
                case 4:
                    displayInventory();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void addMedicine() {
        System.out.println("Enter medicine name:");
        String name = scanner.nextLine();
        System.out.println("Enter medicine ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline character
        System.out.println("Enter medicine price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Enter medicine quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        Medicine medicine = new Medicine(id, name, price, quantity);
        medicines.add(medicine);

        System.out.println("Medicine added.");
    }

    private void editMedicine() {
        System.out.println("Enter medicine ID to edit:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean found = false;
        for (Medicine medicine : medicines) {
            if (medicine.getId() == id) {
                System.out.println("Enter new medicine name:");
                String name = scanner.nextLine();
                medicine.setName(name);
                System.out.println("Enter new medicine price:");
                double price = scanner.nextDouble();
                scanner.nextLine();
                medicine.setPrice(price);
                System.out.println("Enter new medicine quantity:");
                int quantity = scanner.nextInt();
                scanner.nextLine();
                medicine.setQuantity(quantity);
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Medicine updated.");
        } else {
            System.out.println("Medicine not found.");
        }
    }

    private void deleteMedicine() {
        System.out.println("Enter medicine ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine();

        boolean removed = medicines.removeIf(medicine -> medicine.getId() == id);
        if (removed) {
            System.out.println("Medicine deleted.");
        } else {
            System.out.println("Medicine not found.");
        }
    }

    private void displayInventory() {
        Scanner scan = new Scanner(System.in);
        for (Medicine medicine : medicines) {
            System.out.println(medicine);
        }
        try {
            File file = new File("C:\\Users\\likhi\\OneDrive\\Desktop\\medicine.txt");
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
            FileWriter output = new FileWriter("C:\\Users\\likhi\\OneDrive\\Desktop\\medicine.txt");

            output.write("--------------------------------------------------------------------------------\n");
            output.write("                       **MEDICINE LIST**\n");
            output.write("--------------------------------------------------------------------------------\n");
            for (Medicine medicine : medicines) {
                output.append("\n");
                output.append("--------------------------------------------------------------------------------\n");
                output.append("Medicine Name: " + medicine.getName());
                output.append("\n");
                output.append("Medicine ID : " + medicine.getId());
                output.append("\n");
                output.append("Medicine Quantity:" +medicine.getQuantity());
                output.append("\n");
                output.append("Medicine Price(Per Unit):" +medicine.getPrice());
            }
            System.out.println("Data is written to the file.");

            // Closes the writer
            output.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }



    public static void main(String[] args) {
        MedicineInventoryManager manager = new MedicineInventoryManager();
        manager.run();
    }
}

class Medicine {

    private int id;
    private String name;
    private double price;
    private int quantity;

    public Medicine(int id, String name, double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    public int getId(){
    return id;
    }
    public void setId(){
        this.id =  id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public double getPrice(){
        return price;
    }
    public void setPrice(double price){
        this.price = price;
    }

    public double getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity){
        this.quantity =  quantity;
    }
    @Override
    public String toString() {
        return "Medicine [id=" + id + ", name=" + name + ", price(of 1 unit)= "+ price+", quantity= "+quantity+"]";
    }
}

