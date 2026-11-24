
package pistonriojano.modelo;

public class Mecanicos{
private int legajo;
private String nombre;
private String apellido;
private Especialidad especialidad;
private int valor_hora;

    public Mecanicos() {
    }

    public Mecanicos(int legajo, String nombre, String apellido, Especialidad especialidad, int valor_hora) {
        this.legajo = legajo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
        this.valor_hora = valor_hora;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getValor_hora() {
        return valor_hora;
    }

    public void setValor_hora(int valor_hora) {
        this.valor_hora = valor_hora;
    }


}



        
        