
package pistonriojano.dao;

import pistonriojano.modelo.Clientes;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientesDAO {

    public boolean insertar(Clientes cliente) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Clientes"
            (dni, nombre, apellido, telefono, email)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEmail());

            return ps.executeUpdate() > 0;
        }
    }

    public Clientes buscarPorDni(String dni) throws SQLException {
        String sql = """
            SELECT * FROM "PistonRiojano"."Clientes"
            WHERE dni = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearCliente(rs);
                }
            }
        }

        return null;
    }

    public List<Clientes> listar() throws SQLException {
        List<Clientes> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM "PistonRiojano"."Clientes"
            ORDER BY apellido, nombre
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Clientes cliente) throws SQLException {
        String sql = """
            UPDATE "PistonRiojano"."Clientes"
            SET nombre = ?, apellido = ?, telefono = ?, email = ?
            WHERE dni = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String dni) throws SQLException {
        String sql = """
            DELETE FROM "PistonRiojano"."Clientes"
            WHERE dni = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, dni);
            return ps.executeUpdate() > 0;
        }
    }

    private Clientes mapearCliente(ResultSet rs) throws SQLException {
        return new Clientes(
            rs.getString("dni"),
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("telefono"),
            rs.getString("email")
        );
    }
}
