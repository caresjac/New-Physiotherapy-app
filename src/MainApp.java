import java.util.*;

public class MainApp {
    private static List<Patient> patients = new ArrayList<>();
    private static List<Physiotherapist> physiotherapists = new ArrayList<>();
    private static List<Booking> bookings = new ArrayList<>();
    private static int bookingCounter = 1001;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        loadInitialData();

        while (true) {
            System.out.println("\n=== Boost Physio Clinic ===");
            System.out.println("1. Add Patient");
            System.out.println("2. Remove Patient");
            System.out.println("3. Book an Appointment");
            System.out.println("4. Change/Cancel Booking");
            System.out.println("5. Attend Appointment");
            System.out.println("6. Print Report (May 2025)");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: addPatient(scanner); break;
                case 2: removePatient(scanner); break;
                case 3: bookAppointment(scanner); break;
                case 4: cancelBooking(scanner); break;
                case 5: attendAppointment(scanner); break;
                case 6: printMay2025Report(); break;
                case 7: System.out.println("Exiting..."); return;
                default: System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void loadInitialData() {
        physiotherapists.add(new Physiotherapist(1788, "Dr. Jackson", "123 Main St", "555-5678"));
        physiotherapists.add(new Physiotherapist(1092, "Dr. Ajibola", "456 Elm St", "555-6789"));
        physiotherapists.add(new Physiotherapist(6103, "Dr. Andrew Wet", "789 Oak Avenue", "555-1122"));
        physiotherapists.add(new Physiotherapist(1404, "Dr. Raph", "321 Pine Lane", "555-3344"));
        physiotherapists.add(new Physiotherapist(1055, "Dr. Demolic Muller", "89 Maple Blvd", "555-5566"));
        physiotherapists.add(new Physiotherapist(1736, "Dr. Mally", "90 Cedar Hill", "555-6677"));
        physiotherapists.add(new Physiotherapist(9244, "Dr. Waffi Boy", "12 King’s Street", "555-7788"));
        physiotherapists.add(new Physiotherapist(5424, "Dr. Jubreal", "45 Queen’s Road", "555-8899"));
        physiotherapists.add(new Physiotherapist(1982, "Dr. Sarah Nwachukwu", "67 Victoria Ave", "555-9900"));
        physiotherapists.add(new Physiotherapist(1108, "Dr. Daniel Obi", "22 Sunset Blvd", "555-1011"));

        // Patients
        patients.add(new Patient(1, "Uthman", "78 Tiger Moth Way", "000-1234"));
        patients.add(new Patient(2, "Ahmed", "59 Hatfield", "111-2345"));
        patients.add(new Patient(3, "Zainab Ali", "34 Willow Road", "222-3456"));
        patients.add(new Patient(4, "Emmanuel Ade", "21 Cedar Street", "333-4567"));
        patients.add(new Patient(5, "Maryam Bello", "16 Birch Close", "444-5678"));
        patients.add(new Patient(6, "Chinedu Okafor", "10 Jasmine Crescent", "555-6789"));
        patients.add(new Patient(7, "Ngozi Eze", "77 Ocean View", "666-7890"));
        patients.add(new Patient(8, "Tunde Bamidele", "28 Liberty Road", "777-8901"));
        patients.add(new Patient(9, "Aisha Sani", "9 Unity Lane", "888-9012"));
        patients.add(new Patient(10, "Kelvin White", "5 Harmony Gardens", "999-0123"));
    }

    private static void addPatient(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();

        patients.add(new Patient(id, name, address, phone));
        System.out.println("Patient added successfully!");
    }

    private static void removePatient(Scanner scanner) {
        System.out.print("Enter Patient ID to Remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        patients.removeIf(p -> p.getId() == id);
        System.out.println("Patient removed successfully!");
    }

    private static void bookAppointment(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        scanner.nextLine();
        Patient patient = patients.stream().filter(p -> p.getId() == patientId).findFirst().orElse(null);
        if (patient == null) {
            System.out.println("Patient not found!");
            return;
        }

        System.out.println("Available Physiotherapists:");
        for (Physiotherapist p : physiotherapists) {
            System.out.println(p.getName());
        }

        System.out.print("Enter Physiotherapist Name: ");
        String physioName = scanner.nextLine();
        Physiotherapist physio = physiotherapists.stream().filter(p -> p.getName().equalsIgnoreCase(physioName)).findFirst().orElse(null);
        if (physio == null) {
            System.out.println("Physiotherapist not found!");
            return;
        }

        System.out.print("Enter Treatment Name: ");
        String treatment = scanner.nextLine();
        System.out.print("Enter Date & Time (e.g., 2025-05-15 14:00): ");
        String dateTime = scanner.nextLine();

        bookings.add(new Booking(bookingCounter++, patient, physio, dateTime, treatment));
        System.out.println("Appointment booked successfully!");
    }

    private static void cancelBooking(Scanner scanner) {
        System.out.print("Enter Booking ID to Cancel or Change: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        Booking booking = bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);
        if (booking == null) {
            System.out.println("Booking not found!");
            return;
        }

        System.out.print("Do you want to (1) Cancel or (2) Change this booking? ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            booking.cancelBooking();
            System.out.println("Booking cancelled successfully!");
        } else if (choice == 2) {
            System.out.print("Enter new Date & Time: ");
            String newDateTime = scanner.nextLine();
            booking.setDateTime(newDateTime);
            System.out.println("Booking updated successfully!");
        }
    }

    private static void attendAppointment(Scanner scanner) {
        System.out.print("Enter Booking ID to Mark as Attended: ");
        int bookingId = scanner.nextInt();
        scanner.nextLine();

        Booking booking = bookings.stream().filter(b -> b.getBookingId() == bookingId).findFirst().orElse(null);
        if (booking == null) {
            System.out.println("Booking not found!");
            return;
        }

        booking.attendAppointment();
        System.out.println("Appointment marked as attended.");
    }

    private static void printMay2025Report() {
        System.out.println("\n=== Clinic Report (May 2025) ===");
        for (Booking booking : bookings) {
            if (booking.getDateTime().startsWith("2025-05")) {
                System.out.println("Booking ID: " + booking.getBookingId() + ", Patient: " + booking.getPatient().getName() + ", Physiotherapist: " + booking.getPhysiotherapist().getName() + ", DateTime: " + booking.getDateTime() + ", Status: " + booking.getStatus());
            }
        }
    }
}
