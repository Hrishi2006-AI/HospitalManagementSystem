package hospital;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private final List<Appointment> appointments;
    private final DataStore store;

    public AppointmentService(DataStore store) {
        this.store = store;
        this.appointments = new ArrayList<>(store.load("appointments.dat"));
    }

    public Appointment book(int patientId, int doctorId, String date, String time) {
        Appointment appointment = new Appointment(nextId(), patientId, doctorId, date, time);
        appointments.add(appointment);
        save();
        return appointment;
    }

    public List<Appointment> getAll() {
        return new ArrayList<>(appointments);
    }

    public Appointment findById(int id) {
        for (Appointment appointment : appointments) {
            if (appointment.getId() == id) return appointment;
        }
        return null;
    }

    public boolean cancel(int id) {
        Appointment appointment = findById(id);
        if (appointment == null || appointment.getStatus().equals("CANCELLED")) {
            return false;
        }
        appointment.cancel();
        save();
        return true;
    }

    private int nextId() {
        int max = 0;
        for (Appointment a : appointments) max = Math.max(max, a.getId());
        return max + 1;
    }

    private void save() {
        store.save(appointments, "appointments.dat");
    }
}
