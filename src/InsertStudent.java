import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertStudent {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Combien d'étudiants voulez-vous ajouter ? ");
        int nombreEtudiants = scanner.nextInt();
        scanner.nextLine();

        try (Connection conn = DBConnection.getConnection()) {
            for (int i = 0; i < nombreEtudiants; i++) {
                System.out.println("Saisir les informations de l'étudiant " + (i + 1));

                System.out.print("CodeEtudiant: ");
                int codeEtudiant = scanner.nextInt();
                scanner.nextLine();  // Consume newline

                // Vérifier si l'étudiant existe déjà
                if (etudiantExiste(conn, codeEtudiant)) {
                    System.out.println("L'étudiant avec le code " + codeEtudiant + " existe déjà. Sauter à la prochaine entrée.");
                    continue;
                }

                System.out.print("NomEtudiant: ");
                String nomEtudiant = scanner.nextLine();

                System.out.print("Date de naissance (yyyy-mm-dd): ");
                String ddnEtudiant = scanner.nextLine();

                // Insérer l'étudiant
                try (PreparedStatement ps = conn.prepareStatement(
                        "INSERT INTO ETUDIANT (CodeEtudiant, NomEtudiant, ddnEtudiant) VALUES (?, ?, ?)")) {
                    ps.setInt(1, codeEtudiant);
                    ps.setString(2, nomEtudiant);
                    ps.setDate(3, java.sql.Date.valueOf(ddnEtudiant));
                    ps.executeUpdate();
                    System.out.println("Étudiant ajouté avec succès !");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static boolean etudiantExiste(Connection conn, int codeEtudiant) {
        String query = "SELECT COUNT(*) FROM ETUDIANT WHERE CodeEtudiant = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, codeEtudiant);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
