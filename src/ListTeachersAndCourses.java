import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListTeachersAndCourses {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT E.CodeEns, E.NomEns, E.GradeEns, M.CodeMat, M.NomMatiere " +
                    "FROM ENSEIGNANT E " +
                    "JOIN MATIERE M ON E.CodeMat = M.CodeMat";
            try (PreparedStatement ps = conn.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println("CodeEns: " + rs.getInt("CodeEns") +
                            ", NomEns: " + rs.getString("NomEns") +
                            ", GradeEns: " + rs.getString("GradeEns") +
                            ", CodeMat: " + rs.getInt("CodeMat") +
                            ", NomMatiere: " + rs.getString("NomMatiere"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
