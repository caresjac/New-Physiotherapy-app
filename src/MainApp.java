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
        Physiotherapist p1 = new Physiotherapist(101, "Dr. Jackson", "123 Main St", "555-5678");
        Physiotherapist p2 = new Physiotherapist(102, "Dr. Ajibola", "456 Elm St", "555-6789");
        physiotherapists.add(p1);
        physiotherapists.add(p2);

        Patient pat1 = new Patient(1, "Uthman", "78 tiger moth way", "000-1234");
        Patient pat2 = new Patient(2, "Ahmed ", "59 hatfield", "111-2345");
        patients.add(pat1);
        patients.add(pat2);
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
