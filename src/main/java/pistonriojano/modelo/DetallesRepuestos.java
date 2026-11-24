package pistonriojano.modelo;

public class DetallesRepuestos {
    private Repuestos codigo_repuesto;
    private Ordenes num_orden;
    private float pracio_vigente;

    public DetallesRepuestos() {
    }

    public DetallesRepuestos(Repuestos codigo_repuesto, Ordenes num_orden, float pracio_vigente) {
        this.codigo_repuesto = codigo_repuesto;
        this.num_orden = num_orden;
        this.pracio_vigente = pracio_vigente;
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
        return pracio_vigente;
    }

    public void setPracio_vigente(float pracio_vigente) {
        this.pracio_vigente = pracio_vigente;
    }
    
    
}