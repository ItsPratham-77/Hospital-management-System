import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Patient class
class Patient {
    private int id;
    private String name;
    private String gender;
    private LocalDate dob;

    public Patient(int id, String name, String gender, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', gender=" + gender + ", dob=" + dob + "}";
    }
}

// Doctor class
class Doctor {
    private int id;
    private String name;
    private String specialty;

    public Doctor(int id, String name, String specialty) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return "Doctor{id=" + id + ", name='" + name + "', specialty=" + specialty + "}";
    }
}

// Appointment class
class Appointment {
    private int id;
    private Patient patient;
    private Doctor doctor;
    private LocalDateTime dateTime;

    public Appointment(int id, Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.id = id;
        this.patient = patient;
        this.doctor = doctor;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Appointment{id=" + id + ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() + ", dateTime=" + dateTime + "}";
    }
}

// Main management system
public class MainExecution{
    private static List<Patient> patients = new ArrayList<>();
    private static List<Doctor> doctors = new ArrayList<>();
    private static List<Appointment> appointments = new ArrayList<>();

    private static int patientIdCounter = 1;
    private static int doctorIdCounter = 1;
    private static int appointmentIdCounter = 1;

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline
            switch (choice) {
                case 1 -> addPatient();
                case 2 -> listPatients();
                case 3 -> addDoctor();
                case 4 -> listDoctors();
                case 5 -> scheduleAppointment();
                case 6 -> listAppointments();
                case 0 -> running = false;
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Hospital Management System ---");
        System.out.println("1. Add Patient");
        System.out.println("2. List Patients");
        System.out.println("3. Add Doctor");
        System.out.println("4. List Doctors");
        System.out.println("5. Schedule Appointment");
        System.out.println("6. List Appointments");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    private static void addPatient() {
        System.out.print("Enter patient name: ");
        String name = sc.nextLine();
        System.out.print("Enter gender: ");
        String gender = sc.nextLine();
        System.out.print("Enter DOB (yyyy-mm-dd): ");
        String dobStr = sc.nextLine();
        LocalDate dob = LocalDate.parse(dobStr);

        Patient p = new Patient(patientIdCounter++, name, gender, dob);
        patients.add(p);
        System.out.println("Patient added: " + p);
    }

    private static void listPatients() {
        System.out.println("--- Patients ---");
        patients.forEach(System.out::println);
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialty: ");
        String specialty = sc.nextLine();

        Doctor d = new Doctor(doctorIdCounter++, name, specialty);
        doctors.add(d);
        System.out.println("Doctor added: " + d);
    }

    private static void listDoctors() {
        System.out.println("--- Doctors ---");
        doctors.forEach(System.out::println);
    }

    private static void scheduleAppointment() {
        if (patients.isEmpty() || doctors.isEmpty()) {
            System.out.println("Add at least one patient and one doctor first!");
            return;
        }

        System.out.println("Available Patients:");
        patients.forEach(System.out::println);
        System.out.print("Enter patient ID: ");
        int pid = sc.nextInt();
        sc.nextLine();

        System.out.println("Available Doctors:");
        doctors.forEach(System.out::println);
        System.out.print("Enter doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter appointment date/time (yyyy-mm-ddTHH:mm): ");
        String dtStr = sc.nextLine();
        LocalDateTime dateTime = LocalDateTime.parse(dtStr);

        Patient patient = patients.stream().filter(p -> p.getId() == pid).findFirst().orElse(null);
        Doctor doctor = doctors.stream().filter(d -> d.getId() == did).findFirst().orElse(null);

        if (patient != null && doctor != null) {
            Appointment a = new Appointment(appointmentIdCounter++, patient, doctor, dateTime);
            appointments.add(a);
            System.out.println("Appointment scheduled: " + a);
        } else {
            System.out.println("Invalid patient or doctor ID!");
        }
    }

    private static void listAppointments() {
        System.out.println("--- Appointments ---");
        appointments.forEach(System.out::println);
    }
}
