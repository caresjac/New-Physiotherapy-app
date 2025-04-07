import java.util.ArrayList;
import java.util.List;

public class Physiotherapist {
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    private List<Treatment> treatments;
    private TimetableSchedule schedule;

    public Physiotherapist(int id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.treatments = new ArrayList<>();
        this.schedule = new TimetableSchedule();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; }

    public void addTreatment(Treatment treatment) {
        treatments.add(treatment);
    }

    public List<Treatment> getTreatments() {
        return treatments;
    }

    public TimetableSchedule getSchedule() {
        return schedule;
    }

    public void displayInfo() {
        System.out.println("Physiotherapist ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone: " + phoneNumber);
    }
}
