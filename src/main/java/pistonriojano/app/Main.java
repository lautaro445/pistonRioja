import java.sql.Connection;
import pistonriojano.dao.Conexion;

public class Main
{
    public static void main(String[] args)
    {
    Connection conexion = Conexion.conectar();
    
    if(conexion != null)
    {
    System.out.println("Conectado correctamente");
    }
    else
    {
    System.out.println("No se pudo conectar");
    }
    
    }
}