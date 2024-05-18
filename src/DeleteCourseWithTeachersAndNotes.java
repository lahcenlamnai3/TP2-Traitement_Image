import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteCourseWithTeachersAndNotes {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeMat de la matière à supprimer : ");
        int codeMat = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false); // Commencer une transaction

            // Étape 1: Supprimer les notes associées à la matière
            String deleteNotesQuery = "DELETE FROM NOTE WHERE CodeMat = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteNotesQuery)) {
                ps.setInt(1, codeMat);
                ps.executeUpdate();
            }

            // Étape 2: Supprimer les enseignants associés à la matière
            String deleteTeachersQuery = "DELETE FROM ENSEIGNANT WHERE CodeMat = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteTeachersQuery)) {
                ps.setInt(1, codeMat);
                ps.executeUpdate();
            }

            // Étape 3: Supprimer la matière
            String deleteCourseQuery = "DELETE FROM MATIERE WHERE CodeMat = ?";
            try (PreparedStatement ps = conn.prepareStatement(deleteCourseQuery)) {
                ps.setInt(1, codeMat);
                ps.executeUpdate();
            }

            conn.commit(); // Valider la transaction
            System.out.println("Matière, enseignants et notes associés supprimés avec succès !");
        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = DBConnection.getConnection()) {
                conn.rollback(); // Annuler la transaction en cas d'erreur
                System.out.println("Transaction annulée.");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
