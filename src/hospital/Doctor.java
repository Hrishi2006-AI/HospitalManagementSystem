package hospital;

import java.io.Serializable;

public class Doctor implements Serializable {
    private final int id;
    private String name;
    private String specialization;
    private String department;
    private String phone;

    public Doctor(int id, String name, String specialization, String department, String phone) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.department = department;
        this.phone = phone;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public String getDepartment() { return department; }
    public String getPhone() { return phone; }

    @Override
    public String toString() {
        return String.format("ID: %d | Dr. %s | %s | Department: %s | Phone: %s",
                id, name, specialization, department, phone);
    }
}
