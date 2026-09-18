package hospital;

import java.util.List;
import java.util.Scanner;

public class HospitalSystem {
    private final InputHelper input;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final AppointmentService appointmentService;
    private final ReportService reportService;

    public HospitalSystem() {
        DataStore store = new DataStore("data");
        Scanner scanner = new Scanner(System.in);
        input = new InputHelper(scanner);
        patientService = new PatientService(store);
        doctorService = new DoctorService(store);
        appointmentService = new AppointmentService(store);
        reportService = new ReportService();
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = input.readInt("Enter choice: ", 1, 5);
            switch (choice) {
                case 1 -> patientMenu();
                case 2 -> doctorMenu();
                case 3 -> appointmentMenu();
                case 4 -> reportMenu();
                case 5 -> {
                    running = false;
                    System.out.println("Thank you for using the Hospital Management System.");
                }
            }
        }
    }

    private void printMainMenu() {
        System.out.println("\n========================================");
        System.out.println("       HOSPITAL MANAGEMENT SYSTEM");
        System.out.println("========================================");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Appointment Management");
        System.out.println("4. Reports");
        System.out.println("5. Exit");
    }

    private void patientMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Patient Management ---");
            System.out.println("1. Add patient");
            System.out.println("2. View patients");
            System.out.println("3. Search patient");
            System.out.println("4. Delete patient");
            System.out.println("5. Back");
            int choice = input.readInt("Enter choice: ", 1, 5);

            switch (choice) {
                case 1 -> addPatient();
                case 2 -> printPatients(patientService.getAll());
                case 3 -> searchPatient();
                case 4 -> deletePatient();
                case 5 -> back = true;
            }
        }
    }

    private void addPatient() {
        String name = input.readRequired("Name: ");
        int age = input.readInt("Age: ", 1, 120);
        String gender = input.readRequired("Gender: ");
        String phone = input.readPhone("Phone: ");
        String disease = input.readRequired("Condition: ");

        Patient patient = patientService.addPatient(name, age, gender, phone, disease);
        System.out.println("Patient added successfully. Patient ID: " + patient.getId());
    }

    private void printPatients(List<Patient> patients) {
        if (patients.isEmpty()) {
            System.out.println("No patients found.");
            return;
        }
        patients.forEach(System.out::println);
    }

    private void searchPatient() {
        int id = input.readInt("Patient ID: ", 1, Integer.MAX_VALUE);
        Patient patient = patientService.findById(id);
        System.out.println(patient == null ? "Patient not found." : patient);
    }

    private void deletePatient() {
        int id = input.readInt("Patient ID: ", 1, Integer.MAX_VALUE);
        System.out.println(patientService.delete(id) ? "Patient deleted." : "Patient not found.");
    }

    private void doctorMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Doctor Management ---");
            System.out.println("1. Add doctor");
            System.out.println("2. View doctors");
            System.out.println("3. Search doctor");
            System.out.println("4. Back");
            int choice = input.readInt("Enter choice: ", 1, 4);

            switch (choice) {
                case 1 -> addDoctor();
                case 2 -> doctorService.getAll().forEach(System.out::println);
                case 3 -> searchDoctor();
                case 4 -> back = true;
            }
        }
    }

    private void addDoctor() {
        String name = input.readRequired("Doctor name: ");
        String specialization = input.readRequired("Specialization: ");
        showDepartments();
        String department = input.readRequired("Department: ");
        String phone = input.readPhone("Phone: ");

        Doctor doctor = doctorService.addDoctor(name, specialization, department, phone);
        System.out.println("Doctor added successfully. Doctor ID: " + doctor.getId());
    }

    private void searchDoctor() {
        int id = input.readInt("Doctor ID: ", 1, Integer.MAX_VALUE);
        Doctor doctor = doctorService.findById(id);
        System.out.println(doctor == null ? "Doctor not found." : doctor);
    }

    private void appointmentMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Appointment Management ---");
            System.out.println("1. Book appointment");
            System.out.println("2. View appointments");
            System.out.println("3. Cancel appointment");
            System.out.println("4. Back");
            int choice = input.readInt("Enter choice: ", 1, 4);

            switch (choice) {
                case 1 -> bookAppointment();
                case 2 -> appointmentService.getAll().forEach(System.out::println);
                case 3 -> cancelAppointment();
                case 4 -> back = true;
            }
        }
    }

    private void bookAppointment() {
        int patientId = input.readInt("Patient ID: ", 1, Integer.MAX_VALUE);
        if (patientService.findById(patientId) == null) {
            System.out.println("Patient does not exist.");
            return;
        }

        int doctorId = input.readInt("Doctor ID: ", 1, Integer.MAX_VALUE);
        if (doctorService.findById(doctorId) == null) {
            System.out.println("Doctor does not exist.");
            return;
        }

        String date = input.readRequired("Date (YYYY-MM-DD): ");
        String time = input.readRequired("Time (HH:MM): ");

        Appointment appointment = appointmentService.book(patientId, doctorId, date, time);
        System.out.println("Appointment booked. Appointment ID: " + appointment.getId());
    }

    private void cancelAppointment() {
        int id = input.readInt("Appointment ID: ", 1, Integer.MAX_VALUE);
        System.out.println(appointmentService.cancel(id)
                ? "Appointment cancelled."
                : "Appointment not found or already cancelled.");
    }

    private void reportMenu() {
        reportService.printReport(
                patientService.getAll(),
                doctorService.getAll(),
                appointmentService.getAll()
        );
    }

    private void showDepartments() {
        System.out.println("Available departments:");
        Department.getDepartments().forEach(d -> System.out.println("- " + d));
    }
}
