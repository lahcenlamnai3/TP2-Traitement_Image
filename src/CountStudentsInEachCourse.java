import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountStudentsInEachCourse {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT M.CodeMat, M.NomMatiere, COUNT(N.CodeEt) AS NombreEtudiants " +
                    "FROM MATIERE M " +
                    "LEFT JOIN NOTE N ON M.CodeMat = N.CodeMat " +
                    "GROUP BY M.CodeMat, M.NomMatiere";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("CodeMat: " + rs.getInt("CodeMat") +
                            ", NomMatiere: " + rs.getString("NomMatiere") +
                            ", Nombre d'étudiants: " + rs.getInt("NombreEtudiants"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
