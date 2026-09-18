# Hospital Management System

A command-line Hospital Management System developed in Java. The application manages patients, doctors, appointments, departments, and basic hospital reports.

## Features

### 1. Patient Management
- Add patient
- View all patients
- Search patient by ID
- Delete patient

### 2. Doctor Management
- Add doctor
- View all doctors
- Search doctor by ID

### 3. Appointment Management
- Book an appointment
- View appointments
- Cancel an appointment

### 4. Department Management
- View hospital departments

### 5. Reports
- Patient count
- Doctor count
- Appointment count
- Department-wise doctor count

## Technologies

- Java 17+
- Java Collections Framework
- Object-Oriented Programming
- File handling for persistent storage
- Exception handling
- Command-line interface
- Git/GitHub

No external libraries or database server are required.

## Project Structure

```text
HospitalManagementSystem/
├── src/
│   └── hospital/
│       ├── Main.java
│       ├── HospitalSystem.java
│       ├── Patient.java
│       ├── Doctor.java
│       ├── Appointment.java
│       ├── Department.java
│       ├── DataStore.java
│       ├── InputHelper.java
│       ├── PatientService.java
│       ├── DoctorService.java
│       ├── AppointmentService.java
│       └── ReportService.java
├── data/
│   └── .gitkeep
├── test/
│   └── HospitalSystemTest.java
├── run.bat
├── run.sh
├── statement.md
└── .gitignore
```

## Requirements

Install Java Development Kit (JDK) 17 or later.

Check Java:

```bash
java -version
javac -version
```

## Run on Windows

Open Command Prompt or PowerShell in the project root.

### Option 1: Use the included script

```powershell
.\run.bat
```

### Option 2: Compile manually

PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp out hospital.Main
```

## Run on Linux/macOS

```bash
chmod +x run.sh
./run.sh
```

Or manually:

```bash
mkdir -p out
javac -d out $(find src -name "*.java")
java -cp out hospital.Main
```

## Testing

The project contains a small command-line test class.

Windows PowerShell:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
javac -cp out -d out test/HospitalSystemTest.java
java -cp out HospitalSystemTest
```

Linux/macOS:

```bash
javac -d out $(find src -name "*.java")
javac -cp out -d out test/HospitalSystemTest.java
java -cp out HospitalSystemTest
```

Expected output:

```text
All basic tests passed.
```

## Data Storage

The application stores records as text files inside the `data` directory. The application creates the files automatically when required.

## Example Workflow

```text
1. Start application
2. Select Patient Management
3. Add a patient
4. Select Doctor Management
5. Add a doctor
6. Select Appointment Management
7. Book an appointment
8. Select Reports
9. View hospital statistics
10. Exit
```

## Notes

This is a terminal-based application. It does not require Eclipse, IntelliJ IDEA, a web server, or a GUI to execute.
