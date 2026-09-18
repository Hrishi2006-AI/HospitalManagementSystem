package hospital;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportService {
    public void printReport(List<Patient> patients, List<Doctor> doctors,
                            List<Appointment> appointments) {
        System.out.println("\n========== HOSPITAL REPORT ==========");
        System.out.println("Total patients     : " + patients.size());
        System.out.println("Total doctors      : " + doctors.size());
        System.out.println("Total appointments : " + appointments.size());

        long booked = appointments.stream()
                .filter(a -> a.getStatus().equals("BOOKED")).count();
        long cancelled = appointments.stream()
                .filter(a -> a.getStatus().equals("CANCELLED")).count();

        System.out.println("Booked appointments: " + booked);
        System.out.println("Cancelled          : " + cancelled);

        Map<String, Integer> departmentCount = new HashMap<>();
        for (Doctor doctor : doctors) {
            departmentCount.merge(doctor.getDepartment(), 1, Integer::sum);
        }

        System.out.println("\nDoctors by department:");
        if (departmentCount.isEmpty()) {
            System.out.println("No doctor data available.");
        } else {
            departmentCount.forEach((department, count) ->
                    System.out.println("- " + department + ": " + count));
        }
        System.out.println("=====================================\n");
    }
}
