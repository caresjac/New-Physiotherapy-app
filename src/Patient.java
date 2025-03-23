import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<Booking> bookings;

    public Patient(int id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.bookings = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    public void displayPatientInfo() {
        System.out.println("Patient ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
    }
}
