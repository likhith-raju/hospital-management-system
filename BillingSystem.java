import java.io.*;
import java.util.Scanner;

public class BillingSystem {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            File file = new File("C:\\Users\\likhi\\OneDrive\\Desktop\\newfile.txt");
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
            try {
                FileWriter output = new FileWriter("C:\\Users\\likhi\\OneDrive\\Desktop\\newfile.txt");

                output.write("--------------------------------------------------------------------------------\n");
                output.write("                       **BILLING SYSTEM**\n");
                output.write("--------------------------------------------------------------------------------\n");
                System.out.println("Enter Patient ID :");
                int id = scan.nextInt();
                System.out.println("Enter Patient's Name");
                String name = scan.next();
                System.out.println("Patient is diagnosed with:");
                String diagnosis = scan.next();
                System.out.println("Enter Number of medicine purchased:");
                int medicineNo = scan.nextInt();
                String[] medicineName = new String[medicineNo];
                Double[] medicinePrice = new Double[medicineNo];
                Double[] medicineQuantity = new Double[medicineNo];
                for (int i = 0; i < medicineNo; i++) {
                    System.out.println("Enter Medicine Name :");
                    medicineName[i] = scan.next();
                    System.out.println("Enter Medicine price(per unit) :");
                    medicinePrice[i] = scan.nextDouble();
                    System.out.println("Enter Medicine Quantity :");
                    medicineQuantity[i] = scan.nextDouble();
                }
                Patient p = new Patient(id, name, diagnosis);
                output.append("Patient ID: " + p.id + "\n");
                output.append("Patient Name: " + p.name + "\n");
                output.append("Patient is diagnosed with : " + p.diagnosis);
                output.append("\n");
                for (int i = 0; i < medicineNo; i++) {
                    output.append("Medicine Purchased: ");
                    output.append(medicineName[i]);
                    output.append("\n");
                    output.append("Medicine Price: ");
                    output.append(String.valueOf(medicinePrice[i]));
                    output.append("\n");
                    output.append("Medicine Quantity: ");
                    output.append(String.valueOf(medicineQuantity[i]));
                    output.append("\n");
                    output.append("--------------------------------------------------------------------------------\n");

                }
                output.append("Total Price :");
                double sum=0.0;
                for(int i=0;i<medicineNo;i++){

                    sum += medicinePrice[i]*medicineQuantity[i];
                }
                output.append(String.valueOf(sum));


                System.out.println("Data is written to the file.");

                // Closes the writer
                output.close();

            } catch (Exception e) {
                e.getStackTrace();
            }
        }catch(Exception e){
            e.getStackTrace();
        }
    }
}

