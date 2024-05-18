import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListStudentsByDOB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez la date de référence (yyyy-mm-dd) : ");
        String referenceDate = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT CodeEtudiant, NomEtudiant, ddnEtudiant " +
                    "FROM ETUDIANT " +
                    "WHERE ddnEtudiant > ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setDate(1, java.sql.Date.valueOf(referenceDate));
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
