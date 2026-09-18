package hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private final List<Doctor> doctors;
    private final DataStore store;

    public DoctorService(DataStore store) {
        this.store = store;
        this.doctors = new ArrayList<>(store.load("doctors.dat"));
    }

    public Doctor addDoctor(String name, String specialization, String department, String phone) {
        Doctor doctor = new Doctor(nextId(), name, specialization, department, phone);
        doctors.add(doctor);
        save();
        return doctor;
    }

    public List<Doctor> getAll() {
        return new ArrayList<>(doctors);
    }

    public Doctor findById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getId() == id) return doctor;
        }
        return null;
    }

    private int nextId() {
        int max = 0;
        for (Doctor d : doctors) max = Math.max(max, d.getId());
        return max + 1;
    }

    private void save() {
        store.save(doctors, "doctors.dat");
    }
}
