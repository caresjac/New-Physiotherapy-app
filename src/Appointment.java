public class Appointment {
    private String date;
    private String time;
    private String therapistName;
    private Patient patient;

    public Appointment(String date, String time, String therapistName, Patient patient) {
        this.date = date;
        this.time = time;
        this.therapistName = therapistName;
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getTherapistName() {
        return therapistName;
    }

    public Patient getPatient() {
        return patient;
    }

    public void displayAppointment() {
        System.out.println("Date: " + date);
        System.out.println("Time: " + time);
        System.out.println("Therapist: " + therapistName);
        System.out.println("Patient: " + patient.getName());
    }
}
