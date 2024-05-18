import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListStudentsInCourse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeMat de la matière : ");
        int codeMat = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT E.CodeEtudiant, E.NomEtudiant, E.ddnEtudiant " +
                    "FROM ETUDIANT E " +
                    "JOIN NOTE N ON E.CodeEtudiant = N.CodeEt " +
                    "WHERE N.CodeMat = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, codeMat);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("CodeEtudiant: " + rs.getInt("CodeEtudiant") +
                                ", NomEtudiant: " + rs.getString("NomEtudiant") +
                                ", Date de naissance: " + rs.getDate("ddnEtudiant"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
