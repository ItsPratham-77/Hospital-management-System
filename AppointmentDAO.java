import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
    public void addAppointment(int patientId, int doctorId, LocalDateTime dt) {
        String sql = "INSERT INTO appointments (patient_id, doctor_id, appointment_datetime) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, patientId);
            ps.setInt(2, doctorId);
            ps.setTimestamp(3, Timestamp.valueOf(dt));
            ps.executeUpdate();
            System.out.println("Appointment scheduled successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllAppointments() {
        List<String> appts = new ArrayList<>();
        String sql = """
            SELECT a.id, p.name AS patient, d.name AS doctor, a.appointment_datetime
            FROM appointments a
            JOIN patients p ON a.patient_id = p.id
            JOIN doctors d ON a.doctor_id = d.id
        """;
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                appts.add("ID " + rs.getInt("id") +
                        " | Patient: " + rs.getString("patient") +
                        " | Doctor: " + rs.getString("doctor") +
                        " | Time: " + rs.getTimestamp("appointment_datetime"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appts;
    }
}
