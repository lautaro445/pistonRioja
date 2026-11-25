
package pistonriojano.app;

import java.sql.Connection;
import javax.swing.JOptionPane;
import pistonriojano.dao.Conexion;
import pistonriojano.vista.VentanaPrincipal;

public class Main {

    public static void main(String[] args) {

        Connection conexion = Conexion.conectar();

        if (conexion != null) {

            JOptionPane.showMessageDialog(null, "Conexión Correcta a la Base de datos"
                    + "");

            System.out.println("Conectado correctamente");

            VentanaPrincipal ventana = new VentanaPrincipal();
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);

        } else {

            JOptionPane.showMessageDialog(null, "No se pudo conectar");

            System.out.println("No se pudo conectar");
        }
    }
}
