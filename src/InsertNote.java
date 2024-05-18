import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertNote {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO NOTE (CodeEt, CodeMat, note) VALUES (?, ?, ?)")) {
            ps.setInt(1, 1);
            ps.setInt(2, 2);
            ps.setFloat(3, 17.0f);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
