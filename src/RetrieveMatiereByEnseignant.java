import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetrieveMatiereByEnseignant {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "SELECT M.NomMatiere " +
                             "FROM ENSEIGNANT E " +
                             "JOIN MATIERE M ON E.CodeMat = M.CodeMat " +
                             "WHERE E.NomEns = ?")) {
            ps.setString(1, "Prof. Y");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    System.out.println(rs.getString("NomMatiere"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
