
package pistonriojano.dao;

import pistonriojano.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MotoDAO {

    public boolean insertar(Motos moto) throws SQLException {
        String sql = """
            INSERT INTO "PistonRiojano"."Motos"
            (patente, marca, modelo, anio, clientes_dni, cilindrada)
            VALUES (?, ?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, moto.getPatente());
            ps.setString(2, moto.getMarca());
            ps.setString(3, moto.getModelo());
            ps.setInt(4, moto.getAnio());
            ps.setInt(5, moto.getCliente().getDni());
            ps.setInt(6, moto.getCilindrada());

            return ps.executeUpdate() > 0;
        }
    }

    public Motos buscarPorPatente(String patente) throws SQLException {
        String sql = """
            SELECT * FROM ONLY "PistonRiojano"."Motos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearMoto(rs);
                }
            }
        }

        return null;
    }

    public List<Motos> listar() throws SQLException {
        List<Motos> lista = new ArrayList<>();

        String sql = """
            SELECT * FROM ONLY "PistonRiojano"."Motos"
            ORDER BY patente
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearMoto(rs));
            }
        }

        return lista;
    }

    public boolean modificar(Motos moto) throws SQLException {
        String sql = """
            UPDATE ONLY "PistonRiojano"."Motos"
            SET marca = ?, modelo = ?, anio = ?,
                clientes_dni = ?, cilindrada = ?
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, moto.getMarca());
            ps.setString(2, moto.getModelo());
            ps.setInt(3, moto.getAnio());
            ps.setInt(4, moto.getCliente().getDni());
            ps.setInt(5, moto.getCilindrada());
            ps.setString(6, moto.getPatente());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String patente) throws SQLException {
        String sql = """
            DELETE FROM ONLY "PistonRiojano"."Motos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);
            return ps.executeUpdate() > 0;
        }
    }

    private Motos mapearMoto(ResultSet rs) throws SQLException {
        Motos moto = new Motos();

        moto.setPatente(rs.getString("patente"));
        moto.setMarca(rs.getString("marca"));
        moto.setModelo(rs.getString("modelo"));
        moto.setAnio(rs.getInt("anio"));
        moto.setCilindrada(rs.getInt("cilindrada"));

        ClientesDAO clientesDAO = new ClientesDAO();
        moto.setCliente(
            clientesDAO.buscarPorDni(rs.getInt("clientes_dni"))
        );

        return moto;
    }
}
