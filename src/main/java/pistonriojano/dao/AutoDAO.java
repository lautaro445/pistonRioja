
package pistonriojano.dao;

import pistonriojano.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO {

    public boolean insertar(Autos auto) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Autos"
            (patente, marca, modelo, anio, clientes_dni,
             cantidad_puertas, combustible)
            VALUES (?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, auto.getPatente());
            ps.setString(2, auto.getMarca());
            ps.setString(3, auto.getModelo());
            ps.setInt(4, auto.getAnio());
            ps.setString(5, auto.getCliente().getDni());
            ps.setInt(6, auto.getCantidad_puertas());
            ps.setString(7, auto.getCombustible().name());

            return ps.executeUpdate() > 0;
        }
    }

    public Autos buscarPorPatente(String patente) throws SQLException {
        String sql = """
            SELECT * FROM ONLY "PistonRiojano"."Autos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearAuto(rs);
                }
            }
        }

        return null;
    }

    public List<Autos> listar() throws SQLException {
        List<Autos> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM ONLY "PistonRiojano"."Autos"
            ORDER BY patente
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearAuto(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Autos auto) throws SQLException {
        String sql = """
            UPDATE ONLY "PistonRiojano"."Autos"
            SET marca = ?, modelo = ?, anio = ?,
                clientes_dni = ?, cantidad_puertas = ?, combustible = ?
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, auto.getMarca());
            ps.setString(2, auto.getModelo());
            ps.setInt(3, auto.getAnio());
            ps.setString(4, auto.getCliente().getDni());
            ps.setInt(5, auto.getCantidad_puertas());
            ps.setString(6, auto.getCombustible().name());
            ps.setString(7, auto.getPatente());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String patente) throws SQLException {
        String sql = """
            DELETE FROM ONLY "PistonRiojano"."Autos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);
            return ps.executeUpdate() > 0;
        }
    }

    private Autos mapearAuto(ResultSet rs) throws SQLException {
        Autos auto = new Autos();

        auto.setPatente(rs.getString("patente"));
        auto.setMarca(rs.getString("marca"));
        auto.setModelo(rs.getString("modelo"));
        auto.setAnio(rs.getInt("anio"));
        auto.setCantidad_puertas(rs.getInt("cantidad_puertas"));

        auto.setCombustible(
            Combustibles.valueOf(rs.getString("combustible"))
        );

        ClientesDAO clientesDAO = new ClientesDAO();
        auto.setCliente(
            clientesDAO.buscarPorDni(rs.getString("clientes_dni"))
        );

        return auto;
    }
}
