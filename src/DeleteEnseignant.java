import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteEnseignant {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM ENSEIGNANT WHERE CodeEns = ?")) {
            ps.setInt(1, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
