import java.sql.*;

public class RetrieveStudents {
    private static final String URL = "jdbc:mysql://localhost:3306/gestion_etudiants";
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    public static void main(String[] args) throws SQLException {

        try {

            Connection c =  DriverManager.getConnection(URL, USER, PASSWORD);
            Statement statement = c.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM ETUDIANT");
            System.out.println("**** : "+resultSet.getFetchSize());
            while (resultSet.next()) {
                int code = resultSet.getInt("CodeEtudiant");
                String nom = resultSet.getString("NomEtudiant");
                String ddn = resultSet.getDate("ddnEtudiant").toString();
                System.out.println("Code: " + code + ", Nom: " + nom + ", Date de Naissance: " + ddn);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    }

