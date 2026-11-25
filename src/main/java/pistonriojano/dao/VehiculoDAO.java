
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
            ps.setString(5, vehiculo.getCliente().getDni());

            return ps.executeUpdate() > 0;
        }
    }

    public Vehiculos buscarPorPatente(String patente) throws SQLException {

        String sql = """
            SELECT * FROM ONLY "PistonRiojano"."Vehiculos"
            WHERE patente = ?
            """;

        try (Connection con = Conexion.conectar();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, patente);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearVehiculo(rs);
                }
            }
        }

        Autos auto = new AutoDAO().buscarPorPatente(patente);

        if (auto != null) {
            return auto;
        }

        return new MotoDAO().buscarPorPatente(patente);
    }

    public List<Vehiculos> listar() throws SQLException {

        List<Vehiculos> lista = new ArrayList<>();

        lista.addAll(new AutoDAO().listar());
        lista.addAll(new MotoDAO().listar());

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
            ps.setString(4, vehiculo.getCliente().getDni());
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
            clientesDAO.buscarPorDni(rs.getString("clientes_dni"))
        );

        return vehiculo;
    }
}
