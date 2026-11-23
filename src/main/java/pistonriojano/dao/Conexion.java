import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL =
        "jdbc:postgresql://localhost:5432/taller_piston";

    private static final String USUARIO = "postgres";
    private static final String PASSWORD = "paradigma123";

    public static Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(
                URL,
                USUARIO,
                PASSWORD
            );

            System.out.println("¡Conexión exitosa!");

        } catch (SQLException e) {
            System.out.println("Error al conectar:");
            e.printStackTrace();
        }

        return conexion;
    }

    public static void main(String[] args) {
        Connection conexion = conectar();

        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
