import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateStudentName {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE ETUDIANT SET NomEtudiant = ? WHERE CodeEtudiant = ?")) {
            ps.setString(1, "anas safi");
            ps.setInt(2, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
