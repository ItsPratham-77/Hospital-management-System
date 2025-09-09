import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class HospitalManagementSystem {
    private static Scanner sc = new Scanner(System.in);
    private static PatientDAO patientDAO = new PatientDAO();
    private static DoctorDAO doctorDAO = new DoctorDAO();
    private static AppointmentDAO appointmentDAO = new AppointmentDAO();

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();
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
        System.out.println("\n--- Hospital Management System (JDBC) ---");
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
        LocalDate dob = LocalDate.parse(sc.nextLine());

        patientDAO.addPatient(name, gender, dob);
    }

    private static void listPatients() {
        patientDAO.getAllPatients().forEach(System.out::println);
    }

    private static void addDoctor() {
        System.out.print("Enter doctor name: ");
        String name = sc.nextLine();
        System.out.print("Enter specialty: ");
        String specialty = sc.nextLine();

        doctorDAO.addDoctor(name, specialty);
    }

    private static void listDoctors() {
        doctorDAO.getAllDoctors().forEach(System.out::println);
    }

    private static void scheduleAppointment() {
        System.out.print("Enter patient ID: ");
        int pid = sc.nextInt();
        System.out.print("Enter doctor ID: ");
        int did = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter appointment datetime (yyyy-mm-ddTHH:mm): ");
        LocalDateTime dt = LocalDateTime.parse(sc.nextLine());

        appointmentDAO.addAppointment(pid, did, dt);
    }

    private static void listAppointments() {
        appointmentDAO.getAllAppointments().forEach(System.out::println);
    }
}
