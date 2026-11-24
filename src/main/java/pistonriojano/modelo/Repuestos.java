
package pistonriojano.modelo;

public class Repuestos { 
    private String codigo;
    private String descripcion;
    private float precio_unitario;
    private int stock_disponible;

    public Repuestos() {
    }

    public Repuestos(String codigo, String descripcion, float precio_unitario, int stock_disponible) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precio_unitario = precio_unitario;
        this.stock_disponible = stock_disponible;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getStock_disponible() {
        return stock_disponible;
    }

    public void setStock_disponible(int stock_disponible) {
        this.stock_disponible = stock_disponible;
    }
    
}