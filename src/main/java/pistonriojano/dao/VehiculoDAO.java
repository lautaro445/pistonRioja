
package pistonriojano.dao;

import pistonriojano.modelo.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO {

    public boolean insertar(Vehiculos vehiculo) throws SQLException {
        if (vehiculo instanceof Autos) {
            return new AutoDAO().insertar((Autos) vehiculo);
        }

        if (vehiculo instanceof Motos) {
            return new MotoDAO().insertar((Motos) vehiculo);
        }

        String sql = """
            INSERT INTO "PistonRiojano"."Vehiculos"
            (patente, marca, modelo, anio, clientes_dni)
            VALUES (?, ?, ?, ?, ?)
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, vehiculo.getPatente());
            ps.setString(2, vehiculo.getMarca());
            ps.setString(3, vehiculo.getModelo());
            ps.setInt(4, vehiculo.getAnio());
            ps.setInt(5, vehiculo.getCliente().getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public Vehiculos buscarPorPatente(String patente) throws SQLException {
        String sql = """
            SELECT v.*,
                   v.tableoid::regclass::text AS tabla
            FROM "PistonRiojano"."Vehiculos" v
            WHERE v.patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tabla = rs.getString("tabla");

                    if (tabla.endsWith("Autos")) {
                        return new AutoDAO().buscarPorPatente(patente);
                    }

                    if (tabla.endsWith("Motos")) {
                        return new MotoDAO().buscarPorPatente(patente);
                    }

                    return mapearVehiculo(rs);
                }
            }
        }

        return null;
    }

    public List<Vehiculos> listar() throws SQLException {
        List<Vehiculos> lista = new ArrayList<>();

        String sql = """
            SELECT v.*,
                   v.tableoid::regclass::text AS tabla
            FROM "PistonRiojano"."Vehiculos" v
            ORDER BY patente
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String patente = rs.getString("patente");
                String tabla = rs.getString("tabla");

                if (tabla.endsWith("Autos")) {
                    lista.add(new AutoDAO().buscarPorPatente(patente));
                } else if (tabla.endsWith("Motos")) {
                    lista.add(new MotoDAO().buscarPorPatente(patente));
                } else {
                    lista.add(mapearVehiculo(rs));
                }
            }
        }

        return lista;
    }

    public boolean modificar(Vehiculos vehiculo) throws SQLException {
        if (vehiculo instanceof Autos) {
            return new AutoDAO().modificar((Autos) vehiculo);
        }

        if (vehiculo instanceof Motos) {
            return new MotoDAO().modificar((Motos) vehiculo);
        }

        String sql = """
            UPDATE ONLY "PistonRiojano"."Vehiculos"
            SET marca = ?, modelo = ?, anio = ?, clientes_dni = ?
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, vehiculo.getMarca());
            ps.setString(2, vehiculo.getModelo());
            ps.setInt(3, vehiculo.getAnio());
            ps.setInt(4, vehiculo.getCliente().getDni());
            ps.setString(5, vehiculo.getPatente());

            return ps.executeUpdate() > 0;
        }
    }

    public boolean eliminar(String patente) throws SQLException {
        String sql = """
            DELETE FROM "PistonRiojano"."Vehiculos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);
            return ps.executeUpdate() > 0;
        }
    }

    private Vehiculos mapearVehiculo(ResultSet rs) throws SQLException {
        Vehiculos vehiculo = new Vehiculos() {};

        vehiculo.setPatente(rs.getString("patente"));
        vehiculo.setMarca(rs.getString("marca"));
        vehiculo.setModelo(rs.getString("modelo"));
        vehiculo.setAnio(rs.getInt("anio"));

        ClientesDAO clientesDAO = new ClientesDAO();
        vehiculo.setCliente(
            clientesDAO.buscarPorDni(rs.getInt("clientes_dni"))
        );

        return vehiculo;
    }
}
