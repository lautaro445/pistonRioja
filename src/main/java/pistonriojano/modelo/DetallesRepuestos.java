package pistonriojano.modelo;

public class DetallesRepuestos {
    private Repuestos codigo_repuesto;
    private Ordenes num_orden;
    private float precio_vigente;
    private int cantidad;

    public DetallesRepuestos() {
    }

    public DetallesRepuestos(Repuestos codigo_repuesto, Ordenes num_orden, float pracio_vigente, int cantidad) {
        this.codigo_repuesto = codigo_repuesto;
        this.num_orden = num_orden;
        this.precio_vigente = pracio_vigente;
        this.cantidad = cantidad;
    }


    public Repuestos getCodigo_repuesto() {
        return codigo_repuesto;
    }

    public void setCodigo_repuesto(Repuestos codigo_repuesto) {
        this.codigo_repuesto = codigo_repuesto;
    }

    public Ordenes getNum_orden() {
        return num_orden;
    }

    public void setNum_orden(Ordenes num_orden) {
        this.num_orden = num_orden;
    }

    public float getPracio_vigente() {
        return precio_vigente;
    }

    public void setPracio_vigente(float pracio_vigente) {
        this.precio_vigente = precio_vigente;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}