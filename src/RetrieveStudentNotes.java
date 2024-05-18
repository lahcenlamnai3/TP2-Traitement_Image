import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class RetrieveStudentNotes {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT E.CodeEtudiant, E.NomEtudiant, N.note " +
                             "FROM ETUDIANT E " +
                             "JOIN NOTE N ON E.CodeEtudiant = N.CodeEt")) {
            while (rs.next()) {
                System.out.println(rs.getInt("CodeEtudiant") + " " + rs.getString("NomEtudiant") + " " + rs.getFloat("note"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
