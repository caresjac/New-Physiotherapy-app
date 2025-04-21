import java.util.*;

public class TimetableSchedule {
    private Map<Integer, List<Appointment>> weeklySchedule;

    public TimetableSchedule() {
        weeklySchedule = new HashMap<>();
    }

    // weekIndex: 1 to 4, representing May 2025 weeks
    public void addAppointment(int weekIndex, Appointment appointment) {
        weeklySchedule.putIfAbsent(weekIndex, new ArrayList<>());
        weeklySchedule.get(weekIndex).add(appointment);
    }

    public List<Appointment> getAppointmentsForWeek(int weekIndex) {
        return weeklySchedule.getOrDefault(weekIndex, new ArrayList<>());
    }

    public void displaySchedule() {
        for (int week : weeklySchedule.keySet()) {
            System.out.println("===== Week " + week + " (May 2025) =====");
            for (Appointment appt : weeklySchedule.get(week)) {
                System.out.println("Therapist: " + appt.getTherapistName());
                System.out.println("Date: " + appt.getDate());
                System.out.println("Time: " + appt.getTime());
                System.out.println("Patient: " + appt.getPatient().getName());
                System.out.println("---------------------------");
            }
        }
    }
}