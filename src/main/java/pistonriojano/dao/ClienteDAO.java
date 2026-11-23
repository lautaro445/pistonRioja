
package pistonriojano.dao;

import pistonriojano.modelo.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    // ==========================================
    // 1. INSERTAR CLIENTE
    // ==========================================

    public void insertar(Cliente cliente) throws SQLException {

        String sql = """
            INSERT INTO clientes
            (dni, nombre, apellido, telefono, email)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getDni());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellido());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getEmail());

            ps.executeUpdate();
        }
    }


    // ==========================================
    // 2. LISTAR TODOS LOS CLIENTES
    // ==========================================

    public List<Cliente> listar() throws SQLException {

        List<Cliente> clientes = new ArrayList<>();

        String sql = """
            SELECT dni, nombre, apellido, telefono, email
            FROM clientes
            ORDER BY apellido, nombre
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Cliente cliente = new Cliente(
                    rs.getString("dni"),
                    rs.getString("nombre"),
                    rs.getString("apellido"),
                    rs.getString("telefono"),
                    rs.getString("email")
                );

                clientes.add(cliente);
            }
        }

        return clientes;
    }


    // ==========================================
    // 3. BUSCAR CLIENTE POR DNI
    // ==========================================

    public Cliente buscarPorDni(String dni) throws SQLException {

        String sql = """
            SELECT dni, nombre, apellido, telefono, email
            FROM clientes
            WHERE dni = ?
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    return new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("telefono"),
                        rs.getString("email")
                    );
                }
            }
        }

        return null;
    }


    // ==========================================
    // 4. MODIFICAR CLIENTE
    // ==========================================

    public boolean modificar(Cliente cliente) throws SQLException {

        String sql = """
            UPDATE clientes
            SET nombre = ?,
                apellido = ?,
                telefono = ?,
                email = ?
            WHERE dni = ?
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTelefono());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getDni());

            return ps.executeUpdate() > 0;
        }
    }


    // ==========================================
    // 5. ELIMINAR CLIENTE
    // ==========================================

    public boolean eliminar(String dni) throws SQLException {

        String sql = """
            DELETE FROM clientes
            WHERE dni = ?
            """;

        try (Connection conexion = Conexion.conectar();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, dni);

            return ps.executeUpdate() > 0;
        }
    }
}
