
package pistonriojano.dao;

import pistonriojano.modelo.Mecanicos;
import pistonriojano.modelo.Especialidad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MecanicosDAO {

    public boolean insertar(Mecanicos mecanico) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Mecanicos"
            (legajo, nombre, apellidos, especialidad, valor_hora)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, mecanico.getLegajo());
            ps.setString(2, mecanico.getNombre());
            ps.setString(3, mecanico.getApellido());
            ps.setString(4, mecanico.getEspecialidad().name());
            ps.setInt(5, mecanico.getValor_hora());

            return ps.executeUpdate() > 0;
        }
    }

    public Mecanicos buscarPorLegajo(int legajo) throws SQLException {
        String sql = """
            SELECT * FROM "PistonRiojano"."Mecanicos"
            WHERE legajo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, legajo);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearMecanico(rs);
                }
            }
        }

        return null;
    }

    public List<Mecanicos> listar() throws SQLException {
        List<Mecanicos> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM "PistonRiojano"."Mecanicos"
            ORDER BY legajo
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMecanico(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Mecanicos mecanico) throws SQLException {
        String sql = """
            UPDATE "PistonRiojano"."Mecanicos"
            SET nombre = ?, apellidos = ?,
                especialidad = ?, valor_hora = ?
            WHERE legajo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, mecanico.getNombre());
            ps.setString(2, mecanico.getApellido());
            ps.setString(3, mecanico.getEspecialidad().name());
            ps.setInt(4, mecanico.getValor_hora());
            ps.setInt(5, mecanico.getLegajo());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(int legajo) throws SQLException {
        String sql = """
            DELETE FROM "PistonRiojano"."Mecanicos"
            WHERE legajo = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, legajo);
            return ps.executeUpdate() > 0;
        }
    }

    private Mecanicos mapearMecanico(ResultSet rs) throws SQLException {
        Mecanicos mecanico = new Mecanicos();

        mecanico.setLegajo(rs.getInt("legajo"));
        mecanico.setNombre(rs.getString("nombre"));
        mecanico.setApellido(rs.getString("apellidos"));
        mecanico.setEspecialidad(
            Especialidad.valueOf(rs.getString("especialidad"))
        );
        mecanico.setValor_hora(rs.getInt("valor_hora"));

        return mecanico;
    }
}
