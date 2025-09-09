import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {
    public void addDoctor(String name, String specialty) {
        String sql = "INSERT INTO doctors (name, specialty) VALUES (?, ?)";
        try (Connection con = DBUtil.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, specialty);
            ps.executeUpdate();
            System.out.println("Doctor added successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<String> getAllDoctors() {
        List<String> doctors = new ArrayList<>();
        String sql = "SELECT * FROM doctors";
        try (Connection con = DBUtil.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                doctors.add(rs.getInt("id") + " - " +
                        rs.getString("name") +
                        " [" + rs.getString("specialty") + "]");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctors;
    }
}
