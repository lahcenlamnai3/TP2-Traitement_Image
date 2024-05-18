import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ListEnseignantInCourse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeMat de la matière : ");
        int codeMat = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT CodeEns, NomEns, GradeEns " +
                    "FROM ENSEIGNANT " +
                    "WHERE CodeMat = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, codeMat);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        System.out.println("CodeEns: " + rs.getInt("CodeEns") +
                                ", NomEns: " + rs.getString("NomEns") +
                                ", GradeEns: " + rs.getString("GradeEns"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
