import java.sql.Connection;
import java.sql.PreparedStatement;

public class UpdateMatiereCoef {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE MATIERE SET CoefMatiere = ? WHERE CodeMat = ?")) {
            ps.setInt(1, 3);
            ps.setInt(2, 1);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
