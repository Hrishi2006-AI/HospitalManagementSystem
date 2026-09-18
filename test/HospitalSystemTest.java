import hospital.*;

import java.io.File;
import java.util.List;

public class HospitalSystemTest {
    public static void main(String[] args) {
        File temp = new File("test-data");
        delete(temp);

        DataStore store = new DataStore("test-data");
        PatientService patients = new PatientService(store);
        DoctorService doctors = new DoctorService(store);
        AppointmentService appointments = new AppointmentService(store);

        Patient p = patients.addPatient("Test Patient", 20, "Male", "9876543210", "General");
        assert p.getId() == 1;
        assert patients.findById(1) != null;

        Doctor d = doctors.addDoctor("Test Doctor", "Physician", "General Medicine", "9876543211");
        assert d.getId() == 1;
        assert doctors.findById(1) != null;

        Appointment a = appointments.book(1, 1, "2026-09-18", "10:00");
        assert a.getId() == 1;
        assert appointments.getAll().size() == 1;

        assert appointments.cancel(1);
        assert appointments.findById(1).getStatus().equals("CANCELLED");

        assert patients.delete(1);
        assert patients.findById(1) == null;

        System.out.println("All basic tests passed.");
        delete(temp);
    }

    private static void delete(File file) {
        if (!file.exists()) return;
        if (file.isDirectory()) {
            File[] children = file.listFiles();
            if (children != null) {
                for (File child : children) delete(child);
            }
        }
        file.delete();
    }
}
