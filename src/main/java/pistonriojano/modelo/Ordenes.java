package pistonriojano.modelo;
import java.time.LocalDate;

public class Ordenes {
    private int numero;
    private LocalDate fecha_ingreso;
    private String descripcion_problema;
    private Mecanicos responsable;
    private Estados estado;
    private Vehiculos patente_vehiculo;

    public Ordenes() {
    }

    public Ordenes(int numero, LocalDate fecha_ingreso, String descripcion_problema, Mecanicos responsable, Estados estado, Vehiculos patente_vehiculo) {
        this.numero = numero;
        this.fecha_ingreso = fecha_ingreso;
        this.descripcion_problema = descripcion_problema;
        this.responsable = responsable;
        this.estado = estado;
        this.patente_vehiculo = patente_vehiculo;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public LocalDate getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(LocalDate fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getDescripcion_problema() {
        return descripcion_problema;
    }

    public void setDescripcion_problema(String descripcion_problema) {
        this.descripcion_problema = descripcion_problema;
    }

    public Mecanicos getResponsable() {
        return responsable;
    }

    public void setResponsable(Mecanicos responsable) {
        this.responsable = responsable;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Vehiculos getPatente_vehiculo() {
        return patente_vehiculo;
    }

    public void setPatente_vehiculo(Vehiculos patente_vehiculo) {
        this.patente_vehiculo = patente_vehiculo;
    }
    
}