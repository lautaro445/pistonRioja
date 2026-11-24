package pistonriojano.modelo;


public class Autos extends Vehiculos {
    private int cantidad_puertas;
    private Combustibles combustible;

    public Autos() {
    }

    public Autos(int cantidad_puertas, Combustibles combustible, String patente, String marca, String modelo, int anio, Clientes cliente) {
        super(patente, marca, modelo, anio, cliente);
        this.cantidad_puertas = cantidad_puertas;
        this.combustible = combustible;
    }

    public int getCantidad_puertas() {
        return cantidad_puertas;
    }

    public void setCantidad_puertas(int cantidad_puertas) {
        this.cantidad_puertas = cantidad_puertas;
    }

    public Combustibles getCombustible() {
        return combustible;
    }

    public void setCombustible(Combustibles combustible) {
        this.combustible = combustible;
    }


  
}