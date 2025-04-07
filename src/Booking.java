public class Booking {
    private int bookingId;
    private Patient patient;
    private Physiotherapist physiotherapist;
    String dateTime;
    private String treatment;
    private String status; // "Booked", "Cancelled", "Attended"

    public Booking(int bookingId, Patient patient, Physiotherapist physiotherapist, String dateTime, String treatment) {
        this.bookingId = bookingId;
        this.patient = patient;
        this.physiotherapist = physiotherapist;
        this.dateTime = dateTime;
        this.treatment = treatment;
        this.status = "Booked";
    }

    public int getBookingId() { return bookingId; }
    public Patient getPatient() { return patient; }
    public Physiotherapist getPhysiotherapist() { return physiotherapist; }
    public String getDateTime() { return dateTime; }
    public String getTreatment() { return treatment; }
    public String getStatus() { return status; }

    public void cancelBooking() {
        this.status = "Cancelled";
    }

    public void attendAppointment() {
        this.status = "Attended";
    }

    public void displayBookingInfo() {
        System.out.println("Booking ID: " + bookingId);
        System.out.println("Patient: " + patient.getName());
        System.out.println("Physiotherapist: " + physiotherapist.getName());
        System.out.println("Date/Time: " + dateTime);
        System.out.println("Treatment: " + treatment);
        System.out.println("Status: " + status);
    }
}
