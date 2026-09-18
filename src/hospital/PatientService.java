package hospital;

import java.util.ArrayList;
import java.util.List;

public class PatientService {
    private final List<Patient> patients;
    private final DataStore store;

    public PatientService(DataStore store) {
        this.store = store;
        this.patients = new ArrayList<>(store.load("patients.dat"));
    }

    public Patient addPatient(String name, int age, String gender, String phone, String disease) {
        int id = nextId();
        Patient patient = new Patient(id, name, age, gender, phone, disease);
        patients.add(patient);
        save();
        return patient;
    }

    public List<Patient> getAll() {
        return new ArrayList<>(patients);
    }

    public Patient findById(int id) {
        for (Patient patient : patients) {
            if (patient.getId() == id) return patient;
        }
        return null;
    }

    public boolean delete(int id) {
        Patient patient = findById(id);
        if (patient == null) return false;
        patients.remove(patient);
        save();
        return true;
    }

    private int nextId() {
        int max = 0;
        for (Patient p : patients) max = Math.max(max, p.getId());
        return max + 1;
    }

    private void save() {
        store.save(patients, "patients.dat");
    }
}
