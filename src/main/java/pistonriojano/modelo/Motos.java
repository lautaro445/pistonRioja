package pistonriojano.modelo;


public class Motos extends Vehiculos{
    private int cilindrada;

    public Motos() {
    }

    public Motos(int cilindrada, String patente, String marca, String modelo, int anio, Clientes cliente) {
        super(patente, marca, modelo, anio, cliente);
        this.cilindrada = cilindrada;
    }

    public int getCilindrada() {
        return cilindrada;
    }

    public void setCilindrada(int cilindrada) {
        this.cilindrada = cilindrada;
    }



    
    
}