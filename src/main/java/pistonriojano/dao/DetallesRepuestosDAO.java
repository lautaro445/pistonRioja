
package pistonriojano.dao;

import pistonriojano.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetallesRepuestosDAO {

   public boolean insertar(DetallesRepuestos detalle) throws SQLException {
    String sql = """
        INSERT INTO "PistonRiojano"."DetallesRespuestos"
        (codigo_repuesto, num_orden, precio_vigente, cantidad)
        VALUES (?, ?, ?, ?)
        """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, detalle.getCodigo_repuesto().getCodigo());
        ps.setInt(2, detalle.getNum_orden().getNumero());
        ps.setFloat(3, detalle.getPracio_vigente());
        ps.setInt(4, detalle.getCantidad());

        return ps.executeUpdate() > 0;
    }
}

    public DetallesRepuestos buscar(String codigo, int numeroOrden)
            throws SQLException {

        String sql = """
            SELECT * FROM "PistonRiojano"."DetallesRespuestos"
            WHERE codigo_repuesto = ? AND num_orden = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ps.setInt(2, numeroOrden);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearDetalle(rs);
                }
            }
        }

        return null;
    }

    public List<DetallesRepuestos> listarPorOrden(int numeroOrden)
            throws SQLException {

        List<DetallesRepuestos> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM "PistonRiojano"."DetallesRespuestos"
            WHERE num_orden = ?
            ORDER BY codigo_repuesto
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, numeroOrden);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearDetalle(rs));
                }
            }
        }

        return lista;
    }

    public boolean eliminar(String codigo, int numeroOrden)
            throws SQLException {

        String sql = """
            DELETE FROM "PistonRiojano"."DetallesRespuestos"
            WHERE codigo_repuesto = ? AND num_orden = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            ps.setInt(2, numeroOrden);

            return ps.executeUpdate() > 0;
        }
    }

    private DetallesRepuestos mapearDetalle(ResultSet rs)
            throws SQLException {

        DetallesRepuestos detalle = new DetallesRepuestos();

        RepuestoDAO repuestoDAO = new RepuestoDAO();
        OrdenTrabajoDAO ordenDAO = new OrdenTrabajoDAO();

        detalle.setCodigo_repuesto(
            repuestoDAO.buscarPorCodigo(rs.getString("codigo_repuesto"))
        );

        detalle.setNum_orden(
            ordenDAO.buscarPorNumero(rs.getInt("num_orden"))
        );

        detalle.setPracio_vigente(rs.getFloat("precio_vigente"));
        detalle.setCantidad(rs.getInt("cantidad"));
        
        return detalle;
    }
}
