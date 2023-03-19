import java.util.Calendar;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        String months[] = {
                "Jan",
                "Feb",
                "Mar",
                "Apr",
                "May",
                "Jun",
                "Jul",
                "Aug",
                "Sep",
                "Oct",
                "Nov",
                "Dec"
        };
        Calendar calendar = Calendar.getInstance();
        System.out.println("\n--------------------------------------------------------------------------------");
        System.out.println("            *** Welcome to Hospital Management System Project in Java ***");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.print("Date: " + months[calendar.get(Calendar.MONTH)] + " " + calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.YEAR));
        System.out.println("\t\t\t\t\t\t\t\t\t\t\tTime: " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE) + ":" + calendar.get(Calendar.SECOND));
        boolean running = true;
        while(running) {
            System.out.println("--------------------------------------------------------------------------------");
            System.out.println("\n                                    MAIN MENU");
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("1.Doctors  2. Patients  3.Medicines  4.Staff  5. Billing  6. Exit ");
            System.out.println("-----------------------------------------------------------------------------------");
            Scanner scan = new Scanner(System.in);
            int choice = scan.nextInt();

            switch (choice) {
                case 1:
                    DoctorManager doc = new DoctorManager();
                    doc.main(args);break;

                case 2: PatientManager pat = new PatientManager();
                        pat.main(args);break;
                case 3: MedicineInventoryManager med = new MedicineInventoryManager();
                        med.main(args); break;
                case 4: StaffManager staff = new StaffManager();
                        staff.main(args);break;
                case 5:BillingSystem bil =  new BillingSystem();
                        bil.main(args);break;
                case 6: running = false;
                        break;
                default:
                    System.out.println("Enter a Valid Choice");
            }
        }
    }
}
