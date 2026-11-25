
package pistonriojano.dao;

import pistonriojano.modelo.Repuestos;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepuestoDAO {

    public boolean insertar(Repuestos repuesto) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Repuestos"
            (codigo, descripcion, precio_unitario, stock_disponible)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, repuesto.getCodigo());
            ps.setString(2, repuesto.getDescripcion());
            ps.setFloat(3, repuesto.getPrecio_unitario());
            ps.setInt(4, repuesto.getStock_disponible());

            return ps.executeUpdate() > 0;
        }
    }

    public Repuestos buscarPorCodigo(String codigo) throws SQLException {
        String sql = """
            SELECT * FROM "PistonRiojano"."Repuestos"
            WHERE codigo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearRepuesto(rs);
                }
            }
        }

        return null;
    }

    public List<Repuestos> listar() throws SQLException {
        List<Repuestos> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM "PistonRiojano"."Repuestos"
            ORDER BY codigo
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearRepuesto(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Repuestos repuesto) throws SQLException {
        String sql = """
            UPDATE "PistonRiojano"."Repuestos"
            SET descripcion = ?, precio_unitario = ?,
                stock_disponible = ?
            WHERE codigo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, repuesto.getDescripcion());
            ps.setFloat(2, repuesto.getPrecio_unitario());
            ps.setInt(3, repuesto.getStock_disponible());
            ps.setString(4, repuesto.getCodigo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String codigo) throws SQLException {
        String sql = """
            DELETE FROM "PistonRiojano"."Repuestos"
            WHERE codigo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;
        }
    }

    private Repuestos mapearRepuesto(ResultSet rs) throws SQLException {
        return new Repuestos(
            rs.getString("codigo"),
            rs.getString("descripcion"),
            rs.getFloat("precio_unitario"),
            rs.getInt("stock_disponible")
        );
    }

    
    public List<Repuestos> buscar(String texto) throws SQLException {

    List<Repuestos> lista = new ArrayList<>();

    String sql = """
        SELECT * FROM "PistonRiojano"."Repuestos"
        WHERE codigo ILIKE ? OR descripcion ILIKE ?
        ORDER BY codigo
        """;

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, "%" + texto + "%");
        ps.setString(2, "%" + texto + "%");

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearRepuesto(rs));
            }
        }
    }

    return lista;
}   
    }

