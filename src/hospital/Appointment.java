package hospital;

import java.io.Serializable;

public class Appointment implements Serializable {
    private final int id;
    private final int patientId;
    private final int doctorId;
    private final String date;
    private final String time;
    private String status;

    public Appointment(int id, int patientId, int doctorId, String date, String time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.date = date;
        this.time = time;
        this.status = "BOOKED";
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public String getStatus() { return status; }

    public void cancel() { status = "CANCELLED"; }

    @Override
    public String toString() {
        return String.format("Appointment %d | Patient ID: %d | Doctor ID: %d | %s %s | %s",
                id, patientId, doctorId, date, time, status);
    }
}
