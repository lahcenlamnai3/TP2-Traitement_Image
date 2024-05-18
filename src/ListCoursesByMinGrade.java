import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListCoursesByMinGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeEtudiant: ");
        int codeEtudiant = scanner.nextInt();
        System.out.print("Entrez la note minimum: ");
        float noteMinimum = scanner.nextFloat();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT M.CodeMat, M.NomMatiere " +
                    "FROM MATIERE M " +
                    "JOIN NOTE N ON M.CodeMat = N.CodeMat " +
                    "WHERE N.CodeEt = ? AND N.note >= ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, codeEtudiant);
                ps.setFloat(2, noteMinimum);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("CodeMat: " + rs.getInt("CodeMat") +
                                ", NomMatiere: " + rs.getString("NomMatiere"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
