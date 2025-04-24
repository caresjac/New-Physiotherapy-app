import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class MainAppTest {

    @Test
    public void testAddPatient() {


        MainApp.addPatient(1, "John Doe", "123 Street", "08012345678");

        assertEquals(1, MainApp.patients.size());

        Patient p = MainApp.patients.get(0);
        assertEquals(1, p.getId());
        assertEquals("John Doe", p.getName());
        assertEquals("123 Street", p.getAddress());
        assertEquals("08012345678", p.getPhoneNumber());
    }

    @Test
    public void testRemovePatientById() {

        Patient p1 = new Patient(1, "John", "Street 1", "08011111111");
        Patient p2 = new Patient(2, "Jane", "Street 2", "08022222222");

        MainApp.patients.add(p1);
        MainApp.patients.add(p2);

        boolean result = MainApp.removePatientById(1);

        assertTrue(result);
        assertEquals(1, MainApp.patients.size());
        assertEquals(2, MainApp.patients.get(0).getId()); // Only Jane should remain
    }

    @Test
    public void testBookAppointment() {

        MainApp.patients.clear();
        MainApp.physiotherapists.clear();
        MainApp.bookings.clear();
        MainApp.bookingCounter = 1;

        // Add test data
        MainApp.patients.add(new Patient(1, "John", "123 St", "08012345678"));
        MainApp.physiotherapists.add(new Physiotherapist(2, "Dr. Smith", "30 Mosquito St", "090234578698"));

        String input = "1\nDr. Smith\nMassage\n2025-05-15 14:00\n";
        Scanner scanner = new Scanner(input);

        MainApp.testBookAppointment(scanner);

        assertEquals(1, MainApp.bookings.size());
        Booking booking = MainApp.bookings.get(0);
        assertEquals("Massage", booking.getTreatment());
        assertEquals("2025-05-15 14:00", booking.getDateTime());
    }

   @Test
    public void testCancelBooking() {
        // Setup test data
        MainApp.bookings.clear();
        MainApp.bookingCounter = 1;

        Patient patient = new Patient(1, "John", "123 Main", "08011112222");
        Physiotherapist physio = new Physiotherapist(3, "Dr. Max", "40 Townsend", "01233455679");
        Booking booking = new Booking(1, patient, physio, "2025-05-20 10:00", "Therapy");
        MainApp.bookings.add(booking);

        String input = "1\n1\n";
        Scanner scanner = new Scanner(input);

       ByteArrayOutputStream output = new ByteArrayOutputStream();
       PrintStream originalOut = System.out;
       System.setOut(new PrintStream(output));

       MainApp.testCancelBooking(scanner);

       // Restore original output
       System.setOut(originalOut);

       // Get the printed output
       String printed = output.toString();

       assertTrue(printed.contains("Booking cancelled successfully!"));
    }

    @Test
    public void testGetMay2025Bookings() {
        // Setup test data
        MainApp.bookings.clear();

        // Add bookings: one in May 2025 and one outside May
        Patient patient1 = new Patient(1, "Alice", "12 Street", "08012345678");
        Physiotherapist physio1 = new Physiotherapist(1, "Dr. Jane", "Clinic Road", "0123456789");
        Booking booking1 = new Booking(1, patient1, physio1, "2025-05-12 09:00", "Physio");

        Patient patient2 = new Patient(2, "Bob", "45 Avenue", "08087654321");
        Physiotherapist physio2 = new Physiotherapist(2, "Dr. Mark", "Clinic Lane", "0987654321");
        Booking booking2 = new Booking(2, patient2, physio2, "2025-06-10 14:00", "Massage");

        MainApp.bookings.add(booking1);
        MainApp.bookings.add(booking2);

        List<Booking> result = MainApp.getMay2025BookingsForTest();

        assertEquals(1, result.size());
        assertEquals("Alice", result.get(0).getPatient().getName());
        assertEquals("2025-05-12 09:00", result.get(0).getDateTime());
    }


}
