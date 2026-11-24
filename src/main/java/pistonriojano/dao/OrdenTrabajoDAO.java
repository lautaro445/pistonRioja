
package pistonriojano.dao;

import pistonriojano.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdenTrabajoDAO {

    public boolean insertar(Ordenes orden) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Ordenes"
            (numero, fecha_ingreso, descripcion_problema,
             responsable, estado_actual, patente_vehiculo)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orden.getNumero());
            ps.setDate(2, Date.valueOf(orden.getFecha_ingreso()));
            ps.setString(3, orden.getDescripcion_problema());
            ps.setInt(4, orden.getResponsable().getLegajo());
            ps.setString(5, orden.getEstado().name());
            ps.setString(6, orden.getPatente_vehiculo().getPatente());

            return ps.executeUpdate() > 0;
        }
    }

    public Ordenes buscarPorNumero(int numero) throws SQLException {
        String sql = """
            SELECT * FROM "PistonRiojano"."Ordenes"
            WHERE numero = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearOrden(rs);
                }
            }
        }

        return null;
    }

    public List<Ordenes> listar() throws SQLException {
        List<Ordenes> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM "PistonRiojano"."Ordenes"
            ORDER BY numero
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearOrden(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Ordenes orden) throws SQLException {
        String sql = """
            UPDATE "PistonRiojano"."Ordenes"
            SET fecha_ingreso = ?,
                descripcion_problema = ?,
                responsable = ?,
                estado_actual = ?,
                patente_vehiculo = ?
            WHERE numero = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, Date.valueOf(orden.getFecha_ingreso()));
            ps.setString(2, orden.getDescripcion_problema());
            ps.setInt(3, orden.getResponsable().getLegajo());
            ps.setString(4, orden.getEstado().name());
            ps.setString(5, orden.getPatente_vehiculo().getPatente());
            ps.setInt(6, orden.getNumero());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int numero) throws SQLException {
        String sql = """
            DELETE FROM "PistonRiojano"."Ordenes"
            WHERE numero = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numero);
            return ps.executeUpdate() > 0;
        }
    }

    private Ordenes mapearOrden(ResultSet rs) throws SQLException {
        Ordenes orden = new Ordenes();

        orden.setNumero(rs.getInt("numero"));
        orden.setFecha_ingreso(
            rs.getDate("fecha_ingreso").toLocalDate()
        );
        orden.setDescripcion_problema(
            rs.getString("descripcion_problema")
        );

        String estado = rs.getString("estado_actual")
            .trim().replace(' ', '_');

        orden.setEstado(Estados.valueOf(estado));

        MecanicosDAO mecanicosDAO = new MecanicosDAO();
        orden.setResponsable(
            mecanicosDAO.buscarPorLegajo(rs.getInt("responsable"))
        );

        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        orden.setPatente_vehiculo(
            vehiculoDAO.buscarPorPatente(
                rs.getString("patente_vehiculo")
            )
        );

        return orden;
    }
}
