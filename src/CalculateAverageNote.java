import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class CalculateAverageNote {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT AVG(note) AS average " +
                             "FROM NOTE " +
                             "WHERE CodeEt = ?")) {
            ps.setInt(1, 1);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Average note: " + rs.getFloat("average"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
