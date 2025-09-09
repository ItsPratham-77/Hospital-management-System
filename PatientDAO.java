import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
    public void addPatient(String name, String gender, LocalDate dob) {
        String sql = "INSERT INTO patients (name, gender, dob) VALUES (?, ?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setDate(3, Date.valueOf(dob));
            ps.executeUpdate();
            System.out.println("Patient added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllPatients() {
        List<String> patients = new ArrayList<>();
        String sql = "SELECT * FROM patients";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                patients.add(rs.getInt("id") + " - " +
                        rs.getString("name") + " (" +
                        rs.getString("gender") + ", DOB: " +
                        rs.getDate("dob") + ")");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }
}
