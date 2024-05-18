import java.sql.Connection;
import java.sql.PreparedStatement;

public class DeleteNotesForStudent {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM NOTE WHERE CodeEt = ?")) {
            ps.setInt(1, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
