import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateTeacherGrade {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeEns de l'enseignant : ");
        int codeEns = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Entrez le nouveau GradeEns : ");
        String newGradeEns = scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "UPDATE ENSEIGNANT SET GradeEns = ? WHERE CodeEns = ?";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setString(1, newGradeEns);
                ps.setInt(2, codeEns);
                ps.executeUpdate();
                System.out.println("Grade de l'enseignant mis à jour avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
