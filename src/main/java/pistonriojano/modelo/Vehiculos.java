package pistonriojano.modelo;


public abstract class Vehiculos {
    private String patente;
    private String marca;
    private String modelo;
    private int anio;
    private Clientes cliente;

    public Vehiculos() {
    }

    public Vehiculos(String patente, String marca, String modelo, int anio, Clientes cliente) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.cliente = cliente;
    }
    
    
}
    