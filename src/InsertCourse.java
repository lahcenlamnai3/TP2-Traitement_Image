import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertCourse {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le CodeMat de la matière : ");
        int codeMat = scanner.nextInt();
        scanner.nextLine();  // Consume newline
        System.out.print("Entrez le NomMatiere : ");
        String nomMatiere = scanner.nextLine();
        System.out.print("Entrez le CoefMatiere : ");
        int coefMatiere = scanner.nextInt();

        try (Connection conn = DBConnection.getConnection()) {
            String query = "INSERT INTO MATIERE (CodeMat, NomMatiere, CoefMatiere) VALUES (?, ?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(query)) {
                ps.setInt(1, codeMat);
                ps.setString(2, nomMatiere);
                ps.setInt(3, coefMatiere);
                ps.executeUpdate();
                System.out.println("Nouvelle matière ajoutée avec succès !");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
